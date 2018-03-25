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
public class RightStartRightSwitchDoubleRightScale extends CommandGroup {

    public RightStartRightSwitchDoubleRightScale() {
    	//1st cube to scale
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(200, .75, true,2,false,false));
    	addParallel(new DriveToPosition(35,.35,true,-35, true,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-30,.3,true,-45, true,true));
    	addSequential(new DriveToPosition(-30,.3,true,-70, true,true));
    	addSequential(new DriveToPosition(25,.35,true,-140, true,false));
    	addSequential(new DriveToPosition(25,.35,true,-150, false,false));
    	addSequential(new RunIntake(false));
    	//2nd cube to switch
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(8,.4,false,-140, false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-60,.4,true,-150, true,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(20,.4,true,-190, true,false));
    	addSequential(new RunIntake(false));
    	//3rd cube to scale
    	addSequential(new DriveToPosition(-15,.5,true,-135, true,true));
    	addSequential(new DriveToPosition(-80,.4,true,-90, true,true));
    	addSequential(new DriveToPosition(-25,.4,true,-20, false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(35,.3,true,-20, false,false));
    	addSequential(new RunIntake(true));
    }
}
