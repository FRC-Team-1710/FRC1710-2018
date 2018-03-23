package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectory.FollowTrajectoryFromFile;

/**
 *
 */
public class MiddleToLeftSwitch extends CommandGroup {

    public MiddleToLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(45,.45,true,-70,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(90,.6,true,0,true,false));
    	addSequential(new RunIntake(true));
    }
}
