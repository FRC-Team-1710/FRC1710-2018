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
public class RightStartLeftSwitchDoubleLeftScale extends CommandGroup {

    public RightStartLeftSwitchDoubleLeftScale() {
    	addSequential(new PitchIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(190, .75, true,-1,false,false));
    	addSequential(new DriveToPosition(320, .6, true,-85,false,false));
    	addSequential(new DriveToPosition(60, .6, true,0,true,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 500));
    	addSequential(new DriveToPosition(40, .3, true,30,false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TurnToAngle(-10));
    	addSequential(new DriveToPosition(-50, .5, true,-10,true,true));
    	addSequential(new DriveToPosition(-60, .5, true,-90,true,true));
    	addSequential(new DriveToPosition(-20, .5, true,-135,true,true));
    	addSequential(new TrackCube(true));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(10, .5, true,-180,true,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-10, .5, true,-150,true,false));
    	addSequential(new DriveToPosition(-10, .3, true,-180,true,false));
    	addSequential(new TrackCube(true));
    	addSequential(new DriveToPosition(-10, .5, true,-200,true,true));
    	addSequential(new DriveToPosition(-60, .7, true,-270,true,true));
    	addSequential(new DriveToPosition(-20, .5, true,-320,false,true));
    	addSequential(new DriveToPosition(50, .5, true,-360,true,false));
    	addSequential(new DriveToPosition(25, .5, true,-390,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    }
}
