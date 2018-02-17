package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.FollowTrajectory;
import trajectory.FollowTrajectoryFromFile;
import trajectory.waypoints;

/**
 *
 */
public class RightStartRightSwitch extends CommandGroup {

    public RightStartRightSwitch() {
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new FollowTrajectoryFromFile("right_start_right_switch.traj", false, false));
    	
    	addSequential(new RunIntake(true));
    }
}
