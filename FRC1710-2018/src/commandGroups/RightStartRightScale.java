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
    	addSequential(new PitchIntake(false));
    	addSequential(new DriveToPosition(215, .75, true,2,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(20,.5,true,-50, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 750));
    	addSequential(new DriveToPosition(-60,.5,false,0, false,true));
    }
}
