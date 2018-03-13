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
    	addSequential(new DriveToPosition(212, .75, true,-2,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(20,.5,true,50, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new DriveToPosition(-15,.3,true,138, true,true));
    	addSequential(new DriveToPosition(38,.4,true, 152, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition), 100);
    	addSequential(new DriveToPosition(10,.4,false,145,true,false));
    	addSequential(new RunIntake(true));
    }
}
