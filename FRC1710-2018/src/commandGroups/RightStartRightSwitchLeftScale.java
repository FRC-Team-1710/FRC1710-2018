package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

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
    	//addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(100,.8,true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(30,.35,true,-70,false,false));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(-15,.4,true,-120,true,true,true));
    	addParallel(new DriveToPosition(-68,.55,true,-180,false,true,true));
    	addSequential(new ChangeLiftSetpoint(Constants.intake));
    	addSequential(new DriveToPosition(15,.55,true,-150,false,false,true));
    	addSequential(new RunIntake(false));
    	addSequential(new ResetEncoders());
    	addSequential(new ChangeLiftSetpoint(Constants.aboveBump));
    	addSequential(new DriveToPosition(-180,.5,true,-255,true,true));
    	//for some reason the robot direction flip flops here...
    	addSequential(new ResetEncoders());
    	addSequential(new DriveToPosition(-30,.4,true,-315,false,true));
    	//addSequential(new ChangeLiftSetpoint(Constants.scaleHigh));
    }
}
