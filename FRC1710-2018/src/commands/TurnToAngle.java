package commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
	
	double _angle;
	double _speed;
	
    public TurnToAngle(double angle, double speed) {

    	_angle = angle;
    	_speed = speed;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.navx.getAngle() > _angle) {
    		Drive.leftDrive(_speed);
    		
    	}else if(RobotMap.navx.getAngle() < _angle) {
    		Drive.rightDrive(_speed);
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
