package org.usfirst.frc.team3407.robot.vision;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

public class VisionProcessor {

	private TargetEvaluator targetEvaluator = new BoilerEvaluator();
	private VisionThread thread = null;
	private volatile boolean enable = false;
	
	public TargetEvaluator getTargetEvaluator() {
		return targetEvaluator;
	}
	
	public void start(VideoSource camera) {
		enable = true;
		if(thread == null) {
		    thread = new VisionThread(camera, new Pipeline(), new Listener());
		    thread.start();
		}		
	}
	
	public synchronized void stop() {
		if(thread != null) {
			thread.interrupt();
			thread = null;
		}
		enable = false;
	}
	
	private class Pipeline implements VisionPipeline {
		GripPipeline delegate = new GripPipeline();

		@Override
		public void process(Mat image) {
			if(enable) {
				delegate.process(image);
			}
		}		
	}
	
	public class Listener implements VisionRunner.Listener<Pipeline> {

		private int count = 0;
		private int detectCount = 0;
		
		@Override
		public void copyPipelineOutputs(Pipeline pipeline) {
			
			ArrayList<MatOfPoint> pipelineOutput = pipeline.delegate.filterContoursOutput();			
	        boolean found = targetEvaluator.process(pipelineOutput);
	        if (found) {
	        	detectCount++;
	        }
			count++;
			if((count % 50) == 0) {
				System.out.println(Thread.currentThread().getName() + " Pipeline: count=" + count + " detections=" + detectCount);
			}
		}
		public ArrayList<MatOfPoint> returnOutput(Pipeline pipeline){
			ArrayList<MatOfPoint> pipelineOutput = pipeline.delegate.filterContoursOutput();
			return pipelineOutput;
		}
	}
}
