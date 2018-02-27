package commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.CreateTrajectoryFile;
import trajectory.waypoints;

/**
 *
 */
public class BuildPath extends CommandGroup {

    public BuildPath() {
    	/*addSequential(new CreateTrajectoryFile(waypoints.oneCubeRightSwitchRightStart, "right_start_right_switch.traj", false));
    	addSequential(new CreateTrajectoryFile(waypoints.rightScaleToRightSwitch, "right_scale_to_right_switch.traj", false));
    	addSequential(new CreateTrajectoryFile(waypoints.leftScaleToLeftSwitch, "left_scale_to_left_switch.traj", false));
    	addSequential(new CreateTrajectoryFile(waypoints.middleToLeftSwitch, "left_scale_to_left_switch.traj", false));
    	addSequential(new CreateTrajectoryFile(waypoints.middleToRightSwitch, "left_scale_to_right_switch.traj", false));*/
    	addSequential(new CreateTrajectoryFile(waypoints.leftStartLeftSwitch, "one_Cube_Left_Switch.traj", false));
    }
}
