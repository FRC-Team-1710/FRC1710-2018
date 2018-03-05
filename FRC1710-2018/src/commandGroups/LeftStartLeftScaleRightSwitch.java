package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftStartLeftScaleRightSwitch extends CommandGroup {

    public LeftStartLeftScaleRightSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(200,.75,true,0,true,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 200));
    	addSequential(new DriveToPosition(35,.5,true,-42, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new DriveToPosition(80,.3,true,-270, true,true));

    }
}
