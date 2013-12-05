
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
		outIntensity.set(new Color(0,0,0));
		// 1) Loop through each light in the scene.
<<<<<<< HEAD
			for (Light l : scene.getLights()){
				// 2) If the intersection point is shadowed, skip the calculation for the light.
				//	  See Shader.java for a useful shadowing function.
				if (!isShadowed(scene,l,record,shadowRay)) {
					// 3) Compute the incoming direction by subtracting
					//    the intersection point from the light's position.
					incoming.sub(l.position, record.location);
					// 4) Compute the color of the point using the Lambert shading model. Add this value
					//    to the output.
					Color lintense = new Color(l.intensity);
					Color scolor = new Color(color);
					
					//Formula for outIntensity = scolor*lintense*max(0,normal dot incoming)
					double ndotl = record.normal.dot(incoming);
					lintense.scale(Math.max(0,ndotl));
					scolor.scale(lintense);
					outIntensity.add(scolor);
					
=======
		for (Light l : scene.getLights()){
			color.set(new Color(0,0,0));
			// 2) If the intersection point is shadowed, skip the calculation for the light.
			//	  See Shader.java for a useful shadowing function.
			if (!isShadowed(scene, l, record, shadowRay)) {
				// 3) Compute the incoming direction by subtracting
				//    the intersection point from the light's position.
				incoming.sub(l.position, record.location);
				incoming.normalize();
				// 4) Compute the color of the point using the Lambert shading model. Add this value
				//    to the output.
				Color I = new Color(l.intensity);
				Color kd = new Color(diffuseColor);
				
				record.normal.normalize();
				double ndotl = record.normal.dot(incoming);
				I.scale(Math.max(0,ndotl));
				kd.scale(I);
				
				color.add(kd);
				outIntensity.add(color);
>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c
			}
		}
	}

}