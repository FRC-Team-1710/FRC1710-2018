package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Testing extends CommandGroup {

    public Testing() {
    	addSequential(new DriveToPosition(80, 0.7, false, 0,false,false));
    	//addSequential(new DriveToPosition(80, 0.7, false, 90,false,false));
    	//addSequential(new DriveToPosition(-40, 0.7, false, 0,false,true));
    }
}
