package trajectory;

import org.usfirst.frc.team1710.robot.Constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

public class PathManager {
	
	public static Trajectory switchLeftLeft;
	
	public static void calculatePaths() {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC,
			Trajectory.Config.SAMPLES_HIGH, Constants.dt, Constants.maxV,
				Constants.maxAccel, Constants.maxJerk);
		switchLeftLeft = Pathfinder.generate(waypoints.leftStartLeftSwitch, config);
	}
	
}
