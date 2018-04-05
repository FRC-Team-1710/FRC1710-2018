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
public class ThreeCubeRightScale extends CommandGroup {

    public ThreeCubeRightScale() {
    	//addParallel(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(260, .75, true,0,false,false));
    	addSequential(new TurnToAngle(-40));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 200));
    	addSequential(new TurnToAngle(-150));
    	addSequential(new DriveToPosition(60,.4,true,-150, false,false,true));
    	//addSequential(new RunIntake(false));
    	addSequential(new Wait(500));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(-60,.4,true,-150, false,true));
    	addSequential(new TurnToAngle(-60));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-10,.4,true,-60, false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 200));
    	addSequential(new TurnToAngle(-125));
    	addSequential(new DriveToPosition(90,.6,true,-125, false,false,true));
    	addSequential(new Wait(500));
    	//addSequential(new RunIntake(false));
    	addSequential(new DriveToPosition(-50,.6,true,-135, false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new TurnToAngle(-90));
    	//addSequential(new RunIntake(true));
    	addSequential(new AutoTimer());
    }
    
}
