package commands;

import org.usfirst.frc.team1710.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunMotor extends Command {
	Object _motor;
	double _output, maxCurrent, minCurrent;
	int _count, _time, _pdpChannel;	
	String _motorName;
	
	public RunMotor(VictorSPX motor, double output, int time, int pdpChannel, String motorName) {
		_motor=motor;
		_output=output;
		_time=time;
		_motorName = motorName;
	}
	public RunMotor(Spark motor, double output, int time, int pdpChannel, String motorName) {
		_motor=motor;
		_output=output;
		_time= time;
		_motorName = motorName;
	}
    public RunMotor(TalonSRX motor, double output, int time, int pdpChannel, String motorName) {
    	_motor=motor;
    	_output=output;
    	_time=time;
		_motorName = motorName;
    }
    
    
    protected void initialize() {
    	maxCurrent = RobotMap.pdp.getCurrent(_pdpChannel);
    	minCurrent = RobotMap.pdp.getCurrent(_pdpChannel);
    }
    
    protected void execute() {
    	
    	if(_motor instanceof TalonSRX) {
        	((TalonSRX) _motor).set(ControlMode.PercentOutput, _output);
    	} else if (_motor instanceof Spark) {
    		((Spark)_motor).set(_output);
    	} else {
    		((VictorSPX)_motor).set(ControlMode.PercentOutput,_output);
    	}
    	
    	_count++;
    	
    	if(RobotMap.pdp.getCurrent(_pdpChannel) > maxCurrent) {
    		maxCurrent = RobotMap.pdp.getCurrent(_pdpChannel);
    	}
    	
    	if(RobotMap.pdp.getCurrent(_pdpChannel) < minCurrent) {
    		minCurrent = RobotMap.pdp.getCurrent(_pdpChannel);
    	}
    }


    protected boolean isFinished() {
        return _count>_time/20;
    }

  
    protected void end() {
    	if(_motor instanceof TalonSRX) {
        	((TalonSRX) _motor).set(ControlMode.PercentOutput, 0);
    	} else if (_motor instanceof Spark) {
    		((Spark)_motor).set(0);
    	} else {
    		((VictorSPX)_motor).set(ControlMode.PercentOutput,0);
    	}
    	
    	System.out.println("Motor " + _motorName + "'s max current was: " + maxCurrent + " and " + _motorName + "'s min current was: " + minCurrent);
    	
    }

    protected void interrupted() {
    }
}
