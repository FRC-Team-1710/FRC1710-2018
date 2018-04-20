package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TurnToAngle;
import commands.Wait;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartRightScaleRightSwitch extends CommandGroup {

    public RightStartRightScaleRightSwitch() {
    	addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(Constants.wallToScale, .75, true,0,false,false));
    	//addParallel(new PitchIntake(Constants.wristDown));
    	//addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(10, .3, true,-40,false,false,true));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-15, .4, true,-40,false,true));
    	addSequential(new TurnToAngle(-130));
    	addSequential(new DriveToPosition(32,.5,true,-131, false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(8,.5,true,-130, false,false));
    	addSequential(new RunIntake(true));
    }
}
