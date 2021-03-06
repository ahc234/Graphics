
package ray.camera;

import ray.Ray;
import ray.math.Point3;
import ray.math.Vector3;

/**
 * Represents a camera with perspective view.
 * For this camera, the view window corresponds to a rectangle on a
 * plane perpendicular to projNormal but at distance projDistance from
 * viewPoint in the direction of viewDir. A ray with its origin at viewPoint
 * going in the direction of viewDir should intersect the center
 * of the image plane. Given u and v, you should compute a point on the
 * rectangle corresponding to (u,v), and create a ray from viewPoint that
 * passes through the computed point.
 */
public class PerspectiveCamera extends Camera {
  
  protected double projDistance = 1.0;
  public void setprojDistance(double projDistance) { this.projDistance = projDistance; }
  
  /*
   * Derived values that are computed before ray generation.
   * basisU, basisV, and basisW form an orthonormal basis.
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
	if (projNormal.dot(viewDir) > 0) {
  		projNormal.scale(-1.0);
  	}
  	if ( projNormal.length() == 0 ) {
  		setProjNormal(viewDir);
	    //   2. set basisW to be parallel to projection normal but pointing to the opposite direction.
  		basisW.set(projNormal);
  		basisW.scale(-1.0); //multiply by negative 1
  }else	basisW.set(projNormal);
  	
  basisW.normalize();
	    //   3. set basisU to be parallel to the image's U (horizontal) axis.
  	basisU.cross(viewUp, basisW);
  	basisU.normalize();
  	
	    //   4. set basisV to be parallel to the image's V (vertical) axis.
  	basisV.cross(basisW, basisU);
 
    
    initialized = true;
  }
  
  /**
   * Set outRay to be a ray from the camera through a point in the image.
   *
   * @param outRay The output ray (not normalized)
   * @param inU The u coord of the image point (range [0,1])
   * @param inV The v coord of the image point (range [0,1])
   */
  public void getRay(Ray outRay, double inU, double inV) {
    if (!initialized) initView();
    
	inU = viewWidth/2 * (2 * inU -1);
	inV = viewHeight/2 * (2 * inV -1);
	Vector3 tempU = new Vector3(basisU);
	tempU.scale(inU);
	Vector3 tempV = new Vector3(basisV);
	tempV.scale(inV);
	Vector3 newDir = new Vector3(this.viewDir);
	newDir.normalize();
	newDir.scale(projDistance);
	newDir.add(tempU);
	newDir.add(tempV); // newDir = -dw + uu + vv
	
<<<<<<< HEAD
	//System.out.println(tempU);
	//System.out.println(tempV);
	
	outRay.set(this.viewPoint, newDir);
    
//	inU = viewWidth/2 * (2 * inU -1);
//	inV = viewHeight/2 * (2 * inV -1);
//	Vector3 tempU = new Vector3(basisU);
//	tempU.scale(inU);
//	Vector3 tempV = new Vector3(basisV);
//	tempV.scale(inV);
//	Point3 newOrigin = new Point3(this.viewPoint);
//	newOrigin.add(newOrigin, tempU);
//	newOrigin.add(newOrigin, tempV);
//	
//	System.out.println(viewDir);
//	
//	outRay.set(newOrigin, viewDir);
=======
	outRay.set(this.viewPoint, newDir);

>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c

    

  }
}