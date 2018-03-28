package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.RunIntake;
import commands.SweepingIntake;
import commands.TrackCube;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Testing extends CommandGroup {

    public Testing() {
    	addSequential(new DriveToPosition(60, 0.75, true, 0,false,false));
    	//should turn left and intake
    	//addSequential(new SweepingIntake(false));
    }
}
