package commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {

	boolean _didCubeStartInIntake;
	int count = 0;
	int timeoutOut = 40;
	int timeoutIn = 65;
	
    public RunIntake(boolean didCubeStartInIntake) {
    	_didCubeStartInIntake = didCubeStartInIntake;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//didCubeStartInIntake = Intake.isCubeInIntake();
    	//set to true for auto testing
    	System.out.println("running intake");
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
    	if(_didCubeStartInIntake == true) {
        	if(count > 10) {
        		Intake.intake(0, .7);
        	} else {
        		Intake.intake(0, 0);
        	}
    	}else {
    		Intake.intake(1, 0);
    		Drive.arcadeDrive(0, -.25, false);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//testing
    	if(_didCubeStartInIntake == true) {
        	return count > timeoutOut;
    	} else {
        	return count > timeoutIn;
    	}
     //return didCubeStartInIntake != Intake.isCubeInIntake();
    		 
    }
    // Called once after isFinished returns true
    protected void end() {
		Intake.intake(0, 0);
		Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
