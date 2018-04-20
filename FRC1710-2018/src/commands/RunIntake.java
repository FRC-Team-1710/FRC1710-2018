package commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Intake;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Vision;
import org.usfirst.frc.team1710.robot.lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {

	boolean _didCubeStartInIntake, startCondition;
	int count, _waitTime, sweepDir;
	double _liftEnc;
	
    public RunIntake(boolean didCubeStartInIntake) {
    	_didCubeStartInIntake = didCubeStartInIntake;
    	_waitTime = 0;
    	_liftEnc = 0;
    }
    
    public RunIntake(boolean didCubeStartInIntake, int waitTime) {
    	_didCubeStartInIntake = didCubeStartInIntake;
    	_waitTime = waitTime;
    	_liftEnc = 0;
    }
    
    public RunIntake(boolean didCubeStartInIntake, double liftEnc) {
    	_didCubeStartInIntake = didCubeStartInIntake;
    	_liftEnc = liftEnc;
    	_waitTime = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
    	if(_waitTime > 0) {
    		startCondition = count > (_waitTime/20);
    	} else if(_liftEnc > 0) {
    		startCondition = lift.getLiftEncPosition() >= (_liftEnc-400);
    	} else {
    		startCondition = true;
    	}
    	
    	if(startCondition) {
            if(_didCubeStartInIntake == true) {
               	Intake.intake(0, .60);
        		Vision.ledEntry.forceSetNumber(2);
        		Drive.stopDriving();
            }else {
            	Intake.intake(.75, 0);
            	Drive.arcadeDrive(0, -.35, false);
            }
    	} else {
    		Drive.stopDriving();
    		Intake.intake(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(_didCubeStartInIntake) {
        	return count > 30;
    	} else {
    		//return count > 75 || Intake.isCubeInIntake();
    		return count > 65;
    	}
    	//return _didCubeStartInIntake != Intake.isCubeInIntake() || (count > 150);
    }
    // Called once after isFinished returns true
    protected void end() {
    	count = 0;
		Intake.intake(0, 0);
		Drive.stopDriving();
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		if(_didCubeStartInIntake == false) {
			System.out.println("cube in: " + Intake.isCubeInIntake() + " ultra: " + Intake.getUltraSonic() + " count: " + count);
		}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
