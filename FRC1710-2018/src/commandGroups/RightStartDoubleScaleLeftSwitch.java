package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TrackCube;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartDoubleScaleLeftSwitch extends CommandGroup {

    public RightStartDoubleScaleLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(190, .75, true,2,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(40,.35,true,-35, false,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 300));
    	addSequential(new DriveToPosition(-15,.3,true,-138, true,true));
    	addSequential(new DriveToPosition(48,.4,true, -152, true, false));
    	addSequential(new TrackCube(false));
    	addSequential(new DriveToPosition(25,.4,true, -135, true, false, true));
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-30,.4,true, 30, true, true));
    	addParallel(new DriveToPosition(15,.4,false, 30, true, false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 300));
    	addSequential(new DriveToPosition(-15,.3,true,-138, true,true));
    	addSequential(new DriveToPosition(40,.5,true, -152, true, false));
    	addSequential(new DriveToPosition(180,.5,true, -90, false, false));
    	addSequential(new TrackCube(false));
    	addSequential(new DriveToPosition(30,.5,true, -150, false, false, true));
    	addSequential(new RunIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(10,.5,true, -180, false, false));
    	addSequential(new RunIntake(true));
    }
}
