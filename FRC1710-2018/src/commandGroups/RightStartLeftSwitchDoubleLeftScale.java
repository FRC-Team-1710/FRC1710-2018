package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.AutoTimer;
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
public class RightStartLeftSwitchDoubleLeftScale extends CommandGroup {

    public RightStartLeftSwitchDoubleLeftScale() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(Constants.wallToSwitchCorner,.75,true,0,false,false));
    	addSequential(new DriveToPosition(Constants.switchCornerToScalePlacepos,.8,true,-90,false,false));
    	//addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new TurnToAngle(20));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(38,.3,true,20,false,false,true));
    	//addParallel(new PitchIntake(Constants.wristLaunch));
    	addSequential(new RunIntake(true,100));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	//addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new DriveToPosition(-30, .4, true,20,false,true));
    	addSequential(new DriveToPosition(20, .4, true,120,false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake,250));
    	addSequential(new DriveToPosition(-60, .4, true,200,false,true));
    	addSequential(new DriveToPosition(10, .4, true,170,false,false));
    	addSequential(new RunIntake(false));
    	
    	//3rd to scale
    	//addParallel(new PitchIntake(Constants.wristUp));
    	addSequential(new DriveToPosition(-70, .4, true,90,false,true));
    	//addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(35, .4, true,20,false,false,true));
    	addSequential(new RunIntake(true));
    	addSequential(new AutoTimer());
    }
}
