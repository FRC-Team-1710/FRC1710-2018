package commands;
import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class MoveLiftToPosition extends Command {
	
	int _level;
	double setPoint;

    public MoveLiftToPosition(int level) {
    	_level = level;

    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    	if(_level == 0) {
    		setPoint = Constants.intake;
    	}else if(_level == 1) {
    		setPoint = Constants.switchPosition;
    	}else if(_level == 2) {
    		setPoint = Constants.lowLevel;
    	}else if(_level == 3) {
    		setPoint = Constants.highLevel;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.lift1.set(ControlMode.PercentOutput, (-1 * ((setPoint - RobotMap.lift1.getSelectedSensorPosition(0)) * Constants.kPLift)));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (-1 * ((setPoint - RobotMap.lift1.getSelectedSensorPosition(0)) * Constants.kPLift)) <= 0.1 || (-1 * ((setPoint - RobotMap.lift1.getSelectedSensorPosition(0)) * Constants.kPLift)) >= 0.1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}