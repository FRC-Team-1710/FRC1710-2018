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
    	//intake sucks wtf build team
    	addSequential(new RunIntake(true));
    	//path that backs away to get cubes in view- rename this path but it uses switch to scale waypoints
    	addParallel(new FollowTrajectoryFromFile("view_cubes_left.traj", false, true));
    	//tracks a cube behind the switch, TrackCube handles intaking
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	
    	addSequential(new TrackCube(true));
    	
    	//addSequential(new FollowTrajectoryFromFile("turnAround.traj", false, true));
    	//addSequential(new FollowTrajectory(waypoints.turnAroundTwoAndScale, false, false, "turn_around_two.traj"));
    	//TODO: backup and 180 (1st path), then move forward and place on the scale (2nd path)
    }
}
