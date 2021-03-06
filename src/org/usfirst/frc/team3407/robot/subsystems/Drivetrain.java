package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3407.robot.OI;
import org.usfirst.frc.team3407.robot.commands.DriveCommand;
/**
 *
 */
public class Drivetrain extends Subsystem {
    
	private RobotDrive drive;
	
	public Drivetrain() {
		drive = new RobotDrive(0, 1, 2, 3);
		drive.setSafetyEnabled(false);
		drive.setSensitivity(0.75);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand());
    }
    
    public void arcadeDrive(){
    	drive.arcadeDrive(OI.stick.getY()*.8, -OI.stick.getX()*.8);	
    }

    public void tankDrive(){
    	drive.tankDrive(OI.stick.getY() *.8, OI.stick2.getY()*.8); 
    	
    }
    
    public void slowTank(){
    	drive.tankDrive(OI.stick.getY() * .35
    			+ OI.stick.getX() * OI.stick.getX()
    			* OI.stick.getY() * .65,
    			OI.stick2.getY() * .35
    			+ OI.stick2.getX() * OI.stick2.getX()
    			* OI.stick2.getY() * .65);
    }
    
    public void reverseArcade(){
    	drive.arcadeDrive(- OI.stick.getY(), -OI.stick.getX());
    }
    
    public void reverseTank(){
    	drive.tankDrive(-OI.stick2.getY(), -OI.stick.getY());
    }
    
    public void reverseSlow(){
    	drive.tankDrive(
    			-OI.stick2.getY() * .35
    			- OI.stick2.getX() * OI.stick2.getX()
    			* OI.stick2.getY() * .65,
    			- OI.stick.getY() * .35
    			- OI.stick.getX() * OI.stick.getX()
    			* OI.stick.getY() * .65
    			);
    }
    

    public void driveForward(double speedl, double time) {
    	drive.drive(speedl, 0);
    	Timer.delay(time);
    	drive.drive(0, 0);
    }
    
   public void turnRight(double speed, double time) {
	   drive.drive(speed, 1);
	   Timer.delay(time);
	   drive.drive(0, 0);
   }
   public void turnLeft(double speed, double time) {
	   drive.drive(speed, -1);
	   Timer.delay(time);
	   drive.drive(0, 0);
   }
    public void stop(){
    	drive.drive(0, 0);
    }
}