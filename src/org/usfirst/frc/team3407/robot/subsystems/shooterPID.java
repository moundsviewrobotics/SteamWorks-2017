package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;

import java.util.ArrayList;
import java.util.Date;

import org.usfirst.frc.team3407.robot.commands.ShooterAdjustSetPointCommand;

/**
 *
 */
public class shooterPID extends PIDSubsystem {

	public static final double INITIAL_MOTOR_SPEED = 0.8;
	public static final double DEFAULT_SET_POINT = 1350;

	private static final boolean DEBUG_ENABLE = false;
	private static final long DEBUG_INTERVAL = 10;

	// private static final double MAX_RATE = 1800;

	private VictorSP shooterVictor = new VictorSP(5);
	private Encoder encoder = new Encoder(2 /* Channel A */, 1 /* Channel B */);

	private double speed;

	private long testCounter = 0;
	
	private double debugTolerance = 30;

	private ArrayList<DebugInfo> debugInfos = new ArrayList<>();

	// Initialize your subsystem here
	public shooterPID() {
		super("shooterPID", 0.00022, 0.000001, 0.00005);

		setSetpoint(DEFAULT_SET_POINT);

		setAbsoluteTolerance(100);
		PIDController controller = getPIDController();
		controller.setContinuous(false);
		LiveWindow.addActuator("Shooter", "Motor", shooterVictor);
		LiveWindow.addSensor("Shooter", "Encoder", encoder);
		LiveWindow.addActuator("Shooter", "PID", getPIDController());
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShooterAdjustSetPointCommand());
	}

	public void setMotorSpeed(double speed) {
		this.speed = Math.max(0.0, speed);
		this.speed = Math.min(1, this.speed);
		shooterVictor.set(this.speed);
	}

	public void test() {
		testCounter++;
		if (getPIDController().isEnabled() && ((testCounter % DEBUG_INTERVAL) == 0)) {
			ArrayList<DebugInfo> list = new ArrayList<>();
			synchronized (debugInfos) {
				if (!debugInfos.isEmpty()) {
					list.addAll(debugInfos);
					debugInfos.clear();
				}
			}
			printDebug(list);
		} else {
			// Manual speed adjust and rate feedback
			double speed = Double.parseDouble(SmartDashboard.getString("DB/String 2", "0.7"));
			setMotorSpeed(speed);
			SmartDashboard.putString("DB/String 7", Double.toString(encoder.getRate()));
			SmartDashboard.putString("DB/String 9", Double.toString(speed));
		}
	}

	protected double returnPIDInput() {
		double rate = encoder.getRate();
		SmartDashboard.putString("DB/String 7", Double.toString(rate));
		return rate;
	}

	protected void usePIDOutput(double output) {
		double speedAdjust = output;
		PIDController pid = getPIDController();
		SmartDashboard.putString("DB/String 6", Double.toString(output));
		SmartDashboard.putString("DB/String 8", Double.toString(pid.getAvgError()));
		SmartDashboard.putString("DB/String 9", Double.toString(speed));
		setMotorSpeed(speed + speedAdjust);
		if (DEBUG_ENABLE) {
			double error = pid.getError();
			if (Math.abs(error) > debugTolerance) {
				DebugInfo debugInfo = new DebugInfo(System.currentTimeMillis(), speed, error, speedAdjust);
				synchronized (debugInfos) {
					debugInfos.add(debugInfo);
				}
			}
		}
	}

	private void printDebug(ArrayList<DebugInfo> list) {
		int size = list.size();
		if (size == 0) {
			System.out.println("No debug info");
		} else {
			for (int i = 0; i < size; i++) {
				System.out.println(list.get(i));
			}
		}
	}

	private static class DebugInfo {

		private long timestamp;
		private double speed;
		private double error;
		private double adjust;

		public DebugInfo(long timestamp, double speed, double error, double adjust) {
			this.timestamp = timestamp;
			this.speed = speed;
			this.error = error;
			this.adjust = adjust;
		}

		public String toString() {
			return new Date(timestamp) + "(" + timestamp + "): e=" + error + "s=" + speed + " o=" + adjust;	
		}
	}
}
