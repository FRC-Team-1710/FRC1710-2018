package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftStartDoubleScaleRight extends CommandGroup {

    public LeftStartDoubleScaleRight() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(170,.75,true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.aboveBump));
    	addSequential(new DriveToPosition(240,.8,true,83,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(40,.5,true,10,false,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new TurnToAngle(-100));
    	addSequential(new DriveToPosition(-15,.3,true,138, true,true));
    	addSequential(new DriveToPosition(48,.4,true, 152, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-20,.4,true, 0, true, true));
    	addParallel(new DriveToPosition(15,.4,false, 10, true, false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    }
}
