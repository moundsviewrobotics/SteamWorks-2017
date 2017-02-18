package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class loader extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static SpeedController loadShooter = new VictorSP(8);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void shoot(){
    	loadShooter.set(1);
    }
    
    public static void stopShooting(){
        loadShooter.set(0);
    }
}

