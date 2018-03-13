package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.FollowTrajectoryFromFile;

/**
 *
 */
public class MiddleToRightSwitch extends CommandGroup {

    public MiddleToRightSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(40,.6,false,75,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(90,.8,false,0,true,false));
    	addSequential(new RunIntake(true));
    }
}
