package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import commands.Wait;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartRightSwitchLeftScale extends CommandGroup {

    public RightStartRightSwitchLeftScale() {
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	//addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(100,.5,true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(30,.35,true,-60,false,false));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-20,.4,true,-120,true,true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-40,.6,true,-180,false,true));
    	addSequential(new DriveToPosition(-200,.6,true,-270,true,true,true));
    	addSequential(new DriveToPosition(-30,.6,true,-180,false,true));
    }
}
