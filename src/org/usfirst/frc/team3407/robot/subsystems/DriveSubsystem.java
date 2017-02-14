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
    	drive.tankDrive(OI.stick.getY(), OI.stick2.getY());;
    }
    
    public void driveForward(double speedl, double time) {
    	drive.tankDrive(speedl, speedl);
    	Timer.delay(time);
    	drive.tankDrive(0, 0);
    }
    //make sure these  (these being speed for both turnLeft and turnRight and driveForward)
    //are set to stick esq values. (-1.0 to 1.0)
    public void turnRight(double speed, double time) {
    	drive.tankDrive(-speed,speed);
    	Timer.delay(time);
    	drive.tankDrive(0, 0);
    }
    public void turnLeft(double speed, double time) {
    	drive.tankDrive(speed,-speed);
    	Timer.delay(time);
    	drive.tankDrive(0, 0);
    }
    //TODO: add gryo turning method
    
    public void stop(){
    	drive.drive(0, 0);
    }
}
