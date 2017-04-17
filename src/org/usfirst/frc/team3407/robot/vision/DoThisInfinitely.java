package org.usfirst.frc.team3407.robot.vision;

import org.usfirst.frc.team3407.robot.vision.sendToRoborio;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DoThisInfinitely {
	public static void main(String[] args){
		sendToRoborio pi = new sendToRoborio();
		while (true){
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException x){
				
			}
			pi.sendToRio();
		}
	}
}
