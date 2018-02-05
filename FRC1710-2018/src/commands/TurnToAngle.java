package commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
	
	double _angle;
	double _speed;
	double _goal;
	double mult = .005;
	
    public TurnToAngle(double angle) {
    	_angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.navx.getAngle() > _angle) {
    		Drive.leftDrive((RobotMap.navx.getAngle() - _angle) * mult);
    		
    	}else if(RobotMap.navx.getAngle() < _angle) {
    		Drive.rightDrive((RobotMap.navx.getAngle() - _angle) * mult);
    		
    	}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.navx.getAngle() < _goal + 5 || RobotMap.navx.getAngle() > _goal - 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
