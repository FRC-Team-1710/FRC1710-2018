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
    	//addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(260, .75, true,0,false,false));
    	addSequential(new TurnToAngle(-40));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 200));
    	addSequential(new TurnToAngle(10));
    	addSequential(new DriveToPosition(-35,.5,true,10, false,true,true));
    	addSequential(new DriveToPosition(-175,.7,true,90, true,true,true));
    	addSequential(new DriveToPosition(-35,.4,true,150, false,true));
    	addSequential(new DriveToPosition(20,.4,true,150, false,false,true));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition, 200));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(true))
    	
    }
}
