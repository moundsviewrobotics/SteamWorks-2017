package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDInterface;

import org.usfirst.frc.team3407.robot.Robot;
import edu.wpi.first.wpilibj.VictorSP;
/**
 *
 */

public class shooterPID extends PIDSubsystem {
	public static VictorSP shooterVictor = new VictorSP(9);
	
    // Initialize your subsystem here
    public shooterPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("shooterPID", 0.25, 1.5, 0.5);
    	
    	
    	setAbsoluteTolerance(0.02);
    	getPIDController().setContinuous(true);
    	LiveWindow.addActuator("Shooter", "Motor", (VictorSP) shooterVictor);
	LiveWindow.addSensor("Shooter", "Encoder", (Encoder) Robot.shooterEncoder);
	LiveWindow.addActuator("Shooter", "PID", getPIDController());
    	//PIDController.startLiveWindowMode();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.shooterEncoder.pidGet();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	shooterVictor.set(output);
    	
    	
    }
}
