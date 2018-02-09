package commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Vision;

import com.ctre.phoenix.motorcontrol.ControlMode;
/**
 *
 */
public class TrackCube extends Command {

	boolean _isSeekingLeft;
	
    public TrackCube(boolean isSeekingLeft) {
    	_isSeekingLeft = isSeekingLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(_isSeekingLeft == true) {
    		Vision.cubeTrackLeft();
    	} else {
    		Vision.cubeTrackRight();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//ideally we will keep runnning until a cube is detected to be secure in the intake but for now we stop when ty is a certain value
        return Vision.isCubeIntakable();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Cube collected");
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Drive.stopDriving();
    }
}
