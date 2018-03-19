package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.RunIntake;
import commands.TrackCube;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Testing extends CommandGroup {

    public Testing() {
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(60, 0.75, false, 0,false,false));
    	addSequential(new DriveToPosition(-100, 0.75, false, 180,false,true));
    	addSequential(new DriveToPosition(160, 0.75, false, 180,false,false));
    }
}
