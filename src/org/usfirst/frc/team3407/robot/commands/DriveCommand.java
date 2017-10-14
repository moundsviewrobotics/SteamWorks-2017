package org.usfirst.frc.team3407.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3407.robot.*;
import org.usfirst.frc.team3407.robot.subsystems.Drivetrain;

/**
 *
 */
public class DriveCommand extends Command {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*if(false) {
    		if(!OI.isReverse()){
    			Robot.drivetrain.arcadeDrive();
    		} else{
    			Robot.drivetrain.reverseArcade();
    		}
    	} */
    		if(!OI.isTankSlow()){
    			if(!OI.isReverse()){
    			    Robot.drivetrain.tankDrive();
    			}
    			else{
    				Robot.drivetrain.reverseTank();
    			}
    		}
    		else{
    			if(!OI.isReverse()){
    				Robot.drivetrain.slowTank();
    			}
    			else{
    				Robot.drivetrain.reverseSlow();
    			}
    		}
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	((Drivetrain) Robot.drivetrain).stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
