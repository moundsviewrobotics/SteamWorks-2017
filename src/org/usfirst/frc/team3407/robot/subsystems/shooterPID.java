package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow ;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */

public class shooterPID extends PIDSubsystem {
	
	public static final double INITIAL_MOTOR_SPEED = 0.8;
	public static final double DEFAULT_SET_POINT = 1350;
	
	//private static final double MAX_RATE = 1800;
	
	private VictorSP shooterVictor = new VictorSP(5);
	private Encoder encoder = new Encoder(2 /* Channel A */, 1 /* Channel B */);
	
	//public static final String SETPOINT_KEY = "DB/Slider0";
	//public static final String P_KEY = "DB/Slider1";
	//public static final String I_KEY = "DB/Slider2";
	//public static final String D_KEY = "DB/Slider3";
	
	//SmartDashboard.setDefaultNumber(SETPOINT_KEY, 1250);
	//SmartDashboard.setDefaultNumber(SETPOINT_KEY, 1250);
	//SmartDashboard.setDefaultNumber(SETPOINT_KEY, 1250);
	//SmartDashboard.setDefaultNumber(SETPOINT_KEY, 1250);
	
	private double speed;
	
    // Initialize your subsystem here
    public shooterPID() {
    	
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("shooterPID", 0.00022, 0.000001, 0.00005);
    	
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
    	double speed = Double.parseDouble(SmartDashboard.getString("DB/String 2", "0.7"));
    	setMotorSpeed(speed);
    	SmartDashboard.putString("DB/String 7", Double.toString(encoder.getRate()));
    	SmartDashboard.putString("DB/String 9", Double.toString(speed));
    }
    
    protected double returnPIDInput() {
    	double rate = encoder.getRate();
    	SmartDashboard.putString("DB/String 7", Double.toString(rate));
        return rate;
    }

    protected void usePIDOutput(double output) {
    	double speedAdjust = output;
     	SmartDashboard.putString("DB/String 6", Double.toString(output));
    	SmartDashboard.putString("DB/String 8", Double.toString(getPIDController().getAvgError()));
    	SmartDashboard.putString("DB/String 9", Double.toString(speed));
    	setMotorSpeed(speed + speedAdjust);  	
    }
}
