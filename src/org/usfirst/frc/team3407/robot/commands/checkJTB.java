package org.usfirst.frc.team3407.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3407.robot.OI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class checkJTB extends Command {

    public checkJTB() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.JTBIsHeld == true && OI.tankIsSlow == false){
    		OI.tankIsSlow = true;
    		System.out.println("slow tank mode");
            OI.JTBIsHeld = false;
    	}
        else if(OI.JTBIsHeld == true && OI.tankIsSlow == true){
            OI.tankIsSlow = false;
            System.out.println("normal tank mode");
            OI.JTBIsHeld = false;
        }
    	
    	SmartDashboard.putBoolean("DB/LED 3", OI.tankIsSlow);
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
