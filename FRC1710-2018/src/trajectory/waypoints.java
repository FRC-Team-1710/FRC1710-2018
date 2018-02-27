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
			new Waypoint(3,-1.75,0),
			new Waypoint(5,0,Pathfinder.d2r(55)),
			new Waypoint(6,2,Pathfinder.d2r(100)),
	};
	
	//split into to sets of waypoints for final part, play with speed
	public static Waypoint[] leftStartRightSwitch = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(3,-1.75,0),
			new Waypoint(5,0,Pathfinder.d2r(55)),
			new Waypoint(6,2,Pathfinder.d2r(100)),
	};
	
	public static Waypoint[] leftStartLeftSwitch = new Waypoint[] {
			new Waypoint(2.3, 0,Pathfinder.d2r(0)),
			//-45 is south east lmao
			new Waypoint(2.9,-.2,Pathfinder.d2r(-90)),
	};
	
	public static Waypoint[] backupAndGetInViewOfCubesLeft = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1,1,0),
	};
	
	public static Waypoint[] turnAround = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1,0,Pathfinder.d2r(120)),
	};
	
	public static Waypoint[] turnAroundTwoAndScale = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1,-1,Pathfinder.d2r(-135)),
	};
	
	public static Waypoint[] oneCubeLeftSwitchLeftStart = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(2,1,0),
	};
	
	public static Waypoint[] oneCubeRightSwitchRightStart = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(2,-1, 0),
	};
	
	public static Waypoint[] leftScaleToLeftSwitch = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1, -.5 ,0),
	};
	
	public static Waypoint[] rightScaleToRightSwitch = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1.5, -1 ,0),
	};
	
	public static Waypoint[] oneCubeRightSwitchLeftStart= new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(2,-.15,0),
			new Waypoint(4,.3,Pathfinder.d2r(-90)),
			//new Waypoint(0,5,0),
	};
	
	public static Waypoint[] middleToRightSwitch = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1.5, 2.5 ,0),
	};
	
	public static Waypoint[] middleToLeftSwitch = new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1.5, 2.5 ,0),
	};
	
}
