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
public class LeftStartLeftScaleLeftSwitch extends CommandGroup {

    public LeftStartLeftScaleLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(205,.75,true,5,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 200));
    	addSequential(new DriveToPosition(35,.5,true,42, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new DriveToPosition(-15,.3,true,115, true,true));
    	addSequential(new DriveToPosition(38,.35,true, 145, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition), 100);
    	addSequential(new DriveToPosition(10,.4,false,140,true,false));
    	addSequential(new RunIntake(true));
    }
}
