package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartLeftSwitchDoubleLeftScale extends CommandGroup {

    public RightStartLeftSwitchDoubleLeftScale() {
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(190, .75, true,-1,false,false));
    	addSequential(new DriveToPosition(310, .6, true,-85,false,false));
    	addSequential(new DriveToPosition(60, .6, true,0,true,false));
    	//addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 500));
    	addSequential(new DriveToPosition(50, .3, true,30,false,false));
    	addSequential(new RunIntake(true));
    	//addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TurnToAngle(-10));
    	addSequential(new DriveToPosition(-50, .5, true,-10,true,true));
    	addSequential(new DriveToPosition(-100, .5, true,-90,true,true));
    	addSequential(new DriveToPosition(-20, .5, true,-120,true,true));
    	addSequential(new TrackCube(true));
    }
}
