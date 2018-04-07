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
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-15,.4,true,-120,true,true,true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-65,.6,true,-180,false,true,true));
    	addSequential(new DriveToPosition(15,.6,true,-142,false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.aboveBump));
    	addSequential(new DriveToPosition(-190,.6,true,-255,false,true));
    	//addSequential(new DriveToPosition(-10,.6,true,-310,false,true));
    }
}
