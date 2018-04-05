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
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(95,.6,true,-10,false,false));
    	addParallel(new DriveToPosition(32,.6,true,70,false,false,true));
    	addSequential(new RunIntake(true), 500);
    }
}
