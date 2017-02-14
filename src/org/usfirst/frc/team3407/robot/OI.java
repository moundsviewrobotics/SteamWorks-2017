package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3407.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	public static final String SOFTWARE_VERSION_KEY = 		"DB/String 0";
	public static final String SOFTWARE_DATE_KEY = 			"DB/String 5";
	private static final String ARCADE_MODE_KEY = 			"DB/Button 1";
	public static final String ARCADE_DISPLAY_KEY = 		"DB/String 1";
	
	public static Joystick stick = new Joystick(0);
	public static Joystick stick2 = new Joystick(1);

	static {
		toArcade();
	}
	
	public static boolean isArcade() {
		boolean value = SmartDashboard.getBoolean(ARCADE_MODE_KEY,  true);
		setArcadeDisplay(value);
		return value;
	}

	public static void toArcade() {
		SmartDashboard.putBoolean(ARCADE_MODE_KEY,  true);
		setArcadeDisplay(true);
    }
	
    public static void toTank() {
		SmartDashboard.putBoolean(ARCADE_MODE_KEY,  false);
		setArcadeDisplay(false);
    }
    private static void setArcadeDisplay(boolean mode) {
		SmartDashboard.putString(ARCADE_DISPLAY_KEY,  (mode ? "Arcade" : "Tank") + " Drive Mode");    	
    }
    
    public static JoystickButton startFeeder = new JoystickButton(stick,5);
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    startFeeder.whenPressed(new startFeeder());
}

