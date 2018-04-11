package commandGroups.diagnostics;

import org.usfirst.frc.team1710.robot.Constants;

import commands.ChangeLiftSetpoint;
import commands.DriveToPosition;
import commands.PitchIntake;
import commands.RunIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MasterDiagnostic extends CommandGroup {

    public MasterDiagnostic() {
    	addSequential(new PitchIntake(false));
    	addSequential(new ChangeLiftSetpoint(Constants.switchPosition));
    	addSequential(new RunIntake(true));
    	addSequential(new DriveToPosition(15,.5,false,0,false,false));
    }
}
