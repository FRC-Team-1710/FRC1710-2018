package commandGroups.diagnostics;

import org.usfirst.frc.team1710.robot.RobotMap;

import commands.RunMotor;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftDiagnostic extends CommandGroup {

    public LiftDiagnostic() {
    	/*addSequential(new RunMotor(RobotMap.lift1, 1,1000));
    	addSequential(new RunMotor(RobotMap.lift2, 1,1000));*/
    }
}
