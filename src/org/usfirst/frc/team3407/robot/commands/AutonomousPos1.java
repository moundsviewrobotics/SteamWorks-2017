package org.usfirst.frc.team3407.robot.commands;

import org.usfirst.frc.team3407.robot.Robot;
import org.usfirst.frc.team3407.robot.subsystems.loader;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class AutonomousPos1 extends Command {

    public AutonomousPos1(boolean mirror) {
    	requires(Robot.drivetrain);	
    	this.mirror = mirror;
    }
    private boolean mirror;

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveForward(0.7, 1.2);
    	if(mirror) {
    		Robot.drivetrain.turnLeft(1, 1);
    	}
    	else {
    		Robot.drivetrain.turnRight(1, 1);
    	}
    	Timer.delay(5);
    	loader.shoot();
    	Timer.delay(7);
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
