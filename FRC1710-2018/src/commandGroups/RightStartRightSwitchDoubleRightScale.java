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
    	addSequential(new DriveToPosition(Constants.wallToScale, .75, true,-2,false,false));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new DriveToPosition(10, .3, true,-40,false,false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new TurnToAngle(-145));
    	addSequential(new DriveToPosition(35,.5,true,-145, false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-35,.5,true,-205, false,true));
    	addSequential(new DriveToPosition(15,.5,true,-155, false,false,true));
    	addSequential(new RunIntake(false));
    	addParallel(new PitchIntake(Constants.wristUp));
    	addSequential(new DriveToPosition(-80,.6,true, -85, false,true));
    	addParallel(new PitchIntake(Constants.wristDown));
    	addParallel(new DriveToPosition(45, .4, true,-35,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new RunIntake(true));
    }
}
