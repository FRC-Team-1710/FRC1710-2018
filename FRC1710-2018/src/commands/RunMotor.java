package commands;

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
	double _output;
	int _count;
	int _time;
	
	
	public RunMotor(VictorSPX motor, double output, int time ) {
		_motor=motor;
		_output=output;
		_time=time;
		
	}
	public RunMotor(Spark motor, double output, int time ) {
		_motor=motor;
		_output=output;
		_time= time;
	}
    public RunMotor(TalonSRX motor, double output, int time) {
    	_motor=motor;
    	_output=output;
    	_time=time;
    }
    
    
    protected void initialize() {
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
    }

    protected void interrupted() {
    }
}
