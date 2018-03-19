package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.RunIntake;
import commands.TrackCube;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeCubeRightScale extends CommandGroup {

    public ThreeCubeRightScale() {
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(220, .75, true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new TurnToAngle(-35));
    	addSequential(new DriveToPosition(20,.6,false,-35, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 250));
    	addSequential(new DriveToPosition(-15,.3,true,-35, true,true));
    	addSequential(new DriveToPosition(-25,.6,true,35, true,true));
    	addSequential(new DriveToPosition(-85,.6,true,90, true,true));
    	addSequential(new DriveToPosition(-10,.6,true,120, false,true));
    	addSequential(new TrackCube(false));
    	addSequential(new DriveToPosition(-10,.6,true,90, false,true));
    	addSequential(new DriveToPosition(80,.6,true,90, false,false));
    	addSequential(new DriveToPosition(80,.6,true,-5, false,false));
    	addSequential(new TurnToAngle(-35));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(35,.4,false,-35, true,false));
    	addSequential(new RunIntake(true));
    }
}
