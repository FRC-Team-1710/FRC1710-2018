package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.FollowWaypoints;
import commands.RunIntake;
import commands.SweepingIntake;
import commands.TrackCube;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Testing extends CommandGroup {

    public Testing() {
    	/*addSequential(new DriveToPosition(100, 0.6, true, 0,false,false));
    	addSequential(new DriveToPosition(-60, 0.4, true, 0,false,true));
    	addSequential(new DriveToPosition(40, 0.4, true, 0,false,false));
    	addSequential(new DriveToPosition(-150, 0.4, true, 0,false,true));*/
    	
    	addSequential(new DriveToPosition(100, 0.6, true, 0, false,false));
    }
}
