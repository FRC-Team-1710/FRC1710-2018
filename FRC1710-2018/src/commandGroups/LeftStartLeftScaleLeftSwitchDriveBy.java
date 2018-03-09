package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftStartLeftScaleLeftSwitchDriveBy extends CommandGroup {

    public LeftStartLeftScaleLeftSwitchDriveBy() {
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition, 200));
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(80, .75, false, 3,true,false));
    	//at the end of this the robot should be facing into the switch
    	addSequential(new DriveToPosition(40, .75, false, 45,false,false));
    	//spins back around to go intake a cube
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 750));
    	addParallel(new RunIntake(true));
    	addSequential(new DriveToPosition(-80, .75, false, 180,true,true));
    	addSequential(new DriveToPosition(-25, .75, false, 215,true,true));
    	addSequential(new DriveToPosition(-40, .75, false, 145,false,true));
    	addSequential(new DriveToPosition(25, .75, false, 145,false,false));
    	addSequential(new RunIntake(false));
    	//cube in. back up and turn around to place on the scale
    	addSequential(new DriveToPosition(-60, .75, false, 110,false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 750));
    	addSequential(new DriveToPosition(60, .75, false, 0,false,false));
    	addSequential(new RunIntake(true));
    }
}
