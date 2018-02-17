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

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear) {
    	_speed = speed;
    	_encGoal = encGoal;
    	_isInHighGear=isInHighGear;
    	
    }


    protected void initialize() {
    	RobotMap.navx.reset();
    	RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	Drive.setShifters(_isInHighGear);
    }


    protected void execute() {
    	_slowPercent=.75;
    	if((Math.abs(Drive.getLeftPosition())/_encGoal) < _slowPercent) {
    		Drive.straightDriveAuto(_speed);
    	}else {
    		Drive.straightDriveAuto((_speed + .3) - (Math.abs(Drive.getLeftPosition())/_encGoal));
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Drive.getLeftPosition()) >= _encGoal - 1000;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("done" + Drive.getLeftPosition());
    	RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	Drive.straightDriveAuto(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
