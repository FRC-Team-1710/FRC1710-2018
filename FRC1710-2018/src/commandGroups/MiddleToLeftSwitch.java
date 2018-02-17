package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.FollowTrajectoryFromFile;

/**
 *
 */
public class MiddleToLeftSwitch extends CommandGroup {

    public MiddleToLeftSwitch() {
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new FollowTrajectoryFromFile("middle_to_left_switch.traj", false, false));
    	addSequential(new RunIntake(true));
    }
}
