package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {
public static TalonSRX R1, L1, intakeR, intakeL, wrist, lift1, lift2;
public static VictorSPX R2, R3, L2, L3;
public static Joystick driveStick, mechStick;
public static AHRS navx;
}
