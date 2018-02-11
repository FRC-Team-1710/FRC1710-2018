package trajectory;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class trajectoryTestCGroup extends CommandGroup {

    public trajectoryTestCGroup() {
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new FollowTrajectoryFromFile("left_to_left_switch.traj",false, false));
    	//outtakes onto switch and backs up so that it can see cubes
    	addParallel(new RunIntake());
    	//path that backs away to get cubes in view- rename this path but it uses switch to scale waypoints
    	addSequential(new FollowTrajectoryFromFile("switch_to_scale.traj", false, true));
    	//tracks a cube behind the switch, TrackCube handles intaking
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TrackCube(true));
    	//TODO: backup and 180 (1st path), then move forward and place on the scale (2nd path)
    }
}
