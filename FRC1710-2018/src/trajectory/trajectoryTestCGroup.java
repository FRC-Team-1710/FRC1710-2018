package trajectory;

import org.usfirst.frc.team1710.robot.Constants;

import commands.MoveLiftToPosition;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class trajectoryTestCGroup extends CommandGroup {

    public trajectoryTestCGroup() {
    	addParallel(new MoveLiftToPosition(Constants.scaleLow));
    	addSequential(new FollowTrajectory(waypoints.safePoints));
    	addSequential(new MoveLiftToPosition(Constants.intake));
    }
}
