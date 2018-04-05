package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.AutoTimer;
import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.Wait;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleToLeftSwitchRightScale extends CommandGroup {

    public MiddleToLeftSwitchRightScale() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(50,.45,true,-70,true,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(90,.45,true,0,false,false));
    	//addSequential(new RunIntake(true));
    	addSequential(new Wait(500));
    	addSequential(new DriveToPosition(-80,.45,true,-20,false,false,true));
    	
    	
    	addSequential(new AutoTimer());
    }
}
