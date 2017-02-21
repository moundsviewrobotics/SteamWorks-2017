package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.livewindow.LiveWindow ;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */

public class shooterPID extends PIDSubsystem {
	
	private static final double MAX_RATE = 1800;
	
	private VictorSP shooterVictor = new VictorSP(5);
	private Encoder encoder = new Encoder(2 /* Channel A */, 1 /* Channel B */);
	
	private double speed;
	
    // Initialize your subsystem here
    public shooterPID() {
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("shooterPID", 0.25, 1.5, 0.5);
    	setSetpoint(1000);
		
    	setAbsoluteTolerance(0.02);
    	//getPIDController().setContinuous(true);
    	//LiveWindow.addActuator("Shooter", "Motor", shooterVictor);
    	//LiveWindow.addSensor("Shooter", "Encoder", encoder);
    	//LiveWindow.addActuator("Shooter", "PID", getPIDController());
    	//PIDController.startLiveWindowMode();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setMotorSpeed(double speed) {
    	this.speed = speed;
    	shooterVictor.set(speed);
    }
    
    public void test() {
    	LiveWindow.addActuator("Shooter", "Motor", shooterVictor);
    	LiveWindow.addSensor("Shooter", "Encoder", encoder);
    	LiveWindow.addActuator("Shooter", "PID", getPIDController());
    	String speed = SmartDashboard.getString("DB/String 7", "0.5");
    	shooterVictor.set(Double.parseDouble(speed));

    	SmartDashboard.putString("DB/String 8", Double.toString(encoder.getRate()));
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	double rate = encoder.getRate();
    	SmartDashboard.putString("DB/String 3", Double.toString(rate));
        return rate;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	// Normalize revolutions to 0-1 scale for moter speed
    	double speedAdjust = output / MAX_RATE;
    	speed += speedAdjust;
    	SmartDashboard.putString("DB/String 4", Double.toString(output));
    	shooterVictor.set(speed);  	
    }
}
