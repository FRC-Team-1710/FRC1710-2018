package commands;

import org.usfirst.frc.team1710.robot.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {

	boolean didCubeStartInIntake;
	int count = 0;
	
    public RunIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//didCubeStartInIntake = Intake.isCubeInIntake();
    	//set to true for auto testing
    	didCubeStartInIntake = true;
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
    	if(didCubeStartInIntake == true) {
    		Intake.intake(1, 0);
    	}else {
    		Intake.intake(0, 1);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//testing
    	return count > 35;
     //return didCubeStartInIntake != Intake.isCubeInIntake();
    		 
    }
    // Called once after isFinished returns true
    protected void end() {
		Intake.intake(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
