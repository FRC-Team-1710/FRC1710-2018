package commandGroups;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import commands.TrackCube;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightStartRightSwitchLeftScale extends CommandGroup {

    public RightStartRightSwitchLeftScale() {
    	addSequential(new PitchIntake(false));
    	addParallel(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new DriveToPosition(70,.6,true,0,false,false));
    	addParallel(new DriveToPosition(30,.6,true,-60,false,false));
    	addSequential(new RunIntake(true), 200);
    	addParallel(new ChangeLiftSetpoint(Constants.intake,250));
    	addSequential(new DriveToPosition(-15,.6,true,-100,true,true));
    	addSequential(new DriveToPosition(-40,.6,true,-180,false,true));
    	addSequential(new DriveToPosition(-20,.6,true,-135,false,true));
    	addSequential(new TrackCube(true));
    	addParallel(new ChangeLiftSetpoint(Constants.aboveBump));
    	addSequential(new DriveToPosition(-25,.6,true,-90,false,true));
    	addParallel(new ChangeLiftSetpoint(Constants.intake,750));
    	addSequential(new DriveToPosition(200,.6,true,-90,false,false));
    	addParallel(new ChangeLiftSetpoint(Constants.scaleHigh));
    	addSequential(new DriveToPosition(30,.3,true,-20,false,false));
    	addParallel(new RunIntake(true), 500);
    	addSequential(new DriveToPosition(40,.3,true,20,false,false));
    }
}
