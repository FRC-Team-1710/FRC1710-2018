package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartThreeCubeLeftScale extends CommandGroup {

    public RightStartThreeCubeLeftScale() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(200, .75, true,-2,false,false));
    	addParallel(new DriveToPosition(30,.35,true,40, true,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-40,.35,true,45, true,true));
    	addSequential(new DriveToPosition(-40,.35,true,90, true,true));
    	addSequential(new DriveToPosition(20,.4,true,145, false,false));
    	addSequential(new DriveToPosition(25,.4,true,155, false,false));
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-30,.4,true, 120, true, true));
    	addSequential(new DriveToPosition(-65,.4,true, 90, true, true));
    	addSequential(new DriveToPosition(-30,.4,true, 0, true, true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(15,.4,false, 0, true, false));
    	addSequential(new RunIntake(true));
    }
}
