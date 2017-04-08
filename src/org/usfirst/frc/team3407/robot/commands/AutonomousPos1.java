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
    public boolean mirror;

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveForward(0.6, 1.8);
    	if(mirror) {
    		Robot.drivetrain.turnLeft(0.6, 2.5);
    	}
    	else {
    		Robot.drivetrain.turnRight(.8, 1.9);
    	}
    	loader.shoot();
    	Timer.delay(8);
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
