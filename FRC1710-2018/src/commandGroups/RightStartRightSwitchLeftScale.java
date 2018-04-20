package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.AutoTimer;
import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.ResetEncoders;
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
    	addParallel(new PitchIntake(false));

    	//addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new DriveToPosition(90,.8,true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(35,.35,true,-60,false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-10,.4,true,-120,true,true,true));
    	addParallel(new DriveToPosition(-83,.6,true,-180,false,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(12,.4,true,-145,false,false,true));
    	addSequential(new RunIntake(false));
    	addParallel(new PitchIntake(true));
    	//addParallel(new PitchIntake(Constants.wristUp));
    	addSequential(new DriveToPosition(-5,.5,true,-180,true,true));
    	addSequential(new DriveToPosition(-215,.75,true,-270,true,true));
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(-20,.4,true,-320,false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(10,.4,true,-335,false,false));
    	//addParallel(new PitchIntake(Constants.wristDown));
    	addSequential(new DriveToPosition(25,.3,true,-335,false,false));
    	addSequential(new RunIntake(true));
    	/*addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    	//addParallel(new PitchIntake(Constants.wristLaunch));
    	addSequential(new RunIntake(true));
    	addSequential(new AutoTimer());*/
    }
}
