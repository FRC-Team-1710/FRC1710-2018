package trajectory;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class trajectoryTestCGroup extends CommandGroup {

    public trajectoryTestCGroup() {
    	addSequential(new FollowTrajectory(waypoints.testPoints));
    }
}
