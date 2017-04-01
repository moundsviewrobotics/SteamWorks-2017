package org.usfirst.frc.team3407.robot;

import org.usfirst.frc.team3407.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3407.robot.subsystems.Feeder;
import org.usfirst.frc.team3407.robot.subsystems.loader;
//import org.usfirst.frc.team3407.robot.subsystems.linearSlide;
import org.usfirst.frc.team3407.robot.subsystems.shooterPID;
import org.usfirst.frc.team3407.robot.vision.VisionProcessor;
import org.usfirst.frc.team3407.robot.commands.AutonomousPos1;
import org.usfirst.frc.team3407.robot.commands.AutonomousPos2;
import org.usfirst.frc.team3407.robot.commands.AutonomousPos3;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static Drivetrain drivetrain = new Drivetrain();
	public static loader ballLoader = new loader();
	//public static linearSlide slide = new linearSlide();
	public static shooterPID shooterpid = new shooterPID();
	
	private static VisionProcessor visionProcessor = new VisionProcessor();
	private UsbCamera camera;

	private static final String SOFTWARE_VERSION = "Steamworks-2017-1.0";
	private static final String SOFTWARE_DATE = "DATE(02/21/17)";
    Command autonomousCommand;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI(); 
        SmartDashboard.putString("DB/String 0", SOFTWARE_VERSION);
        SmartDashboard.putString("DB/String 5", SOFTWARE_DATE); 

        SmartDashboard.putData(shooterpid);
        
        CameraServer server = CameraServer.getInstance();
        camera = server.startAutomaticCapture("Front", 0);        
        //server.startAutomaticCapture("Back", 1);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
        visionProcessor.stop();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

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
        
    	Feeder.start();
    	shooterpid.setMotorSpeed(shooterPID.INITIAL_MOTOR_SPEED);
    	shooterpid.enable();
        String selected = SmartDashboard.getString("Auto Selector","A");
        System.out.println("SELECTED=" + selected);
        
        if(selected .equals("pos1B")) {
        	autonomousCommand = new AutonomousPos1(true);
        }
        else if(selected .equals("pos1R")){
        	autonomousCommand = new AutonomousPos1(false);
        }
        else if(selected .equals("pos2B")) {
        	autonomousCommand = new AutonomousPos2(true);
        }
        else if(selected .equals("pos2R")){
        	autonomousCommand = new AutonomousPos2(false);
        }
        else if(selected .equals("pos3")) {
        	autonomousCommand = new AutonomousPos3();
        }
        else {
        	autonomousCommand = new AutonomousPos3();
        }//TODO: make this a switch statement
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
    	//Feeder.start();
    	//shooterpid.setMotorSpeed(0.8);
    	//shooterpid.setSetpoint(1300);
    	//shooterpid.enable();
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
    
    public void testInit() {
    	//shooterpid.setMotorSpeed(shooterPID.INITIAL_MOTOR_SPEED);
    	//shooterpid.setSetpoint(shooterPID.DEFAULT_SET_POINT);
    	shooterpid.setMotorSpeed(.75);;
    	shooterpid.setSetpoint(1350);
    	shooterpid.enable();
        loader.shoot();
        //visionProcessor.start(camera);
    }
    
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {	
    	shooterpid.test();
    	
    	//Object target = visionProcessor.getTargetEvaluator().getTargetCenter();
        //SmartDashboard.putString("DB/String 0", (target == null) ? "<None>" : target.toString());
        
        LiveWindow.run();
    }
}