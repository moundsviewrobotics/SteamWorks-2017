package org.usfirst.frc.team3407.robot.vision;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class BoilerEvaluator extends TargetEvaluator {

	private static final double HEIGHT_RATIO = 1.25d;
	private static final int X_MATCH_THRESHOLD = 15;
	private static final int WIDTH_MATCH_THRESHOLD = 15;
	private static final double HEIGHT_RATIO_MATCH_THRESHOLD = 0.25d;

	@Override
	public boolean process(ArrayList<MatOfPoint> contours) {
		
		int contourCount = contours.size();
		
		System.out.println("process(): countours=" + contourCount);

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
			setTarget(new Rect(upper.x, upper.y, upper.width, lower.height + (lower.y - upper.y)));
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

		boolean matches = (xDiff < X_MATCH_THRESHOLD) && (widthDiff < WIDTH_MATCH_THRESHOLD) && 
				(heightRatioDiff < HEIGHT_RATIO_MATCH_THRESHOLD);

		return matches;
	}
}
