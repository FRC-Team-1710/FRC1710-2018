package commands;

import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetEncoders extends Command {

    public ResetEncoders() {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
