package ray.camera;

import ray.Ray;
import ray.math.Point3;
import ray.math.Vector3;

/**
 * Represents a camera object. This class is responsible for generating rays that are intersected
 * with the scene.
 */
public abstract class Camera {
	/*
	 * Fields that are read in from the input file to describe the camera.
	 * You'll probably want to store some derived values to make ray generation easy.
	 */
	
	/**
	 * The position of the eye.
	 */
	protected final Point3 viewPoint = new Point3();
	public void setViewPoint(Point3 viewPoint) { this.viewPoint.set(viewPoint); }
	
	/**
	 * The direction the eye is looking.
	 */
	protected final Vector3 viewDir = new Vector3(0, 0, -1);
	public void setViewDir(Vector3 viewDir) { this.viewDir.set(viewDir); }
	
	/**
	 * The upwards direction from the viewer's perspective.
	 */
	protected final Vector3 viewUp = new Vector3(0, 1, 0);
	public void setViewUp(Vector3 viewUp) { this.viewUp.set(viewUp); }
	
	/**
	 * The normal of the image plane. By default this should be set to be the
	 * same as the view direction.
	 */
	protected final Vector3 projNormal = new Vector3(0, 0, 0);
	public void setProjNormal(Vector3 projNormal) { this.projNormal.set(projNormal); }
	
	/**
	 * The width of the viewing window.
	 */
	protected double viewWidth = 1.0;
	public void setViewWidth(double viewWidth) { this.viewWidth = viewWidth; }
	
	/**
	 * The height of the viewing window.
	 */
	protected double viewHeight = 1.0;
	public void setViewHeight(double viewHeight) { this.viewHeight = viewHeight; }
	
	/**
	 * Generate a ray that points out into the scene for the given (u,v) coordinate.
	 * This coordinate corresponds to a point on the viewing window, where (0,0) is the
	 * lower left corner and (1,1) is the upper right.
	 * @param outRay A space to return the output ray
	 * @param u The horizontal coordinate (0 is left, 1 is right)
	 * @param v The vertical coordinate (0 is bottom, 1 is top)
	 */
	public abstract void getRay(Ray outRay, double u, double v);
}
