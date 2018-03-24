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
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(175, .75, true,-1,false,false));
    	addSequential(new DriveToPosition(230, .6, true,-87,false,false));
    	addSequential(new DriveToPosition(40, .6, true,0,true,false));
    	addParallel(new DriveToPosition(15, .3, true,20,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addSequential(new TurnToAngle(-10));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-50, .5, true,-10,true,true));
    	addSequential(new DriveToPosition(-60, .5, true,-90,true,true));
    	addSequential(new DriveToPosition(-20, .5, true,-135,true,true));
    	addSequential(new DriveToPosition(20, .5, true,-160,true,false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(10, .5, true,-160,true,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-10, .5, true,-150,true,false));
    	addSequential(new DriveToPosition(-10, .3, true,-180,true,false));
    	addSequential(new DriveToPosition(15, .3, true,-180,true,false));
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-10, .5, true,-200,true,true));
    	addSequential(new DriveToPosition(-60, .7, true,-270,true,true));
    	addSequential(new DriveToPosition(-20, .5, true,-320,false,true));
    	addSequential(new DriveToPosition(50, .5, true,-360,true,false));
    	addSequential(new DriveToPosition(25, .5, true,-390,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    }
}
