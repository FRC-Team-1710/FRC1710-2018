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
    	addSequential(new DriveToPosition(60000, .75, true,0,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new TurnToAngle(-60));
    	addSequential(new RunIntake(true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TurnToAngle(-80));
    	addSequential(new RightScaleToRightSwitch());
    }
}
