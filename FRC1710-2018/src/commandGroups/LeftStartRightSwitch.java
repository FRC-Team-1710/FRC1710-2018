package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftStartRightSwitch extends CommandGroup {

    public LeftStartRightSwitch() {
    	//addSequential(new FollowTrajectory(waypoints.oneCubeRightSwitchLeftStart, false, false, "one_Cube_right_switch_left_start.traj"));
    	addSequential(new DriveToPosition(42000,.75,false,0,false));
    	addSequential(new DriveToPosition(31700,1,false,90,false));
    	//addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition (8000,.5,false,180,true));
    	//addSequential(new RunIntake());
    }
}
