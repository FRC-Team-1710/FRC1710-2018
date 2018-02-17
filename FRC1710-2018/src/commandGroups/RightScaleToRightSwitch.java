package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.FollowTrajectoryFromFile;

/**
 *
 */
public class RightScaleToRightSwitch extends CommandGroup {

    public RightScaleToRightSwitch() {
    	addSequential(new FollowTrajectoryFromFile("right_scale_to_right_switch.traj", false, false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new RunIntake(true));
    }
}
