package com.hiveworkshop.rms.editor.model;

import com.hiveworkshop.rms.parsers.mdlx.MdlxExtent;
import com.hiveworkshop.rms.util.Vertex3;

/**
 * MinimumExt,MaximumExt,BoundsRad
 *
 * Eric Theller 11/10/2011
 */
public class ExtLog {
	public static final double NO_BOUNDS_RADIUS = -99;
	private Vertex3 minimumExtent;
	private Vertex3 maximumExtent;
	private double boundsRadius = NO_BOUNDS_RADIUS;
	static double DEFAULT_BOUNDSRADIUS = 100.00;
	static Vertex3 DEFAULT_MINEXT = new Vertex3(-100, -100, -100);
	static Vertex3 DEFAULT_MAXEXT = new Vertex3(100, 100, 100);

	public ExtLog(final double boundsRadius) {
		this.boundsRadius = boundsRadius;
	}

	public ExtLog(final MdlxExtent extent) {
		boundsRadius = extent.boundsRadius;
		minimumExtent = new Vertex3(extent.min);
		maximumExtent = new Vertex3(extent.max);
	}

	public MdlxExtent toMdlx() {
		final MdlxExtent extent = new MdlxExtent();

		extent.boundsRadius = (float)boundsRadius;
		extent.min = minimumExtent.toFloatArray();
		extent.max = maximumExtent.toFloatArray();

		return extent;
	}

	public ExtLog(final Vertex3 minE, final Vertex3 maxE) {
		minimumExtent = minE;
		maximumExtent = maxE;
	}

	public ExtLog(final Vertex3 minE, final Vertex3 maxE, final double boundsRad) {
		minimumExtent = minE;
		maximumExtent = maxE;
		boundsRadius = boundsRad;
	}

	public ExtLog(final float[] minE, final float[] maxE, final double boundsRad) {
		minimumExtent = new Vertex3(minE);
		maximumExtent = new Vertex3(maxE);
		boundsRadius = boundsRad;
	}

	public ExtLog(final ExtLog other) {
		minimumExtent = other.minimumExtent;
		maximumExtent = other.maximumExtent;
		boundsRadius = other.boundsRadius;
	}

	public void setMinExt(final Vertex3 v) {
		minimumExtent = v;
	}

	public void setMaxExt(final Vertex3 v) {
		maximumExtent = v;
	}

	public void setBounds(final double b) {
		boundsRadius = b;
	}

	public boolean hasBoundsRadius() {
		return boundsRadius != NO_BOUNDS_RADIUS;
	}

	public Vertex3 getMinimumExtent() {
		return minimumExtent;
	}

	public void setMinimumExtent(final Vertex3 minimumExtent) {
		this.minimumExtent = minimumExtent;
	}

	public Vertex3 getMaximumExtent() {
		return maximumExtent;
	}

	public void setMaximumExtent(final Vertex3 maximumExtent) {
		this.maximumExtent = maximumExtent;
	}

	public double getBoundsRadius() {
		return boundsRadius;
	}

	public void setBoundsRadius(final double boundsRadius) {
		this.boundsRadius = boundsRadius;
	}
}
