package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
	
	double _angle;
	int count;
	
    public TurnToAngle(double angle) {
    	_angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SubsystemManager.NavxReset();
    	count =0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drive.setRobotHeading(_angle);
    	count++;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (RobotMap.navx.getAngle() < _angle + Constants.rotateToAngleHiEnd && RobotMap.navx.getAngle() > _angle - Constants.rotateToAngleLoEnd) || count > 50;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("done" + RobotMap.navx.getAngle()) ;
    	Drive.stopDriving();
    	SubsystemManager.NavxReset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
