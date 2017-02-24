package org.usfirst.frc.team3407.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Motor channels
	public static final int DRIVE_LEFT_A = 0;
	public static final int DRIVE_LEFT_B = 1;
	public static final int DRIVE_RIGHT_A = 2;
	public static final int DRIVE_RIGHT_B = 3;

	public static final int LOADER_SHOOTER = 4;
	public static final int LOADER_AGITATOR = 7;
    
	public static final int SHOOTER = 5;

	public static final int FEEDER = 6;
	
	// Sensors
	public static final int SHOOTER_ENCODER_A = 2;
	public static final int SHOOTER_ENCODER_B = 1;
}
