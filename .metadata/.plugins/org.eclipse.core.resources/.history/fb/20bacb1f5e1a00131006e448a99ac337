
package ray.shader;

import ray.IntersectionRecord;
import ray.Ray;
import ray.Scene;
import ray.Workspace;
import ray.light.Light;
import ray.math.Color;
import ray.math.Vector3;

/**
 * A Lambertian material scatters light equally in all directions. 
 *
 * @author ags
 */
public class Lambertian extends Shader {

	/** The color of the surface. */
	protected final Color diffuseColor = new Color(1, 1, 1);
	public void setDiffuseColor(Color inDiffuseColor) { diffuseColor.set(inDiffuseColor); }

	public Lambertian() { }
	
	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "lambertian: " + diffuseColor;
	}

	/**
	 * Evaluate the intensity for a given intersection using the Lambert shading model.
	 */
	@Override
	public void shade(Color outIntensity, Scene scene, Workspace workspace) {
		IntersectionRecord record = workspace.intersectionRecord;
		Vector3 incoming = workspace.incomingDirection;
		Ray shadowRay = workspace.shadowRay;
		Color color = workspace.color;
		// TODO: Fill in this function.
		// 1) Loop through each light in the scene.
		// 2) If the intersection point is shadowed, skip the calculation for the light.
		//	  See Shader.java for a useful shadowing function.
		// 3) Compute the incoming direction by subtracting
		//    the intersection point from the light's position.
		// 4) Compute the color of the point using the Lambert shading model. Add this value
		//    to the output.
		
	}

}