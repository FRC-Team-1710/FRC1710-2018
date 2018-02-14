package org.usfirst.frc.team1710.robot;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DashboardReport {
	
	static List<Object> _thingsToPutOnDashboard = new ArrayList<Object>();
	static List<Double> encoderValues = new ArrayList<Double>();
	static List<TalonSRX> srxObjects = new ArrayList<TalonSRX>();
	
	public DashboardReport(List<Object> thingsToPutOnDashboard) {
		_thingsToPutOnDashboard = thingsToPutOnDashboard;
	}
	
	public static List<TalonSRX> getSrxs() {
		for(Object thing : _thingsToPutOnDashboard) {
			if(thing instanceof TalonSRX) {
				srxObjects.add((TalonSRX) thing);
			}
		}
		
		return srxObjects;
	}
	
	public static List<Double> getEncoderValues() {
		for(Object thing : _thingsToPutOnDashboard) {
			if (thing instanceof TalonSRX) {
				encoderValues.add((double) ((TalonSRX) thing).getSelectedSensorPosition(0));
			}
		}
		return encoderValues;
	}	
}
