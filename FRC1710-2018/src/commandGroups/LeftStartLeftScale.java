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
    	addSequential(new PitchIntake(false));
    	addSequential(new DriveToPosition(212, .75, true,-2,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(20,.5,true,50, true,false));
    	addSequential(new RunIntake(true));
    }
}
