package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.FollowTrajectoryFromFile;

/**
 *
 */
public class LeftScaleToLeftSwitch extends CommandGroup {

    public LeftScaleToLeftSwitch() {
    	//addSequential(new FollowTrajectoryFromFile("left_scale_to_left_switch.traj", false, false));
    	addSequential(new DriveToPosition(12000, .75, false,-20,false,false));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new RunIntake(true));
    }
}
