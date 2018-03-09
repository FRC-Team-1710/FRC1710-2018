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
public class LeftStartRightScaleRightSwitch extends CommandGroup {

    public LeftStartRightScaleRightSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(155,.75,true,0,false,false));
    	addSequential(new DriveToPosition(265,.75,true,85,false,false));
    	addSequential(new DriveToPosition(60,.75,true,-90,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new DriveToPosition(-15,.3,true,120, true,true));
    	addSequential(new DriveToPosition(38,.4,true, 140, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition), 100);
    	addSequential(new DriveToPosition(10,.4,false,140,true,false));
    	addSequential(new RunIntake(true));
    }
}
