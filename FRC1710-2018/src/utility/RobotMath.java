package utility;

public class RobotMath {

	public static boolean isInRange(double valOne, double valTwo, double distance) {
		if(valOne >= valTwo - distance && valOne <= valTwo + distance) {
			return true;
		}else {
			return false;
		}
	}
}
