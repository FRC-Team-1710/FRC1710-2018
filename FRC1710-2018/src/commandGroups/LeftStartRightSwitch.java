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
public class LeftStartRightSwitch extends CommandGroup {
    public LeftStartRightSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(145,.75,true,0,false,false));
    	addSequential(new DriveToPosition(275,.75,true,90,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new TurnToAngle(180));
    	addSequential(new DriveToPosition (60,.8,false,220,true,false));
    	addSequential(new RunIntake(true));
    }
}
