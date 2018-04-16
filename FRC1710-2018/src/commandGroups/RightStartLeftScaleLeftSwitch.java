package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.ResetEncoders;
import commands.RunIntake;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartLeftScaleLeftSwitch extends CommandGroup {

    public RightStartLeftScaleLeftSwitch() {
    	addSequential(new DriveToPosition(Constants.wallToSwitchCorner,.75,true,0,false,false));
    	addSequential(new DriveToPosition(Constants.switchCornerToScalePlacepos,.8,true,-90,false,false));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new TurnToAngle(20));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(42,.3,true,20,false,false,true));
    	addParallel(new PitchIntake(Constants.wristLaunch));
    	addSequential(new RunIntake(true,100));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-10,.3,true,20,true,true));
    	addSequential(new TurnToAngle(-180,false));
    	addSequential(new ResetEncoders());
    	addSequential(new DriveToPosition(20,.4,true, -185, false, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(5,.2,true,-185,true,false));
    	addSequential(new RunIntake(true));
    }
}
