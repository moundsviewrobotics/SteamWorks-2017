
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
	
	private static final String ARCADE_MODE_KEY = "DB/Button 1";
	private static final String ARCADE_DISPLAY_KEY = "DB/String 2";
	
	public static final String REVERSE_MODE_KEY = "DB/Button 2";
	public static final String REVERSE_DISPLAY_KEY = "DB/String 2";
	
	public static final String SLOW_MODE_KEY = "DB/Button 3";
	public static final String SLOW_DISPLAY_KEY = "DB/String 3";
	
	public static final String LOADER_ENGAGED_KEY = "DB/LED 0";
	public static final String FEEDER_ENGAGED_KEY = "DB/LED 1";
	public static final String SHOOTER_ENGAGED_KEY = "DB/LED 2";
	
	public static Joystick stick = new Joystick(0);
	public static Joystick stick2 = new Joystick(1);
	
	public static JoystickButton loadingButton = new JoystickButton(stick, 1);
	public static JoystickButton startFeeder = new JoystickButton(stick, 3);
	public static JoystickButton stopFeeder = new JoystickButton(stick, 4);

	public static JoystickButton startShooting = new JoystickButton(stick, 5);
	public static JoystickButton stopShooting = new JoystickButton(stick, 6);
	
	public static JoystickButton reverseDrive = new JoystickButton(stick2, 1);
	
    static {
    	loadingButton.whenActive(new startLoading());
    	loadingButton.whenInactive(new stopLoading());
    	
    	startShooting.whenPressed(new startShooting());
    	stopShooting.whenPressed(new stopShooting());
    	
    	startFeeder.whenPressed(new StartFeeder());
    	stopFeeder.whenPressed(new StopFeeder());
 
    	
    	toTank(); 
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
    	SmartDashboard.putBoolean(ARCADE_MODE_KEY, false); 
    	setArcadeDisplay(false);
    	
    } 

    private static void setArcadeDisplay(boolean mode) { 
    	SmartDashboard.putString(ARCADE_DISPLAY_KEY,  (mode ? "Arcade" : "Tank") + " Drive Mode");    	 
    } 
    
    
    public static boolean isReverse() {
    	boolean reverse = SmartDashboard.getBoolean(REVERSE_MODE_KEY, false);
    	setReverseDisplay(reverse);
    	return reverse;
    }
    
    public static void noReverse(){
    	SmartDashboard.putBoolean(REVERSE_MODE_KEY, false);
    	setReverseDisplay(false);
    }
    
    public static void toReverse() {
    	SmartDashboard.putBoolean(REVERSE_MODE_KEY, true);
    	setReverseDisplay(true);
    }
    
    public static void setReverseDisplay(boolean mode){
    	SmartDashboard.putString(REVERSE_DISPLAY_KEY, (mode ? "reverse" : "forward"));
    }
    
    
    public static boolean isTankSlow(){
    	boolean slow = SmartDashboard.getBoolean(SLOW_MODE_KEY, false);
    	setSlowDisplay(slow);
    	return slow;
    }
    
    public static void toSlow(){
    	SmartDashboard.putBoolean(SLOW_MODE_KEY, true);
    	setSlowDisplay(true);
    	toTank();
    }
    
    public static void noSlow(){
    	SmartDashboard.putBoolean(SLOW_MODE_KEY, false);
    	setSlowDisplay(false);
    	toTank();
    }
    
    public static void setSlowDisplay(boolean mode){
    	SmartDashboard.putString(SLOW_DISPLAY_KEY, mode ? "slow" : "not slow");
    }
}