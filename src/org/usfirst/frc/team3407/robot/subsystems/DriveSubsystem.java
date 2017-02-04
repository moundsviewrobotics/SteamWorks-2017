package org.usfirst.frc.team3407.robot.subsystems;


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3407.robot.OI;
import org.usfirst.frc.team3407.robot.commands.*;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private RobotDrive drive;
	public DriveSubsystem() {
		drive = new RobotDrive(0, 1);
		drive.setSafetyEnabled(false);
		drive.setSensitivity(0.75);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public void ArcadeDrive(){
    	drive.arcadeDrive(OI.y, OI.nx);; //this is what does arcade drive
    }
    
    public void TankDrive(){
    	drive.tankDrive(OI.y, OI.stick2.getY());
    }
    
    public void AutonomousDrive (double speedl, double time){
    	drive.drive(speedl, 0);
    	Timer.delay(time);
    	drive.drive(0, 0);
    }
    public void stop(){
    	drive.drive(0, 0);
    }
}