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
public class LeftStartRightSwitch extends CommandGroup {
    public LeftStartRightSwitch() {
    	//addSequential(new FollowTrajectory(waypoints.oneCubeRightSwitchLeftStart, false, false, "one_Cube_right_switch_left_start.traj"));
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(160,.6,true,0,true,false));
    	addSequential(new DriveToPosition(260,.6,true,80,true,false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new TurnToAngle(210));
    	addSequential(new DriveToPosition (25,.5,false,210,false,false));
    	addSequential(new RunIntake(true));
    }
}
