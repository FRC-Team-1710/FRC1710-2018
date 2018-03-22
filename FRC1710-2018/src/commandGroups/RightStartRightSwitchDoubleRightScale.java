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
    	addSequential(new PitchIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(220, .75, true,-1,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new TurnToAngle(-35));
    	addSequential(new DriveToPosition(20,.6,false,-35, true,false));
    	addSequential(new RunIntake(true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake, 250));
    	addSequential(new DriveToPosition(-15,.3,true,-35, true,true));
    	addSequential(new DriveToPosition(-20,.8,true,35, true,true));
    	addSequential(new DriveToPosition(-90,.8,true,90, false,true));
    	addSequential(new DriveToPosition(-25,.8,true,120, false,true));
    	addSequential(new TrackCube(false));
    	//2nd cube to switch
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addParallel(new DriveToPosition(8,.4,false,150, false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-15,.4,true,180, true,true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(-10,.4,true,150, true,true));
    	addSequential(new DriveToPosition(-20,.4,true,190, false,true));
    	addSequential(new TrackCube(false));
    	//3rd cube to scale
    	addSequential(new DriveToPosition(-15,.5,true,230, true,true));
    	addSequential(new DriveToPosition(-70,.8,true,270, true,true));
    	addSequential(new DriveToPosition(-25,.8,true,360, false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh, 250));
    	addSequential(new DriveToPosition(70,.3,true,330, false,false));
    	addSequential(new RunIntake(true, 500));
    }
}
