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
public class RightStartLeftSwitch extends CommandGroup {

    public RightStartLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(185,.75,true,0,false,false));
    	addSequential(new DriveToPosition(210,.75,true,-90,false,false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition (55,.4,true,-225,true,false,true));
    	addSequential(new RunIntake(true));
    }
}
