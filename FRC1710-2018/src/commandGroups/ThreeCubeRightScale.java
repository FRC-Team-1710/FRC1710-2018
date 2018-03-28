package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeCubeRightScale extends CommandGroup {

    public ThreeCubeRightScale() {
    	//addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(240, .75, true,3,false,false));
    	addSequential(new DriveToPosition(35,.35,true,-40, true,false));
    	//addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	//addSequential(new RunIntake(true));
    	//addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-10,.3,true,-40, true,true));
    	addSequential(new DriveToPosition(-30,.5,true,0, true,true, true));
    	addSequential(new DriveToPosition(-20,.5,true,50, true,true));
    	addSequential(new DriveToPosition(-80,.5,true,90, true,true, true));
    	addSequential(new DriveToPosition(-10,.5,true,120, false,true, true));
    	//addSequential(new RunIntake(false));
    	/*addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-40,.4,true, -120, false, true));
    	addSequential(new DriveToPosition(-65,.4,true, -90, false, true));
    	addSequential(new DriveToPosition(-30,.4,true, 0, false, true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(15,.4,false, 0, true, false));
    	addSequential(new RunIntake(true));*/
    }
}
