package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowWaypoints extends Command {

	double[][] _waypoints;
	double goalPos, goalHeading, currentPos, distanceCovered, x, y, speed, output;
	boolean _inHighGear;
	int i, driveState;
	
	
    public FollowWaypoints(double[][] waypoints, boolean inHighGear) {
    	_waypoints = waypoints;
    	_inHighGear = inHighGear;
    }

    protected void initialize() {
    	Drive.setShifters(_inHighGear);
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	i=0;
    }

    //20,20,.5
    protected void execute() {
    	x = _waypoints[i][0];
    	y = _waypoints[i][1];
    	speed = _waypoints[i][2];
    	
    	goalPos = Math.sqrt(Math.pow(x * Constants.ticksPerInch, 2) + Math.pow(y * Constants.ticksPerInch, 2)) + distanceCovered;
    	
    	if(x == 0) {
    		if(y < 0) {
    			goalHeading = -90;
    		} else {
    			goalHeading = 90;
    		}
    	} else {
    		if(x >= 0 && y >= 0) {
    			goalHeading = Math.atan((y/x));
    		} else if(x <= 0 && y >= 0) {
    			goalHeading = Math.atan((y/x)) + 180;
    		} else if(x <= 0 && y <= 0) {
    			goalHeading = Math.atan((y/x)) + 180;
    		} else if(x >= 0 && y <= 0) {
    			goalHeading = Math.atan((y/x)) + 360;
    		}
    	}
    	
    	currentPos = (Drive.getLeftPosition() + Drive.getRightPosition())/2;
    	
    	if(i == 0) {
    		if(output < speed) {
        		output = (Math.pow((currentPos/(goalPos*.5)),2) * speed) + .15;
    		} else {
    			output = speed;
    		}
    		driveState = 0;
    	} else if(i > 0) {
    		output = speed;
    		driveState = 1;
    	} else if(i == _waypoints.length){
    		output = (Math.pow((currentPos/goalPos), 2) - 1) * speed;
    		driveState = 2;
    	}
    	
    	if(Math.abs(currentPos-goalPos) < 250) {
    		distanceCovered += goalPos;
    		i++;
    	} else {
    		Drive.straightDriveTele(output, goalHeading, _inHighGear);
    	}
    	
    }

    protected boolean isFinished() {
        return i > _waypoints.length;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
