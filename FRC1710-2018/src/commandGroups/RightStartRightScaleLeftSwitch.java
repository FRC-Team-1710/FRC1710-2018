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
public class RightStartRightScaleLeftSwitch extends CommandGroup {

    public RightStartRightScaleLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(Constants.wallToScale, .75, true,0,false,false));
    	//addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(10, .3, true,-40,false,false,true));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-25,.5,true,10, false,true,true));
    	addSequential(new DriveToPosition(-168,.7,true,90, true,true,true));
    	addSequential(new DriveToPosition(-38,.4,true,150, false,true));
    	addSequential(new DriveToPosition(8,.4,true,150, false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new RunIntake(true));
    	
    }
}
