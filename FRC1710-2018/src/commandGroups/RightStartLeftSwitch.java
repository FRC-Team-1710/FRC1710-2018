package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TurnToAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartLeftSwitch extends CommandGroup {

    public RightStartLeftSwitch() {
    	addParallel(new PitchIntake(false));
    	addSequential(new DriveToPosition(170,.75,true,0,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.aboveBump));
    	addSequential(new DriveToPosition(240,.8,true,-83,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new TurnToAngle(-170));
    	addSequential(new DriveToPosition (50,.8,false,-200,true,false));
    	addSequential(new RunIntake(true));
    }
}
