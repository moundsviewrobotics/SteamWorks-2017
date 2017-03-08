package org.usfirst.frc.team3407.robot.vision;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;

public abstract class TargetEvaluator {

	private Rect target = null;

	public synchronized Rect getTarget() {
		return (target == null) ? null : target.clone();
	}
	
	protected synchronized void setTarget(Rect t) {
		target = t;
	}

	public synchronized Point getTargetCenter() {
		Rect t = getTarget();
		return (t == null) ? null : new Point(t.x + (t.width / 2), t.y + (t.height / 2));
	}

	public double getTargetHorizontalOffset(Point point) {
		Point targetPoint = getTargetCenter();		
		return targetPoint.x - point.x;
	}

	public abstract boolean process(ArrayList<MatOfPoint> contours);
}
