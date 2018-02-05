package org.usfirst.frc.team1710.robot;

public class RobotState {
	
	double _liftPosition, _robotHeading, _wristPosition;
	boolean _isLeftIntakeActuated, _isRightIntakeActuated;
	
	public RobotState(double liftPosition, double robotHeading, double wristPosition, boolean isLeftIntakeActuated,
			boolean isRightIntakeActuated)
	{
		_liftPosition = liftPosition;
		_robotHeading = robotHeading;
		_wristPosition = wristPosition;
		_isLeftIntakeActuated = isLeftIntakeActuated;
		_isRightIntakeActuated = isRightIntakeActuated;
	}
	
	public RobotState getState() {
		return new RobotState(_liftPosition, _robotHeading, _wristPosition, _isLeftIntakeActuated, _isRightIntakeActuated);
	}
	
	public void setState(RobotState wantedState) {
		_liftPosition = wantedState._liftPosition;
		_robotHeading = wantedState._robotHeading;
		_wristPosition = wantedState._wristPosition;
		_isLeftIntakeActuated = wantedState._isLeftIntakeActuated;
		_isRightIntakeActuated = wantedState._isRightIntakeActuated;
	}
}
