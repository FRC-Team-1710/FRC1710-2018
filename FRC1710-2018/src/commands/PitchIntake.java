package commands;
import org.usfirst.frc.team1710.robot.Intake;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class PitchIntake extends Command {

	double _goalWrist;
    public PitchIntake(double goalWrist) {
    	_goalWrist = goalWrist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Intake.setWristPosition(_goalWrist);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Intake.manipulateWrist();    	
			} 

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

     protected void interrupted() {
    }
}