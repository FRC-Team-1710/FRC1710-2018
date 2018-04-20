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
    	addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(Constants.wallToScale, .8, true,0,false,false));
    	addSequential(new DriveToPosition(10, .3, true,-40,false,false,true));
    	addSequential(new RunIntake(true));
    	addParallel(new DriveToPosition(-15, .4, true,-50,false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake,300));
    	addSequential(new TurnToAngle(-132));
    	addSequential(new DriveToPosition(35,.5,true,-132, false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-25,.5,true,-90, false,true));
    	//addSequential(new PitchIntake(Constants.wristUp));
    	//addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new DriveToPosition(45,.35,true,-30,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new DriveToPosition(-15,.3,true,-40, false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake,500));
    	addSequential(new DriveToPosition(55,.5,true,-120, false,false,true));
    	addSequential(new RunIntake(false));
    }
    
}
