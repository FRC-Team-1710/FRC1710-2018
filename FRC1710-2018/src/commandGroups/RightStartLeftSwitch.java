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
    	//addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(212,1,true,0,false));
    	addSequential(new DriveToPosition(260,1,true,-92,false));
    	addSequential(new DriveToPosition (18,.5,false,-220,false));
    	//addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	//addSequential(new RunIntake(true));
    }
}
