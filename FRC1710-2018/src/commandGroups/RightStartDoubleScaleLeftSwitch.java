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
    	addSequential(new DriveToPosition(-30,.4,true, -120, true, true));
    	addSequential(new DriveToPosition(-65,.4,true, -90, true, true));
    	addSequential(new DriveToPosition(-30,.4,true, 0, true, true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(15,.4,false, 0, true, false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 300));
    	addSequential(new DriveToPosition(-20,.4,true, 0, true, true));
    	addSequential(new DriveToPosition(-40,.5,true, -30, true, true));
    	addSequential(new DriveToPosition(180,.5,true, -90, false, false));
    	addSequential(new DriveToPosition(30,.5,true, -150, false, false));
    	addSequential(new RunIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(10,.5,true, -180, false, false));
    	addSequential(new RunIntake(true));
    }
}
