package commandGroups.diagnostics;

import org.usfirst.frc.team1710.robot.RobotMap;

import commands.RunMotor;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveDiagnostic extends CommandGroup {

    public DriveDiagnostic() {
    	 addSequential(new RunMotor(RobotMap.L1,.75,1000, 3, "left leader"));
    	 addSequential(new RunMotor(RobotMap.L2,.75,1000, 2, "left follower 1"));
    	 addSequential(new RunMotor(RobotMap.L3,.75,1000, 3, "left folower2" ));
    	 addSequential(new RunMotor(RobotMap.R1,.75,1000, 3, "right leader"));
    	 addSequential(new RunMotor(RobotMap.R2,.75,1000, 3, "right follower 1"));
    	 addSequential(new RunMotor(RobotMap.R3,.75,1000, 3, "right follower 2"));
    }
}
