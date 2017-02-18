package org.usfirst.frc.team3407.robot.commands;

import org.usfirst.frc.team3407.robot.Robot;
import org.usfirst.frc.team3407.robot.subsystems.loader;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousPath2 extends Command {

    public AutonomousPath2() {
    	requires(Robot.driveSubsystem);	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.driveForward(0.7, 1.2);
    	Robot.driveSubsystem.turnLeft(1, 1);
    	loader.shoot();
    	Timer.delay(2);
    	loader.stopShooting();
    	
    	System.out.println("B");
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
