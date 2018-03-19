package commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Intake;
import org.usfirst.frc.team1710.robot.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {

	boolean _didCubeStartInIntake;
	int count;
	
    public RunIntake(boolean didCubeStartInIntake) {
    	_didCubeStartInIntake = didCubeStartInIntake;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	System.out.println("running intake");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
        if(_didCubeStartInIntake == true) {
           	Intake.intake(0, .85);
    		Vision.ledEntry.forceSetNumber(2);
           	System.out.println("outtaking " + Intake.getUltraSonic());
        }else {
        	Intake.intake(1, 0);
        	Drive.arcadeDrive(0, -.25, false);
           	System.out.println("intaking " + Intake.getUltraSonic());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return _didCubeStartInIntake != Intake.isCubeInIntake() && count > 25;
    }
    // Called once after isFinished returns true
    protected void end() {
		Intake.intake(0, 0);
    	System.out.println("done running intake");
		Drive.stopDriving();
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
