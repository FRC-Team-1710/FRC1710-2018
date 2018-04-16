package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.AutoTimer;
import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.ResetEncoders;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import commands.Wait;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeCubeRightScale extends CommandGroup {

    public ThreeCubeRightScale() {
    	addSequential(new DriveToPosition(Constants.wallToScale, .75, true,0,false,false));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new DriveToPosition(9, .3, true,-40,false,false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TurnToAngle(-147));
    	addSequential(new DriveToPosition(30,.4,true,-147, false,false,true));
    	addSequential(new RunIntake(false));
    	addParallel(new PitchIntake(Constants.wristUp));
    	addSequential(new DriveToPosition(-20,.5,true,-90, false,true));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new DriveToPosition(45,.35,true,-20,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new DriveToPosition(-25,.3,true,-40, false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(55,.5,true,-122, false,false,true));
    	addSequential(new RunIntake(false));
    }
    
}
