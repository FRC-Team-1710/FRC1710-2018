package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartRightSwitchLeftScale extends CommandGroup {

    public RightStartRightSwitchLeftScale() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(90,.5,true,3,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(30,.35,true,-60,false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-60,.4,true,-60,false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake,250));
    	addSequential(new DriveToPosition(-140,.6,true,-180,true,true));
    	addSequential(new DriveToPosition(25,.6,true,-150,true,false));
    	addSequential(new DriveToPosition(30,.6,true,-160,false,false));
    	addSequential(new RunIntake(false));
    	/*addSequential(new DriveToPosition(-12,.4,true,-180,false,true));
    	addSequential(new DriveToPosition(-25,.6,true,-90,false,true));
    	addSequential(new DriveToPosition(200,.6,true,-90,false,false));
    	addSequential(new TurnToAngle(-20));
    	addParallel(new DriveToPosition(45,.3,true,-20,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new TurnToAngle(20));
    	addSequential(new DriveToPosition(30,.3,true,20,false,false));
    	addSequential(new RunIntake(true));*/
    }
}
