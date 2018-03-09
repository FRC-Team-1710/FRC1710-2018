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
	int _encGoal;
	double _slowPercent;
	double _speedFactor;
	boolean _isInHighGear, _endBehavior, _direction;
	double _heading;
	double _startingPosition;
	double _totalTicks, _currentTicks, _percentComplete, _error, _output, _deltaPos, _goalDist;
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
    	RobotMap.R1.setSensorPhase(false);
    	RobotMap.L1.setSensorPhase(true);
    	Drive.setShifters(_isInHighGear);

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
    	if(_encGoal < 0) {
        	_output =  ( (Math.pow(_deltaPos/_goalDist, 2) - 1) * _speed) - .2;
    	} else {
        	_output =  ( (1 - Math.pow(_deltaPos/_goalDist, 2)) * _speed) + .2;
    	}

    	//System.out.println("Current Output: " + _output);
    	//System.out.println("Current Ticks: " + _currentTicks);
    	
    	SmartDashboard.putNumber("percent complete", _percentComplete);
    	SmartDashboard.putNumber("Inches", Drive.getRightPosition()/215);
    	SmartDashboard.putNumber("Starting Position", _startingPosition);
    	SmartDashboard.putNumber("Goal Inches", _totalTicks/215);
    	SmartDashboard.putNumber("Angle", Drive.getNavxAngle());
    	SmartDashboard.putNumber("Right", Drive.getRightPosition());
    	SmartDashboard.putNumber("Left", Drive.getLeftPosition());

    	if(_endBehavior == true) {
    		if(_direction == true) {
    	    	Drive.straightDriveTele(-1 * _speed, _heading);
    		} else {
    	    	Drive.straightDriveTele(_speed, _heading);
    		}
    	} else {
    	   Drive.straightDriveTele(_output, _heading);
    	}
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return RobotMath.isInRange(_currentTicks, _totalTicks, 250);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drive.setRobotHeading(_exitAngle);
    	System.out.println("done");
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
