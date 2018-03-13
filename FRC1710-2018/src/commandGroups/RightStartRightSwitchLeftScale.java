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
public class RightStartRightSwitchLeftScale extends CommandGroup {

    public RightStartRightSwitchLeftScale() {
    	addSequential(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(100,.6,true,0,false,false));
    	addParallel(new DriveToPosition(30,.6,true,-50,false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-50,.6,true,-210,false,false));
    	addSequential(new DriveToPosition(-200,.8,true,-270, false,true));
    	addSequential(new DriveToPosition(20,.5,false,-200, false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-20,.5,false,0, false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(40,.5,false,0, false,true));
    	addSequential(new RunIntake(true));
    }
}
