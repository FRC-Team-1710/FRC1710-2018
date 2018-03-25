package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Intake;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.RobotMath;


public class DriveToPosition extends Command {

	int _encGoal, count;
	boolean _isInHighGear, _endBehavior, _direction, _startReset, _needToResetStart, _foundSlowDownStart;
	double _totalTicks, _currentTicks, _percentComplete, _output, _slowDownStart,
			_speed, _deltaPos, _goalDist, _heading, _startingPosition;
    
    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, boolean direction) {
    	_speed = speed;
    	//217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * 217;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_direction = direction;
    }

    protected void initialize() {
    	RobotMap.R1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	RobotMap.L1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	RobotMap.R1.setSensorPhase(false);
    	RobotMap.L1.setSensorPhase(true);
    	
    	Drive.setShifters(_isInHighGear);

    	count = 0;
    	_startingPosition = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
        _totalTicks = _encGoal + _startingPosition;	
    	_goalDist = Math.abs(_encGoal);
    }


    protected void execute() { 
    	_currentTicks = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    	_deltaPos = _currentTicks - _startingPosition;
    	_percentComplete = _currentTicks/_totalTicks;

    	if(_endBehavior == true) {
    		if(_direction == true) {
    	    	Drive.straightDriveTele(-1 * _speed, _heading, _isInHighGear);
    		} else {
    	    	Drive.straightDriveTele(_speed, _heading, _isInHighGear);
    		}
    	} else {
        	if(_encGoal < 0) {
            	if(Math.abs(_percentComplete) > .675) {
            		//TODO: Make some sort of signal that allows the lift to be manipulated when changeliftsetpoint is added in parallel w this
            		count++;
            		if(_foundSlowDownStart == false) {
            			_slowDownStart = Math.abs(_currentTicks);
            			_foundSlowDownStart = true;
            		} else {
                		_output =  ( (Math.pow(_deltaPos/(_goalDist + (_slowDownStart * 1.25)), 2) - 1) * _speed); 
            		}
            	} else {
            		_output = -_speed;
            	}
        	} else {
           		if(_percentComplete > .675) {
            		count++;
            		if(_foundSlowDownStart == false) {
            			_slowDownStart = Math.abs(_currentTicks);
            			_foundSlowDownStart = true;
            		} else {
                        _output =  ( (1 - Math.pow(_deltaPos/(_goalDist + (_slowDownStart * 1.25)), 2)) * _speed);
            		}
           		} else {
           			_output = _speed;
           		}
        	}
        	Drive.straightDriveTele(_output, _heading, _isInHighGear);
    	}
    	
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	/*
    	 * heading must be within 8 degrees of the goal, if it isn't and we have met the correct distance the robot should turn
    	 * in place and correct itself. However, if it gets stuck there for more than 2 seconds we will move on.
    	 */
    	
    	if(_direction) {
        	return (_currentTicks <= _totalTicks && Math.abs(Math.abs(_heading) - Math.abs(RobotMap.navx.getAngle())) < 8) || count > 100;
    	} else {
    		return (_currentTicks >= _totalTicks && Math.abs(Math.abs(_heading) - Math.abs(RobotMap.navx.getAngle())) < 8) || count > 100;
    	}
    }

    // Called once after isFinished returns true
    protected void end() { 
    	System.out.println("done");
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
