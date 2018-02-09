package trajectory;

import org.usfirst.frc.team1710.robot.Constants;

import commands.MoveLiftToPosition;
import commands.TrackCube;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class trajectoryTestCGroup extends CommandGroup {

    public trajectoryTestCGroup() {
    	addSequential(new FollowTrajectory(waypoints.safePoints, false, false, "simple.traj"));
    	addSequential(new TrackCube(true));
    }
}
