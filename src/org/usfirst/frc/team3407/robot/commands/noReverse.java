package org.usfirst.frc.team3407.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3407.robot.OI;
/**
 *
 */
public class noReverse extends Command {

    public noReverse() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.noReverse();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
