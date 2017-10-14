package org.usfirst.frc.team3407.robot.vision;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class PegEvaluator extends TargetEvaluator {
	
	private static final double SEPERATION_RATIO = 3.0d;
	private static final int Y_MATCH_THRESHOLD = 10;
	private static final int WIDTH_MATCH_THRESHOLD = 10;
	private static final int HEIGHT_MATCH_THRESHOLD = 10;
	private static final double SEPERATION_RATIO_MATCH_THRESHOLD = 1.0;
	
	@Override
	public boolean process(ArrayList<MatOfPoint> contours) {
		
		int contourCount = contours.size();
		
		System.out.println("process(): countours=" + contourCount);

		Rect left = null;
		Rect right = null;
		for(int i = 0;(i < (contourCount - 1)) && (left == null);i++) {
			Rect rectI = Imgproc.boundingRect(contours.get(i));
			for(int j = (i + 1);j < contourCount;j++) {
				Rect rectJ = Imgproc.boundingRect(contours.get(j));			
				if (validateTarget(rectI, rectJ)) {
					left = rectI;
					right = rectJ;
					break;
				}
				else if (validateTarget(rectJ, rectI)) {
					left = rectJ;
					right = rectI;
					break;
				}
			}
		}
			
		setTarget((left == null) ? null : new Rect(left.x, left.y, (right.width - left.width) + left.width, left.height));
		
		return (left != null);
	}
	
	private boolean validateTarget(Rect left, Rect right) {
	
		// Check position
		if(left.x > right.x) {
			return false;
		}

		int widthDiff = Math.abs(left.width - right.width);
		int heightDiff = Math.abs(left.height - right.height);
		int yDiff = Math.abs(left.y - right.y);
		int xDiff = right.x - left.x;
		double seperationRatio = ((double) xDiff) / ((double) left.width);
		double seperationRatioDiff = Math.abs(seperationRatio - SEPERATION_RATIO);

		System.out.println("validateTarget(): xDiff=" + xDiff + " yDiff=" + yDiff + 
				" widthDiff=" + widthDiff + 
				" heightDiff=" + heightDiff + 
				" seperationRatio=" + seperationRatio +
				" seperationRatiDiffo=" + seperationRatioDiff);

		boolean matches = (yDiff < Y_MATCH_THRESHOLD) && (widthDiff < WIDTH_MATCH_THRESHOLD) && 
				(heightDiff < HEIGHT_MATCH_THRESHOLD) && (seperationRatioDiff < SEPERATION_RATIO_MATCH_THRESHOLD);

		return matches;
	}

}
