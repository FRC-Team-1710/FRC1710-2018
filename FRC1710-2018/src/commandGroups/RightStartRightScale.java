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
    	addSequential(new DriveToPosition(200, .75, true,2,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(30,.3,true,-45, true,false));
    	addSequential(new RunIntake(true));
    }
}
