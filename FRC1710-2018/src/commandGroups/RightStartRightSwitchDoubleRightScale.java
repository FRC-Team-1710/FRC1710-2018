package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.AutoTimer;
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
public class RightStartRightSwitchDoubleRightScale extends CommandGroup {

    public RightStartRightSwitchDoubleRightScale() {
    	//addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(260, .75, true,0,false,false));
    	addSequential(new TurnToAngle(-40));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 200));
    	addSequential(new TurnToAngle(-150));
    	addSequential(new DriveToPosition(65,.5,true,-150, false,false,true));
    	//addSequential(new RunIntake(false));
    	addSequential(new Wait(500));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	//addSequential(new RunIntake(true));
    	addSequential(new Wait(500));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 500));
    	addSequential(new DriveToPosition(-30,.5,true,-220, false,true,true));
    	addSequential(new DriveToPosition(30,.5,true,-170, false,false,true));
    	//addSequential(new RunIntake(false));
    	addSequential(new Wait(500));
    	addSequential(new DriveToPosition(-80,.6,true,-130, false,true,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new TurnToAngle(-50));
    	//addSequential(new RunIntake(true));
    	addSequential(new Wait(500));
    	addSequential(new AutoTimer());
    }
}
