package commands;

import org.usfirst.frc.team1710.robot.Drive;

import edu.wpi.first.wpilibj.command.Command;


public class DriveToPosition extends Command {

	double _speed;
	int _encGoal;
	
    public DriveToPosition(int encGoal, double speed) {
    	_speed = speed;
    	_encGoal = encGoal;
    }


    protected void initialize() {
    }


    protected void execute() {
    	if(Drive.getLeftPosition() < _encGoal) {
    		Drive.straightDriveAuto(_speed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Drive.getLeftPosition() >= _encGoal;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drive.straightDriveAuto(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
