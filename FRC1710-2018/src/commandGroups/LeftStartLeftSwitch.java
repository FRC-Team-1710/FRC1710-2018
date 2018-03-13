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
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(65,.6,true,0,true,false));
    	addParallel(new DriveToPosition(25,.6,true,65,false,false));
    	addSequential(new PitchIntake(false));
    	addSequential(new RunIntake(true));
    }
}
