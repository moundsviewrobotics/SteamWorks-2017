package org.usfirst.frc.team3407.robot;

//import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
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
	public static boolean isArcade = true;
	public static boolean JBBIsHeld = false;
	public static boolean JBCIsHeld = false;
	//public static boolean JTBIsHeld = false;
	public static boolean tankIsSlow = false;
	public static JoystickButton JBA = new JoystickButton(stick, 1);
	public static Button JBB = new JoystickButton(stick, 2);
	public static Button JBC = new JoystickButton(stick, 3);
	//public static Button JTA = new JoystickButton(stick2, 1);
	//public static Button JTB = new JoystickButton(stick2, 2);

	public static Joystick getInstance() {
		// TODO Auto-generated method stub
		return stick;
	}
	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
	public static void toArcade(){
    	isArcade = true;
    }
    public static void toTank(){
    	isArcade = false;
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
	
    {
		JBA.whileHeld(new checkButtons());
		JBB.whenPressed(new JoystickButtonBHeld());
		JBB.whenReleased(new JoystickButtonBRelease());
		JBC.whenPressed(new JoystickButtonCHeld());
		JBC.whenReleased(new JoystickButtonCRelease());
		//JTA.whileHeld(new checkJTB);
		//JTB.whenPressed(new JTBIsHeld);
		//JTB.whenReleased(new JTBReleased);
    }
	
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

