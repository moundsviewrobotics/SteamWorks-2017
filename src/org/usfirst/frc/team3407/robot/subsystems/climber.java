package org.usfirst.frc.team3407.robot.subsystems;

import org.usfirst.frc.team3407.robot.OI;

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
    public static SpeedController climberMotor = new VictorSP(6);
	
	public static void start() {
    	//.putBoolean(OI.FEEDER_ENGAGED_KEY, true);    	 
		climberMotor.set(.75);
	};

	public static void stop() {
    	SmartDashboard.putBoolean(OI.FEEDER_ENGAGED_KEY, false);    	 
		climberMotor.set(0);
	};
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

