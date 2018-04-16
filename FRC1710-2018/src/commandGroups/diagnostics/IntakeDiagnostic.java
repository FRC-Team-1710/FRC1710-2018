package commandGroups.diagnostics;

import org.usfirst.frc.team1710.robot.RobotMap;

import commands.RunMotor;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeDiagnostic extends CommandGroup {

    public IntakeDiagnostic() {
    	 /*addSequential(new RunMotor(RobotMap.intakeL, 1,1000));
    	 addSequential(new RunMotor(RobotMap.intakeR,1,1000));*/
    }
}
