package trajectory;

import org.usfirst.frc.team1710.robot.Constants;

import commands.MoveLiftToPosition;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class trajectoryTestCGroup extends CommandGroup {

    public trajectoryTestCGroup() {
    	//addParallel(new MoveLiftToPosition(Constants.switchPosition));
    	addSequential(new FollowTrajectoryFromFile("left_to_left_switch.traj",false, false));
    	//outtakes onto switch and backs up so that it can see cubes
    	addParallel(new RunIntake());
    	//path that backs away to get cubes in view- rename this path but it uses switch to scale waypoints
    	addSequential(new FollowTrajectoryFromFile("switch_to_scale.traj", false, true));
    	addSequential(new TrackCube(true));
    }
}
