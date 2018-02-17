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
public class LeftStartLeftSwitch extends CommandGroup {

    public LeftStartLeftSwitch() {
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new FollowTrajectoryFromFile("one_Cube_Left_Switch.traj", false, false));
    	
    	addSequential(new RunIntake(false));
    }
}
