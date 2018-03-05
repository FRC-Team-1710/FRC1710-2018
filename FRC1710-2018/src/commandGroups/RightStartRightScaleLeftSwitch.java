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
    	addSequential(new DriveToPosition(200,.75,true,0,true,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 200));
    	addSequential(new DriveToPosition(30,.5,true,-45, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new TurnToAngle(33));
    	addSequential(new DriveToPosition(140,.8,true,95, true,true));
    	//addSequential(new DriveToPosition(2,.4,true,145, true,true));
    	addSequential(new DriveToPosition(20,.4,true,145, true,false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition, 100));
    	addSequential(new DriveToPosition(12,.5,true,143, true,false));
    	addSequential(new RunIntake(true));
    }
}
