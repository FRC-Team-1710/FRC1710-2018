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
public class RightStartRightScaleLeftSwitch extends CommandGroup {

    public RightStartRightScaleLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(210,.75,true,-5,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 200));
    	addSequential(new DriveToPosition(30,.5,true,-45, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new TurnToAngle(50));
    	addSequential(new DriveToPosition(-190,.8,true,100, false,true));
    	addSequential(new DriveToPosition(-55,.4,true,145, true,true));
    	addSequential(new DriveToPosition(15,.4,true,145, true,false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition, 100));
    	addSequential(new DriveToPosition(12,.5,true,143, true,false));
    	addSequential(new RunIntake(true));
    }
}
