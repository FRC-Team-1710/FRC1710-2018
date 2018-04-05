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
public class LeftStartLeftScale extends CommandGroup {

    public LeftStartLeftScale() {
    	addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(290, .75, true,0,false,false));
    	addSequential(new DriveToPosition(5, .3, true,90,false,false,true));
    	addSequential(new RunIntake(true));
    }
}
