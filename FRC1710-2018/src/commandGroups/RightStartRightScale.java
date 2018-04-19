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
public class RightStartRightScale extends CommandGroup {

    public RightStartRightScale() {
    	addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(Constants.wallToScale, .75, true,0,false,false));
    	addSequential(new DriveToPosition(8, .3, true,-40,false,false,true));
    	addSequential(new RunIntake(true));
    }
}
