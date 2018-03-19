package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.RobotMath;


public class DriveToPosition extends Command {

	double _speed;
	int _encGoal, count;
	double _slowPercent;
	double _speedFactor;
	boolean _isInHighGear, _endBehavior, _direction, _startReset, _needToResetStart, _foundSlowDownStart;
	double _heading;
	double _startingPosition;
	double _totalTicks, _currentTicks, _percentComplete, _error, _lastPos, _slowDownStart, _posIntegral, _output, _deltaPos, _goalDist;
	double  _exitAngle = 0;

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, double exitAngle) {
    	_speed = speed;
    	//217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * 217;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_exitAngle = exitAngle;
    }

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, boolean direction) {
    	_speed = speed;
    	//217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * 217;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_exitAngle = heading;
    	_direction = direction;
    }

    protected void initialize() {
    	RobotMap.R1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	RobotMap.L1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	RobotMap.R1.setSensorPhase(false);
    	RobotMap.L1.setSensorPhase(true);
    	Drive.setShifters(_isInHighGear);

    	count = 0;
    	
    	if(Math.abs(Drive.getNavxAngle() - _heading) < 5) {
    		_needToResetStart = false;
    	} else {
    		_needToResetStart = true;
			System.out.println("need to fix heading");
    	}
    	
    	_startingPosition = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    	System.out.println("Start " + _startingPosition);
    	//actual goal enc value
    	
        _totalTicks = _encGoal + _startingPosition;	
    	
    	_goalDist = Math.abs(_encGoal);

    	System.out.println("goal " + _totalTicks);
    	System.out.println("TargetPos " + _encGoal);
    }


    protected void execute() { 
    	_currentTicks = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    	_deltaPos = _currentTicks - _startingPosition;
    	_error = _currentTicks - _totalTicks;
    	_percentComplete = _currentTicks/_totalTicks;
                
    	SmartDashboard.putNumber("Drive Output", _output);
    	SmartDashboard.putNumber("ENC Pos", _currentTicks);
    	SmartDashboard.putNumber("Perc", _percentComplete);

    	if(_endBehavior == true) {
    		if(_direction == true) {
    	    	Drive.straightDriveTele(-1 * _speed, _heading, _isInHighGear);
    		} else {
    	    	Drive.straightDriveTele(_speed, _heading, _isInHighGear);
    		}
    	} else {
        	if(_encGoal < 0) {
            	if(Math.abs(_percentComplete) > .675) {
            		count++;
            		if(_foundSlowDownStart == false) {
            			_slowDownStart = Math.abs(_currentTicks);
            			_foundSlowDownStart = true;
            		} else {
                		_output =  ( (Math.pow(_deltaPos/(_goalDist + (_slowDownStart * 1.5)), 2) - 1) * _speed); 
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
                        _output =  ( (1 - Math.pow(_deltaPos/(_goalDist + (_slowDownStart * 1.5)), 2)) * _speed);
            		}
           		} else {
           			_output = _speed;
           		}
        	}
        	Drive.straightDriveTele(_output, _heading, _isInHighGear);
    	}
    	
    	_lastPos = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return RobotMath.isInRange(_currentTicks, _totalTicks, 250) || count > 40;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("done");
		_foundSlowDownStart = false;
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
