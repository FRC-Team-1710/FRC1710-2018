package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.FollowTrajectory;
import trajectory.FollowTrajectoryFromFile;
import trajectory.waypoints;

/**
 *
 */
public class RightStartRightSwitch extends CommandGroup {

    public RightStartRightSwitch() {
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(35,1,false,-10,true));
    	addParallel(new DriveToPosition(10,1,false,-40,false));
    	addSequential(new PitchIntake(false));
    	addSequential(new RunIntake(true));
    }
}
