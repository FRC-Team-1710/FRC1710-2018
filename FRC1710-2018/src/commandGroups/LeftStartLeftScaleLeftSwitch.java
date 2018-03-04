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
public class LeftStartLeftScaleLeftSwitch extends CommandGroup {

    public LeftStartLeftScaleLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 2000));
    	addSequential(new DriveToPosition(275,.6,true,0,false,false));
    	addSequential(new TurnToAngle(40));
    	addSequential(new RunIntake(true));
    	addSequential(new TurnToAngle(120));
    	//addSequential(new DriveToPosition (30,.6,false,0,true, true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(85,.4,true, 190, true, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(5,.4,false,180,true,false));
    	addSequential(new RunIntake(true));
    }
}
