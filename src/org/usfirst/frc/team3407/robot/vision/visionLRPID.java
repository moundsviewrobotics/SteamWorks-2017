package org.usfirst.frc.team3407.robot.vision;

import org.usfirst.frc.team3407.robot.vision.BoilerEvaluator;
import org.usfirst.frc.team3407.robot.vision.VisionProcessor;
import org.usfirst.frc.team3407.robot.vision.GripPipeline;

public class visionLRPID {
	public static int setpoint = 640;
	public static int sumOfErrors = 0;
	public static int errorNum = 0;
	public static double kP = .03/1280,
			kI = .03/1280,
			kD = .03/1280,
			kF = 1/1280;
	public static int error,
	integral,
	derivative,
	prevError;
	public static double output;
	public static double LRControl(){
		
		error = setpoint - BoilerEvaluator.evalLRdist(GripPipeline.filterContoursOutput);
		sumOfErrors += error;
		errorNum++;
		integral = sumOfErrors / errorNum;
		derivative = error - prevError;
		output = error * kP + integral * kI + derivative * kD + setpoint * kF;
		return output;
		
		
	}
}
