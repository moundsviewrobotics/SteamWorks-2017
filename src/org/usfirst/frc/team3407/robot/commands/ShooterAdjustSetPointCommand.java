package org.usfirst.frc.team3407.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3407.robot.OI;
import org.usfirst.frc.team3407.robot.Robot;

public class ShooterAdjustSetPointCommand extends Command {

		private static final double RANGE = 100.0;
		
		private double initialCenterPoint = 0.0; 
	   
		public ShooterAdjustSetPointCommand() {
	    	requires(Robot.shooterpid);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	initialCenterPoint = OI.stick.getZ();
    		System.out.println("Initial shooter adjust center point: " + initialCenterPoint);
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	double currentPoint = OI.stick.getZ();
	    	double adjust = (currentPoint - initialCenterPoint) * RANGE;
	    	if(adjust != 0) {
	    		System.out.println("Adjusting shooter setpoint: " + adjust);
	    		double currentSetPoint = Robot.shooterpid.getSetpoint();
	    		Robot.shooterpid.setSetpoint(currentSetPoint + adjust);
	    	}
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
	    	end();
	    }
}
