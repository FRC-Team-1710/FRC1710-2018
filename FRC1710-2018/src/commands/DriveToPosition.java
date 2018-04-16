package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Intake;
import org.usfirst.frc.team1710.robot.Robot;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;
import org.usfirst.frc.team1710.robot.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.RobotMath;


public class DriveToPosition extends Command {

	int _encGoal, count;
	boolean _isInHighGear, _endBehavior, _direction, _startReset, _shouldFixHeading,
			_newStart, _needToResetStart, _foundSlowDownStart, _hellaAccurate, _fixingHeading;
	double _totalTicks, _currentTicks, _percentComplete, _output, _slowDownStart,
			_speed, _deltaPos, _goalDist, _heading, _startingPosition;
    
	Timer driveTime = new Timer();
	
    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, boolean direction) {
    	_speed = speed;
    	//encGoal is in inches, there are 217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * Constants.ticksPerInch;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_direction = direction;
    	_hellaAccurate = false;
    }
    
    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, boolean direction, boolean hellaAccurate) {
    	_speed = speed;
    	//encGoal is in inches, there are 217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * Constants.ticksPerInch;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_direction = direction;
    	_hellaAccurate = hellaAccurate;
    }

    protected void initialize() {
    	//todo try this, if weird things happen go back to QuadEncoder
    	RobotMap.R1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	RobotMap.L1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	RobotMap.R1.setSensorPhase(false);
    	RobotMap.L1.setSensorPhase(true);
    	Drive.setShifters(_isInHighGear);

    	count = 0;
    	_startingPosition = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    	if(!RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20)) {
    		_shouldFixHeading = true;
            _totalTicks = _encGoal + _startingPosition;	
    	} else {
    		_shouldFixHeading = false;
            _totalTicks = _encGoal + _startingPosition;	
    	}
    	_goalDist = Math.abs(_encGoal);
    	driveTime.start();
		System.out.println("Start pos: " + _startingPosition);
		System.out.println("Goal pos: " + _totalTicks);
    }


    protected void execute() { 
    	_currentTicks = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
    	_percentComplete = _currentTicks/_totalTicks;

    	if(_endBehavior == true) {
    		if(_direction == true) {
    	    	Drive.straightDriveTele(-1 * _speed, _heading, _isInHighGear);
    		} else {
    	    	Drive.straightDriveTele(_speed, _heading, _isInHighGear);
    		}
    	} else {
        	if(_encGoal < 0) {
            	if(Math.abs(_percentComplete) > Constants.slowDownPercent) {
            		count++;
            		if(_foundSlowDownStart == false) {
            			_slowDownStart = _currentTicks;
            			_foundSlowDownStart = true;
            		} else {
            			_deltaPos = _currentTicks - _slowDownStart;
                		_output =  ((Math.pow(_deltaPos/(_goalDist + Math.abs(_slowDownStart)), 3) - 1) * _speed); 
            		}
            	} else {
            		_output = -_speed;
            	}
        	} else {
           		if(_percentComplete > Constants.slowDownPercent) {
            		count++;
            		if(_foundSlowDownStart == false) {
            			_slowDownStart = _currentTicks;
            			_foundSlowDownStart = true;
            		} else {
            			_deltaPos = _currentTicks - _slowDownStart;
                        _output =  ( (1 - Math.pow(_deltaPos/(_goalDist + Math.abs(_slowDownStart)), 3)) * _speed);
            		}
           		} else {
           			_output = _speed;
           		}
        	}
        	        	
        	if(_hellaAccurate == true) {
        		lift.override = true;
        		if(!RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20)) {
        			//turns in place until heading is close to prevent giant over corrections that eat the drive distance
                	Drive.straightDriveTele(0 ,_heading, _isInHighGear);
                	_fixingHeading = true;
        		} else {
        			if(!_newStart && _shouldFixHeading) {
        		    	_startingPosition = (Drive.getRightPosition() + Drive.getLeftPosition())/2;
        	            _totalTicks = _encGoal + _startingPosition;
        	            _newStart = true;
        			} else {
                    	_fixingHeading = false;
                    	Drive.straightDriveTele(_output, _heading, _isInHighGear);
        			}
        		}
        	} else {
        		lift.override = false;
            	_fixingHeading = false;
            	Drive.straightDriveTele(_output, _heading, _isInHighGear);
        	}
       	}
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	/*
    	 * heading must be within 8 degrees of the goal, if it isn't and we have met the correct distance the robot should turn
    	 * in place and correct itself. However, if it gets stuck there for more than 3 seconds we will move on.
    	 */
    	
    	if(_direction == true) {
        	return (_currentTicks <= _totalTicks && RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20) && !_fixingHeading);
    	} else {
    		return (_currentTicks >= (_totalTicks-50) && RobotMath.isInRange(Drive.getNavxAngle(), _heading, 20) && !_fixingHeading);
    	}
    }

    // Called once after isFinished returns true
    protected void end() { 
		lift.override = false;
    	driveTime.stop();

		System.out.println("End pos: " + _currentTicks);
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
