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
    	addSequential(new DriveToPosition(220, .75, true,2,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(20,.6,true,-55, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.aboveBump, 500));
    	addSequential(new TurnToAngle(10));
    	addSequential(new DriveToPosition(-19,.6,true,50, false,true));
    	addSequential(new DriveToPosition(-200,.8,true,100, false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new DriveToPosition(-35,.4,true,150, true,true));
    	addSequential(new DriveToPosition(15,.4,true,150, true,false));
    	addSequential(new RunIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition, 100));
    	addParallel(new DriveToPosition(12,.5,true,143, true,false));
    	addSequential(new RunIntake(true));
    }
}
