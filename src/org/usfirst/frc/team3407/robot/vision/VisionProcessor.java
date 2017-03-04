package org.usfirst.frc.team3407.robot.vision;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class VisionProcessor {

	private static final double HEIGHT_RATIO = 1.25d;
	private static final int X_MATCH_THRESHOLD = 15;
	private static final int WIDTH_MATCH_THRESHOLD = 15;
	private static final double HEIGHT_RATIO_MATCH_THRESHOLD = 0.25d;

	private Rect target = null;
	private VisionThread thread = null;
	private volatile boolean enable = false;
	private Object targetLock = new Object();
	
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
	
	private boolean processTarget(ArrayList<MatOfPoint> contours) {
		
		int contourCount = contours.size();
		
		System.out.println("processTarget(): countours=" + contourCount);

		Rect upper = null;
		Rect lower = null;
		for(int i = 0;(i < (contourCount - 1)) && (upper == null);i++) {
			Rect rectI = Imgproc.boundingRect(contours.get(i));
			for(int j = (i + 1);j < contourCount;j++) {
				Rect rectJ = Imgproc.boundingRect(contours.get(j));			
				if (validateTarget(rectI, rectJ)) {
					upper = rectI;
					lower = rectJ;
					break;
				}
				else if (validateTarget(rectJ, rectI)) {
					upper = rectJ;
					lower = rectI;
					break;
				}
			}
		}
			
		if (upper != null) {
			synchronized (targetLock) {
				target = new Rect(upper.x, upper.y, upper.width, lower.height + (lower.y - upper.y));
			}
		}
		
		return (upper != null);
	}
	
	private boolean validateTarget(Rect upper, Rect lower) {
		
		// Check height
		if(upper.y > lower.y) {
			return false;
		}

		int xDiff = Math.abs(upper.x - lower.x);
		int widthDiff = Math.abs(upper.width - lower.width);
		double heightRatio = ((double) upper.height) / ((double) lower.height);

		double heightRatioDiff = Math.abs(HEIGHT_RATIO - heightRatio);

		//System.out.println("validateTarget(): xDiff=" + xDiff + " widthDiff=" + widthDiff + " heightRatio="
		//		+ heightRatio + " heightRatioDiff=" + heightRatioDiff);

		boolean matches = (xDiff < X_MATCH_THRESHOLD) && (widthDiff < WIDTH_MATCH_THRESHOLD)
				&& (heightRatioDiff < HEIGHT_RATIO_MATCH_THRESHOLD);

		return matches;
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
	
	private class Listener implements VisionRunner.Listener<Pipeline> {

		private int count = 0;
		private int detectCount = 0;
		
		@Override
		public void copyPipelineOutputs(Pipeline pipeline) {
			
			ArrayList<MatOfPoint> pipelineOutput = pipeline.delegate.filterContoursOutput();			
	        boolean found = processTarget(pipelineOutput);
	        if (found) {
	        	detectCount++;
	        }
			count++;
			if((count % 50) == 0) {
				System.out.println(Thread.currentThread().getName() + " Pipeline: count=" + count + " detections=" + detectCount);
			}
		}
	}
}
