package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3407.robot.OI;
import org.usfirst.frc.team3407.robot.commands.DriveCommand;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
	private RobotDrive drive;
	
	public DriveSubsystem() {
		drive = new RobotDrive(0, 1, 2, 3);
		drive.setSafetyEnabled(false);
		drive.setSensitivity(0.75);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand());
    }
    
    public void arcadeDrive(){
    	drive.arcadeDrive(OI.stick.getY(), -OI.stick.getX());;
    }
    
    public void tankDrive(){
    	drive.arcadeDrive(OI.stick.getY(), OI.stick2.getY());;
    }
    
    public void driveForward(double speedl, double time) {
    	drive.drive(speedl, 0);
    	Timer.delay(time);
    	drive.drive(0, 0);
    }
    
    public void  driveRight(double AutoSpeed, double AutoTime) {
    	drive.drive(AutoSpeed/2, 0);
    	Timer.delay(AutoTime/4);
    	drive.drive(AutoSpeed, .5);
    	Timer.delay(AutoTime/2);
    	drive.drive(AutoSpeed/2, 0.5);
    	Timer.delay(AutoTime/4);
    	drive.drive(0, 0);
    }
    public void stop(){
    	drive.drive(0, 0);
    }
}