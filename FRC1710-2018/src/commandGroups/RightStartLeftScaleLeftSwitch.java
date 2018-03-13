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
public class RightStartLeftScaleLeftSwitch extends CommandGroup {

    public RightStartLeftScaleLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(165,.75,true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.aboveBump));
    	addSequential(new DriveToPosition(195,.8,true,-85,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(40,.5,false,10,false,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new TurnToAngle(-115));
    	addSequential(new DriveToPosition(-15,.3,true,-145, true,true));
    	addSequential(new DriveToPosition(20,.4,true, -160, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition), 100);
    	addSequential(new DriveToPosition(10,.4,false,-165,true,false));
    	addSequential(new RunIntake(true));
    }
}
