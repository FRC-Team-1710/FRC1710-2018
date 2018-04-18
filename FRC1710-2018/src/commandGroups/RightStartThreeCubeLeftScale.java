package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.AutoTimer;
import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TurnToAngle;
import commands.Wait;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartThreeCubeLeftScale extends CommandGroup {

    public RightStartThreeCubeLeftScale() {
    	addSequential(new DriveToPosition(Constants.wallToSwitchCorner,.75,true,0,false,false));
    	addSequential(new DriveToPosition(Constants.switchCornerToScalePlacepos,.8,true,-90,false,false));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new TurnToAngle(20));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(42,.3,true,20,false,false,true));
    	addParallel(new PitchIntake(Constants.wristLaunch));
    	addSequential(new RunIntake(true,100));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new DriveToPosition(-35, .4, true,20,false,true));
    	addSequential(new DriveToPosition(20, .4, true,130,false,false,true));
    	addSequential(new RunIntake(false));
    	addParallel(new PitchIntake(Constants.wristUp));
    	addSequential(new DriveToPosition(-20, .4, true,140,false,true));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(25, .4, true,20,false,false,true));
    	addSequential(new RunIntake(true));
    	addSequential(new AutoTimer());
    }
}
