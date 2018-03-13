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
public class LeftStartDoubleScaleLeft extends CommandGroup {

    public LeftStartDoubleScaleLeft() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(212, .75, true,-2,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(20,.6,true,55, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 300));
    	addSequential(new DriveToPosition(-15,.3,true,138, true,true));
    	addSequential(new DriveToPosition(48,.4,true, 152, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-30,.4,true, -30, true, true));
    	addParallel(new DriveToPosition(15,.4,false, -30, true, false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    }
}
