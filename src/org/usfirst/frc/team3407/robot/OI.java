
package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

import org.usfirst.frc.team3407.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	public static Joystick stick = new Joystick(0);
	public static Joystick stick2 = new Joystick(1);
	
	public static JoystickButton startLoading = new JoystickButton(stick, 5);
	public static JoystickButton startShooting = new JoystickButton(stick, 6);
	
    static {
    	startLoading.whenPressed(new startLoading());
    	startShooting.whenPressed(new startShooting());
    }

	public static void toArcade(){
    	//isArcade = true;
    }
    
	public static void toTank(){
    	//isArcade = false;
    }
}