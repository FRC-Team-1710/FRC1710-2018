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
public class RightStartThreeCubeLeftScale extends CommandGroup {

    public RightStartThreeCubeLeftScale() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(180, .75, true,0,false,false));
    	addSequential(new DriveToPosition(255, .6, true,-90,false,false));
    	addSequential(new DriveToPosition(35, .4, true,5,true,false));
    	addParallel(new DriveToPosition(10, .3, true,20,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addSequential(new TurnToAngle(135));
    	addParallel(new DriveToPosition(40,.3,true,135, false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new RunIntake(false));
    }
}
