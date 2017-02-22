package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow ;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */

public class shooterPID extends PIDSubsystem {
	
	public static final double DEFAULT_SET_POINT = 1500;
	
	//private static final double MAX_RATE = 1800;
	
	private VictorSP shooterVictor = new VictorSP(5);
	private Encoder encoder = new Encoder(2 /* Channel A */, 1 /* Channel B */);
	
	private double speed;
	
    // Initialize your subsystem here
    public shooterPID() {
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("shooterPID", 0.0001, 0.00, 0.0005);
    	
    	setSetpoint(DEFAULT_SET_POINT);
		
    	setAbsoluteTolerance(100);
    	PIDController controller = getPIDController();
       	controller.setContinuous(false);
        LiveWindow.addActuator("Shooter", "Motor", shooterVictor);
    	LiveWindow.addSensor("Shooter", "Encoder", encoder);
    	LiveWindow.addActuator("Shooter", "PID", getPIDController());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setMotorSpeed(double speed) {
    	this.speed = Math.max(0.0,  speed);
    	this.speed = Math.min(1,  this.speed);
    	shooterVictor.set(this.speed);
    }
    
    public void test() {
    	LiveWindow.addActuator("Shooter", "Motor", shooterVictor);
    	LiveWindow.addSensor("Shooter", "Encoder", encoder);
    	LiveWindow.addActuator("Shooter", "PID", getPIDController());
    	//SmartDashboard.putString("DB/String 8", Double.toString(encoder.getRate()));
    }
    
    protected double returnPIDInput() {
    	double rate = encoder.getRate();
    	SmartDashboard.putString("DB/String 3", Double.toString(rate));
        return rate;
    }

    protected void usePIDOutput(double output) {
    	double speedAdjust = output;
     	SmartDashboard.putString("DB/String 4", Double.toString(output));
    	SmartDashboard.putString("DB/String 7", Double.toString(getPIDController().getAvgError()));
    	SmartDashboard.putString("DB/String 9", Double.toString(speed));
    	setMotorSpeed(speed + speedAdjust);  	
    }
}
