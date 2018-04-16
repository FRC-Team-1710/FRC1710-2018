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
    	addSequential(new DriveToPosition(Constants.wallToScale, .75, true,-2,false,false));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new DriveToPosition(10, .3, true,-40,false,false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TurnToAngle(-145));
    	addSequential(new DriveToPosition(30,.5,true,-145, false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-15,.5,true,-145, false,true));
    }
}
