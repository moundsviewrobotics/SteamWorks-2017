
package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3407.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	public static Joystick stick = new Joystick(0);
	public static Joystick stick2 = new Joystick(1);
	
	public static JoystickButton startShooter = new JoystickButton(stick, 6);
	public static JoystickButton stopShooter = new JoystickButton(stick, 7);
	public static JoystickButton left = new JoystickButton(stick, 8);
	public static JoystickButton right = new JoystickButton(stick, 9);
	public static JoystickButton startLoader = new JoystickButton(stick, 10);
	public static JoystickButton stopFeeder = new JoystickButton(stick, 4);
	public static JoystickButton startFeeder = new JoystickButton(stick, 5);
	

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
	public static boolean isArcade() {
		return true;
	}
	public static void toArcade(){
    	//isArcade = true;
    }
    public static void toTank(){
    	//isArcade = false;
    }
    {
    	startLoader.whenPressed(new startLoading());
    	stopShooter.whenPressed(new stopLoading());
    	right.whenPressed(new slideRight());
    	left.whenPressed(new slideLeft());
    	
    	startFeeder.whenPressed(new StartFeeder());
    	stopFeeder.whenPressed(new StopFeeder());
    }
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
}