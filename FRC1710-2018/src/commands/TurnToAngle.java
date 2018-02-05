package commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

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
    	SubsystemManager.NavxReset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drive.arcadeDrive(0, (RobotMap.navx.getAngle() - _angle) * mult, false);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.navx.getAngle() < _goal + 5 || RobotMap.navx.getAngle() > _goal - 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
