package commandGroups.diagnostics;

import org.usfirst.frc.team1710.robot.RobotMap;

import commands.RunMotor;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WristDiagnostic extends CommandGroup {

    public WristDiagnostic() {
    	 //addSequential(new RunMotor(RobotMap.wrist, 1,1000));
    }
}
