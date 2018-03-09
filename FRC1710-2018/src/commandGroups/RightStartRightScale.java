package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.RunIntake;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartRightScale extends CommandGroup {

    public RightStartRightScale() {
    	addSequential(new DriveToPosition(200, .75, true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(35,.5,true,42, true,false));
    	addSequential(new RunIntake(true));
    }
}
