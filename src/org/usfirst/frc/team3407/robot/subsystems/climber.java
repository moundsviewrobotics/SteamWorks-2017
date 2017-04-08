package org.usfirst.frc.team3407.robot.subsystems;

import org.usfirst.frc.team3407.robot.OI;
import org.usfirst.frc.team3407.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static SpeedController climberMotor = new VictorSP(4);
	
	public static void start() {
    	//.putBoolean(OI.FEEDER_ENGAGED_KEY, true);    	 
		climberMotor.set(.9);
		Robot.shooterpid.setMotorSpeed(0.0);
	};

	public static void stop() {
    	//SmartDashboard.putBoolean(OI.FEEDER_ENGAGED_KEY, false);    	 
		climberMotor.set(0);
		Robot.shooterpid.setMotorSpeed(shooterPID.INITIAL_MOTOR_SPEED);
	};
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

