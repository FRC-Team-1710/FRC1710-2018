package commands;

import org.usfirst.frc.team1710.robot.Intake;
import org.usfirst.frc.team1710.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;


public class PitchIntake extends Command {

	boolean _isGoingUp;
	double _setpoint;
	int count;
    public PitchIntake(boolean isGoingUp) {
    	_isGoingUp = isGoingUp;
    	_setpoint = -254;
    }
    
    public PitchIntake(double setpoint) {
    	_setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count ++;
    	if(_setpoint == -254) {
    		if(_isGoingUp == true) {
    			RobotMap.wrist.set(ControlMode.PercentOutput, .7);
    		}else {
    			RobotMap.wrist.set(ControlMode.PercentOutput, -.8);
    		}
    	} else {
    		Intake.setWristSetpoint(_setpoint);
    	}
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 80;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.wrist.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
