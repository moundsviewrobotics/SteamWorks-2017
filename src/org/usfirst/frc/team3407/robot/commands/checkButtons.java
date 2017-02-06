package org.usfirst.frc.team3407.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3407.robot.OI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class checkButtons extends Command {

    public checkButtons() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.JBBIsHeld == true && OI.JBCIsHeld == false){
    		OI.toArcade();
    		System.out.println("arcade mode");
    	}
    	if (OI.JBCIsHeld == true && OI.JBBIsHeld == false){
    		OI.toTank();
    		System.out.println("tank mode");
    	}
    	SmartDashboard.putBoolean("DB/LED 0", OI.isArcade);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
