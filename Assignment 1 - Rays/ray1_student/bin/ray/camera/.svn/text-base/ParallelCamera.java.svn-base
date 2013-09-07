package ray.camera;

import ray.Ray;
import ray.math.Vector3;


/**
 * Represents a camera with a parallel view.
 * For this camera, all rays should start at the viewing window and have a direction
 * parallel to viewDir. The viewing window's center is defined by viewPoint; all rays
 * will have their origins offset from this point. Note that the viewing window's normal
 * is defined by projNormal, which may be different than the viewing direction.
 *
 */
public class ParallelCamera extends Camera {
	
	/*
	 * Derived values that are computed before ray generation.
	 * basisU, basisV, and basisW form an orthonormal basis.
	 * 
	 * basisW is parallel to projNormal.
	 */
	protected final Vector3 basisU = new Vector3();
	protected final Vector3 basisV = new Vector3();
	protected final Vector3 basisW = new Vector3();
	protected final Vector3 centerDir = new Vector3();

	// Has the view been initialized?
	protected boolean initialized = false;
	
	/**
	 * Initialize the derived view variables to prepare for using the camera.
	 */
    public void initView() {
	    // TODO: fill in this function. Remember to check Camera.java for inherited fields.
	    // Hint:
    	//   1. check projNormal; if its length is 0, set it to the view direction.
	    //   2. set basisW to be parallel to projection normal but pointing to the opposite direction.
	    //   3. set basisU to be parallel to the image's U (horizontal) axis.
	    //   4. set basisV to be parallel to the image's V (vertical) axis.
    	
	    
	    initialized = true;
    }


    /**
     * Set outRay to be a ray originating from a point in the image.
     *
     * @param outRay The output ray (not normalized)
     * @param inU The u coord of the image point (range [0,1])
     * @param inV The v coord of the image point (range [0,1])
     */
	@Override
	public void getRay(Ray outRay, double inU, double inV) {
		if (!initialized) initView();
	    
	    // TODO: fill in this function.

	}
}
