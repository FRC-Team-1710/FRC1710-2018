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
public class LeftStartLeftScaleRightSwitch extends CommandGroup {

    public LeftStartLeftScaleRightSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(220, .75, true,-2,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(30,.5,true,30, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new TurnToAngle(45));
    	addSequential(new DriveToPosition(-190,.8,true,-100, false,true));
    	addSequential(new DriveToPosition(-55,.4,true,-145, true,true));
    	addSequential(new DriveToPosition(15,.4,true,-145, true,false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition, 100));
    	addSequential(new DriveToPosition(12,.5,true,-143, true,false));
    	addSequential(new RunIntake(true));
    }
}
