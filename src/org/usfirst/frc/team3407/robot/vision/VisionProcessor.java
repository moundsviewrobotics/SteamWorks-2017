package org.usfirst.frc.team3407.robot.vision;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class VisionProcessor {

	private Rect target = null;
	private VisionThread thread = null;
	private Object targetLock = new Object();
	
	public void start(VideoSource camera) {
		if(thread == null) {
		    thread = new VisionThread(camera, new GripPipeline(), new Listener());
		    thread.start();
		}		
	}
	
	public synchronized void stop() {
		//thread.d
		//threadRunning = false;
	}
	
	public synchronized Rect getTarget() {
		synchronized (targetLock) {
			return target;
		}
	}
	
	public synchronized Point getTargetCenter() {
		synchronized (targetLock) {
			return (target == null) ? null : new Point(target.x + (target.width / 2), target.y + (target.height / 2));
		}
	}
	
	private Rect processTarget(ArrayList<MatOfPoint> potentialTargets) {
        return potentialTargets.isEmpty() ? null : Imgproc.boundingRect(potentialTargets.get(0));
	}
	
	private class Listener implements VisionRunner.Listener<GripPipeline> {

		private int count = 0;
		private int detectCount = 0;
		
		@Override
		public void copyPipelineOutputs(GripPipeline pipeline) {
			
			ArrayList<MatOfPoint> pipelineOutput = pipeline.filterContoursOutput();			
			count++;
			detectCount += pipelineOutput.size();
			if((count % 500) == 0) {
				System.out.println("Pipeline: count=" + count + " detections=" + detectCount);
			}
	        Rect t = processTarget(pipelineOutput);
	        synchronized (targetLock) {
	        	target = t;
	        }			
		}
	}
}
