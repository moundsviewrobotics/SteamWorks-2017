package org.usfirst.frc.team3407.robot;

import org.usfirst.frc.team3407.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team3407.robot.subsystems.loader;
import org.usfirst.frc.team3407.robot.subsystems.linearSlide;
import org.usfirst.frc.team3407.robot.commands.AutonomousPath1;
import org.usfirst.frc.team3407.robot.commands.AutonomousPath2;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static loader ballLoader = new loader();
	public static linearSlide slide = new linearSlide();
	
	
	private static final String SOFTWARE_VERSION = "Steamworks-2017-0.2";
	private static final String SOFTWARE_DATE = "DATE(02/11/17)";
    Command autonomousCommand;
    private Victor shooterMotor = new Victor(2);
    private Encoder shooterSensor = new Encoder(1, 2);
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI(); 
        SmartDashboard.putString("DB/String 0", SOFTWARE_VERSION);
        SmartDashboard.putString("DB/String 5", SOFTWARE_DATE); 
        
        CameraServer server = CameraServer.getInstance();
        server.startAutomaticCapture("Front", 0);
 
		
    	oi = new OI(); 
        
		//SmartDashboard.putString(OI.SOFTWARE_VERSION_KEY, SOFTWARE_VERSION);
       //SmartDashboard.putString(OI.SOFTWARE_DATE_KEY, SOFTWARE_DATE); 
        
        SmartDashboard.putData(Scheduler.getInstance());
        SmartDashboard.putData(driveSubsystem);
        
        //CameraServer server = CameraServer.getInstance();
        //server.startAutomaticCapture("Front", 0);
        //server.startAutomaticCapture("Back", 1);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	//autochooser
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        
        String selected = SmartDashboard.getString("Auto Selector","A");
        System.out.println("SELECTED=" + selected);
        
        if(selected .equals("A")) {
        	autonomousCommand = new AutonomousPath1();
        }
        else if(selected .equals("B")) {
        	autonomousCommand = new AutonomousPath2();
        }
        else {
        	autonomousCommand = new AutonomousPath1();
        }
        //note to future teams: don't use sendablechooser	
        System.out.println(autonomousCommand);
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    	LiveWindow.addActuator("Shooter",  "motor", shooterMotor);
    	LiveWindow.addSensor("Shooter",  "sensor", shooterSensor);
        LiveWindow.run();
        
        //while(true) {
        //	Thread.sleep(50);
        	double speed = Math.min(SmartDashboard.getNumber("DB/Slider 0", 0.2), 1.0);
        	System.out.println("Setting" + speed);
        	shooterMotor.set(speed);
        	SmartDashboard.putNumber("Encoder", shooterSensor.getRate());
        //}
    }
}