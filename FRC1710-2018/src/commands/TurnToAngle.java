package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
	
	double _angle;
	boolean _slow;
	int count;
	
    public TurnToAngle(double angle) {
    	_angle = angle;
    	_slow = false;
    }
    
    public TurnToAngle(double angle, boolean slow) {
    	_angle = angle;
    	_slow = slow;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count =0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(_slow) {
    		Drive.setShifters(false);
        	Drive.straightDriveTele(0 , _angle, false);
    	} else {
        	Drive.straightDriveTele(0 , _angle, true);
    	}
    	count++;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Math.abs(Drive.getNavxAngle()) - Math.abs(_angle)) < 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Done Turning " + RobotMap.navx.getAngle());
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
