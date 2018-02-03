package commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1710.robot.Vision;

import com.ctre.phoenix.motorcontrol.ControlMode;
/**
 *
 */
public class TrackCube extends Command {

    public TrackCube() {
    	Vision.initializeVision();
    	Vision.cubeTrackLeft();
    	Vision.cubeTrackRight();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
