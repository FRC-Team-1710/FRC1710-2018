package commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SweepingIntake extends Command {

	boolean _sweepDir;
	
	/**
	 * Intakes while doing a wide radius turn. Dead reckon to the outside of the cube and the arm
	 * should slide the cube with the robot as it turns. (Intakes cubes like the Cheesy Poofs' 3 cube auto)
	 * @param sweepDir true: left false: right
	 */
    public SweepingIntake(boolean sweepDir) {
    	_sweepDir = sweepDir;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Intake.intake(1, 0);
    	if(_sweepDir) {
        	Drive.rightDrive(-.25);
        	Drive.leftDrive(.6);
    	} else {
        	Drive.rightDrive(-.6);
        	Drive.leftDrive(.25);
    	}
    }

    protected boolean isFinished() {
        return Intake.isCubeInIntake();
    }

    protected void end() {
    	Drive.stopDriving();
    	Intake.intake(0,0);
    }

    protected void interrupted() {
    }
}
