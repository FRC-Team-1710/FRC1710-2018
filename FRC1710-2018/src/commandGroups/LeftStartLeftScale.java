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
public class LeftStartLeftScale extends CommandGroup {

    public LeftStartLeftScale() {
    	addSequential(new DriveToPosition(60000, 1, true));
    	addSequential(new TurnToAngle(-60));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TurnToAngle(180));
    	addSequential(new LeftScaleToLeftSwitch());
    }
}
