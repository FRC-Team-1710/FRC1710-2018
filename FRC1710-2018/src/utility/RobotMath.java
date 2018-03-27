package utility;

public class RobotMath {

	public static boolean isInRange(double valOne, double valTwo, double distance) {
		if(Math.abs(Math.abs(valOne) - Math.abs(valTwo)) <= distance) {
			return true;
		}else {
			return false;
		}
	}
}
