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
public class RightStartRightScaleRightSwitch extends CommandGroup {

    public RightStartRightScaleRightSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(200,.6,true,0,true,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 200));
    	addSequential(new DriveToPosition(35,.4,true,-42, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new DriveToPosition(20,.3,true,-115, true,true));
    	addSequential(new DriveToPosition(15,.35,true, -140, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition), 100);
    	addSequential(new DriveToPosition(8,.4,false,-140,true,false));
    	addSequential(new RunIntake(true));
    }
}
