package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartLeftSwitch extends CommandGroup {

    public RightStartLeftSwitch() {
    	//addSequential(new FollowTrajectory(waypoints.oneCubeRightSwitchLeftStart, false, false, "one_Cube_right_switch_left_start.traj"));
    	addSequential(new DriveToPosition(42000,.75,false));
    	addSequential(new TurnToAngle (-90));
    	addSequential(new DriveToPosition(31700,1,false));
    	addSequential(new TurnToAngle (-90));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition (8000,.5,false));
    	//addSequential(new RunIntake());
    }
}
