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
public class LeftStartLeftSwitch extends CommandGroup {

    public LeftStartLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(90,.6,true,-3,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(30,.4,true,60,false,false));
    }
}
