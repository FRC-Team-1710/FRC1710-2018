package commands;

import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;


public class PitchIntake extends Command {

	boolean piston;
	boolean toggle;
	
    public PitchIntake() {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (piston) {
			piston = false;
			RobotMap.intakeRight.set(DoubleSolenoid.Value.kForward);
			
		} else if(RobotMap.mechStick.getRawButton(6) == false) {
			toggle = true;
			
		} else {
			piston = true;
			RobotMap.intakeRight.set(DoubleSolenoid.Value.kReverse);
			
			} 
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
