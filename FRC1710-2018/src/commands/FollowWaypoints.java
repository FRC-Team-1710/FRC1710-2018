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
	double goalPos, goalHeading, currentPos, distanceCovered, x, y, lastX, lastY, speed, output, _maxSpeed, slowStart;
	boolean _inHighGear, fixingHeading, needToFindNewStart, allDone, updateGoalPos, foundSlowStart;
	int i, driveState;
	
	
    public FollowWaypoints(double[][] waypoints, boolean inHighGear, double maxSpeed) {
    	_waypoints = waypoints;
    	_inHighGear = inHighGear;
    	_maxSpeed = maxSpeed;
    }

    protected void initialize() {
    	RobotMap.R1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	RobotMap.L1.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	RobotMap.R1.setSensorPhase(false);
    	RobotMap.L1.setSensorPhase(true);
    	updateGoalPos = true;
    	Drive.setShifters(_inHighGear);
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	i=0;
    }
 
    protected void execute() {
    	try {
    		x = _waypoints[i][0];
    		y = _waypoints[i][1];
    	
    		if(updateGoalPos) {
        		goalPos = Math.sqrt(Math.pow((x - lastX) * Constants.ticksPerInch, 2) + Math.pow((y - lastY) * Constants.ticksPerInch, 2)) + distanceCovered;
        		updateGoalPos = false;
    		}
    	
    		if(x == 0) {
    			if(y < 0) {
    				goalHeading = -90;
    			} else {
    				goalHeading = 90;
    			}
    		} else {
    			if(x >= 0 && y >= 0) {
    				goalHeading = Math.toDegrees(Math.atan((y/x)));
    			} else if(x <= 0 && y >= 0) {
    				goalHeading = Math.toDegrees(Math.atan((y/x))) + 180;
    			} else if(x <= 0 && y <= 0) {
    				goalHeading = Math.toDegrees(Math.atan((y/x))) + 180;
    			} else if(x >= 0 && y <= 0) {
    				goalHeading = Math.toDegrees(Math.atan((y/x))) + 360;
    			}
    		}
    	
    		lastX = _waypoints[i][0];
    		lastY = _waypoints[i][1];
    		
    		currentPos = (Drive.getLeftPosition() + Drive.getRightPosition())/2;
    	
    		if(Math.abs(Drive.getNavxAngle() - goalHeading) < 8) {
    			fixingHeading = false;
    			if(!needToFindNewStart) {
    				if(i == 0) {
    					if(output < _maxSpeed) {
    						output = (Math.pow((currentPos/(goalPos*.4)),4) * _maxSpeed) + .15;
    					} else {
    						output = _maxSpeed;
    					}
    					driveState = 0;
    				} else if(i > 0) {
    					if(i == (_waypoints.length-1)){
							if(goalPos < currentPos) {
        						output = (Math.pow((currentPos/(goalPos + slowStart)), 2) - 1) * _maxSpeed;
    						} else {
        						output = (1 - Math.pow((currentPos/(goalPos + slowStart)), 2)) * _maxSpeed;
    						}
        					driveState = 2;
        				} else {
        					output = _maxSpeed;
        					driveState = 1;
        				}
    				}
    			} else {
    				distanceCovered+=currentPos;
    				needToFindNewStart = false;
    				updateGoalPos = true;
    			}
    		} else {
    			fixingHeading = true;
    			needToFindNewStart = true;
    			Drive.straightDriveTele(0, goalHeading, _inHighGear);
    		}
    	
    		if(Math.abs(Math.abs(currentPos)-goalPos) < 250 && !fixingHeading) {
    			if(Math.abs(Drive.getNavxAngle() - goalHeading) < 8) {
    				distanceCovered += currentPos;
    				updateGoalPos = true;
    				i++;
    				System.out.println("made it to point: " + i);
    			} else {
    				Drive.straightDriveTele(0, goalHeading, _inHighGear);
    			}
    		} else {
    			Drive.straightDriveTele(output, goalHeading, _inHighGear);
    		}
    		System.out.println("Current: " + currentPos + " goal pos: " + goalPos + " output: " + output + " goal heading: " + goalHeading + " current heading: " + Drive.getNavxAngle() + " index " + i);
    
    	} catch(ArrayIndexOutOfBoundsException e) {
    		allDone = true;
    	}
    }    

    protected boolean isFinished() {
        return allDone;
    }

    protected void end() {
    	System.out.println("all done!");
    	Drive.stopDriving();
    }

    protected void interrupted() {
    }
}
