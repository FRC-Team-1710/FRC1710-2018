package trajectory;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class waypoints {
	//-y goes left, +y goes right
	//-x goes back, +x goes forward
	//-heading goes left, +heading goes right
	public static Waypoint[] teshhtPoints = new Waypoint[] {
		new Waypoint(-4, -1, Pathfinder.d2r(-45)),
		new Waypoint(-2,-2,0),
		new Waypoint(-4,0,0),
	};
	
	public static Waypoint[] forwardLeft = new Waypoint[] {
			new Waypoint(1, 0, 0),
			new Waypoint(2, 1, Pathfinder.d2r(45)),
	};
	
	public static Waypoint[] backRight = new Waypoint[] {
			new Waypoint(1, 0, 0),
			new Waypoint(2, -1, Pathfinder.d2r(-45)),
	};
	
	public static Waypoint[] testPoints = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1,-2,0),
			new Waypoint(4,0,Pathfinder.d2r(-90)),
	};
	
	public static Waypoint[] leftStartLeftSwitch = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1.5,-.4,Pathfinder.d2r(-10)),
	};
	
	//split into to sets of waypoints for final part, play with speed
	public static Waypoint[] leftStartRightSwitchPart1 = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(3,-1.75,0),
			new Waypoint(5,0,Pathfinder.d2r(55)),
			new Waypoint(6,2,Pathfinder.d2r(100)),
	};
	
	public static Waypoint[] safePoints = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1,1,0),
			new Waypoint(3,0,0),
	};
	
}
