package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartLeftSwitch extends CommandGroup {

    public RightStartLeftSwitch() {
    	//addSequential(new FollowTrajectory(waypoints.oneCubeRightSwitchLeftStart, false, false, "one_Cube_right_switch_left_start.traj"));
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(185,.75,true,0,false));
    	addSequential(new DriveToPosition(200,.75,true,-90,false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition (8,.5,false,-245,false));
    	addSequential(new RunIntake(true));
    }
}
