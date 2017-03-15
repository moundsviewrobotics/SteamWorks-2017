package org.usfirst.frc.team3407.robot.commands;

import org.usfirst.frc.team3407.robot.Robot;
import org.usfirst.frc.team3407.robot.subsystems.loader;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class AutonomousPos2 extends Command {

	public boolean mirror;

	public AutonomousPos2(boolean mirror) {
    	requires(Robot.drivetrain);	
    	this.mirror = mirror;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveForward(0.5, 1);
    	Robot.drivetrain.driveForward(0.5, 0.5);
    	if(mirror){
    		Robot.drivetrain.turnLeft(1, 1);
    		Robot.drivetrain.driveForward(0.7, 1);
    		Robot.drivetrain.turnRight(1, 1);
    	}
    	else {
    		Robot.drivetrain.turnRight(1, 1);
    		Robot.drivetrain.driveForward(0.7, 1);
    		Robot.drivetrain.turnLeft(1, 1);
    	}
    	Robot.drivetrain.driveForward(0.7, 1.1);
    	Robot.drivetrain.turnRight(1, 2);
    	loader.shoot();
    	Timer.delay(4);
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
