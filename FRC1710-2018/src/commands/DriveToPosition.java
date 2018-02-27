package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import edu.wpi.first.wpilibj.command.Command;


public class DriveToPosition extends Command {

	double _speed;
	int _encGoal;
	double _slowPercent;
	double _speedFactor;
	boolean _isInHighGear;
	double _heading;
	double _startingPosition;

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading) {
    	_speed = speed;
    	_encGoal = encGoal;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    }


    protected void initialize() {
    	RobotMap.navx.reset();
    	Drive.setShifters(_isInHighGear);
    	_startingPosition = Math.abs(Drive.getLeftPosition());
    }


    protected void execute() {    	
    	Drive.straightDriveTele((_speed + .8) - (Math.abs(Drive.getLeftPosition())/_encGoal), _heading);
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Drive.getLeftPosition()) >= _encGoal + _startingPosition;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
    }
}
