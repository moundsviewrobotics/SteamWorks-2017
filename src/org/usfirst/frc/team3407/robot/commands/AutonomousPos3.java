package org.usfirst.frc.team3407.robot.commands;

import org.usfirst.frc.team3407.robot.Robot;
import org.usfirst.frc.team3407.robot.subsystems.loader;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousPos3 extends Command {

    public boolean mirror;

	public AutonomousPos3(boolean mirror) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);	
    	this.mirror = mirror;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveForward(1, 1.2);
    	if(mirror){
    		Robot.drivetrain.turnRight(1, 1.9);
    	}
    	else {
    		Robot.drivetrain.turnRight(1, 1.9);
    	}
    	Timer.delay(3.5);
    	loader.shoot();
    	Timer.delay(8);
    	loader.stopShooting();
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
