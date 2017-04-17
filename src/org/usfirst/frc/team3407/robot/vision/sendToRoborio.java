package org.usfirst.frc.team3407.robot.vision;

import org.usfirst.frc.team3407.robot.vision.visionLRPID;

//import edu.wpi.first.wpilibj.networktables2.*;
import edu.wpi.first.wpilibj.networktables.*;

public class sendToRoborio {
	public double dataToSend;
	//public visionLRPID pid = new visionLRPID();
	public NetworkTable piTable;
	//public String dataKey = "LR";
	public String tableKey = "PI";
	
	
	public sendToRoborio() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.34.07.1");
		piTable = NetworkTable.getTable("RIO");
		
	}
	public void sendToRio(){
		dataToSend = visionLRPID.LRControl();
		piTable.putNumber("LR", dataToSend);
	}
	

}
