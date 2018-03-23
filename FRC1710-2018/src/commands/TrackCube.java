package commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Intake;
import org.usfirst.frc.team1710.robot.Vision;

import com.ctre.phoenix.motorcontrol.ControlMode;
/**
 *
 */
public class TrackCube extends Command {

	boolean _isSeekingLeft;
	int count;
	
    public TrackCube(boolean isSeekingLeft) {
    	_isSeekingLeft = isSeekingLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drive.setShifters(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(count < Constants.autoTrackTimeout) {
    		System.out.println("tracking");
    		if(_isSeekingLeft == true) {
    			Vision.cubeTrackLeft();
    		} else {
    			Vision.cubeTrackRight();
    		}	
    	}
    	//starts timing how long it's seeking, if it seeks too long the limelight probably isn't working so It'll just run intake and move forward
    	if(Vision.areCubesAvailable() == false) {
    		count++;
    		if(count > Constants.autoTrackTimeout) {
    			Drive.arcadeDrive(0, -.4, false);
    			Intake.intake(1, 0);
    		}
    	} else {
    		count = 0;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Intake.isCubeInIntake() || Vision.getTvValue() == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Cube collected");
    	count = 0;
    	Intake.intake(0, 0);
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Intake.intake(0, 0);
    	Drive.stopDriving();
    }
}
