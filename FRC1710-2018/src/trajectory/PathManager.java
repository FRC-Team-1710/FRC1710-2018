package trajectory;

import java.io.File;

import org.usfirst.frc.team1710.robot.Constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class PathManager {
	
	public static Trajectory switchLeftLeft;
	
	public static void calculatePaths() {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC,
			Trajectory.Config.SAMPLES_HIGH, Constants.dt, Constants.maxV,
				Constants.maxAccel, Constants.maxJerk);
		switchLeftLeft = Pathfinder.generate(waypoints.leftStartLeftSwitch, config);
	}
	
	public static void writePathToFile(Waypoint[] waypoints, String fileName) {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, Constants.dt, Constants.maxV, Constants.maxAccel, 60);
		Trajectory trajectory = Pathfinder.generate(waypoints, config);
		File file = new File(fileName);
		Pathfinder.writeToFile(file, trajectory);
	}
	
	public static Trajectory readTrajFromFile(String fileName) {
		File file = new File(fileName);
		return Pathfinder.readFromFile(file);
	}
	
}
