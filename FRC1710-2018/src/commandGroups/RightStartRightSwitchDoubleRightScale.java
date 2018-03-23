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
public class RightStartRightSwitchDoubleRightScale extends CommandGroup {

    public RightStartRightSwitchDoubleRightScale() {
    	//1st cube to scale
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(190, .75, true,0,false,false));
    	addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new TurnToAngle(-45));
    	addSequential(new DriveToPosition(25,.6,false,-35, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 250));
    	addSequential(new DriveToPosition(-20,.3,true,-35, true,true));
    	addSequential(new DriveToPosition(-25,.5,true,20, true,true));
    	addSequential(new DriveToPosition(-30,.5,true,90, false,true));
    	addSequential(new DriveToPosition(-10,.5,true,150, false,true));
    	addSequential(new TrackCube(false));
    	addSequential(new DriveToPosition(15,.5,true,120, false,true, true));
    	addSequential(new RunIntake(false));
    	//2nd cube to switch
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addParallel(new DriveToPosition(8,.4,false,150, false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-15,.4,true,180, true,true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-10,.4,true,150, true,true));
    	addSequential(new DriveToPosition(-20,.4,true,190, false,true));
    	addSequential(new TrackCube(false));
    	addSequential(new DriveToPosition(15,.5,true,180, false,true, true));
    	addSequential(new RunIntake(false));
    	//3rd cube to scale
    	addSequential(new DriveToPosition(-15,.5,true,230, true,true));
    	addSequential(new DriveToPosition(-70,.8,true,270, true,true));
    	addSequential(new DriveToPosition(-25,.8,true,360, false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 250));
    	addSequential(new DriveToPosition(70,.3,true,330, false,false));
    	addSequential(new RunIntake(true, 500));
    }
}
