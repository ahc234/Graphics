
package ray.shader;

import ray.IntersectionRecord;
import ray.Ray;
import ray.Scene;
import ray.Workspace;
import ray.light.Light;
import ray.math.Color;
import ray.math.Vector3;

/**
 * A Phong material has a diffuse component plus a specular highlight.
 *
 * @author ags, pramook
 */
public class Phong extends Shader {

	/** The color of the diffuse reflection. */
	protected final Color diffuseColor = new Color(1, 1, 1);
	public void setDiffuseColor(Color diffuseColor) { this.diffuseColor.set(diffuseColor); }

	/** The color of the specular reflection. */
	protected final Color specularColor = new Color(1, 1, 1);
	public void setSpecularColor(Color specularColor) { this.specularColor.set(specularColor); }

	/** The exponent controlling the sharpness of the specular reflection. */
	protected double exponent = 1.0;
	public void setExponent(double exponent) { this.exponent = exponent; }

	public Phong() { }

	/**
	 * @see Object#toString()
	 */
	public String toString() {    
		return "phong " + diffuseColor + " " + specularColor + " " + exponent + " end";
	}

	/**
	 * Evaluate the intensity for a given intersection using the Phong shading model.
	 */
	@Override
	public void shade(Color outIntensity, Scene scene, Workspace workspace) {
		Vector3 incoming = workspace.incomingDirection;
		Vector3 outgoing = workspace.outgoingDirection;
		IntersectionRecord record = workspace.intersectionRecord;
		Ray shadowRay = workspace.shadowRay;
		Color color = workspace.color;
		
		// TODO: Fill in this function.
<<<<<<< HEAD
		outIntensity = new Color(0,0,0);
		color = new Color(0,0,0);
		// 1) Loop through each light in the scene.
		for (Light l : scene.getLights()){
		// 2) If the intersection point is shadowed, skip the calculation for the light.
		//	  See Shader.java for a useful shadowing function.
			if (!isShadowed(scene,l,record,shadowRay)) {
				
=======
		outIntensity.set(new Color(0,0,0));
		// 1) Loop through each light in the scene.
		for (Light l : scene.getLights()){
			color.set(new Color(0,0,0));
		// 2) If the intersection point is shadowed, skip the calculation for the light.
		//	  See Shader.java for a useful shadowing function.

			if (!isShadowed(scene, l, record, shadowRay)) {
>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c
		// 3) Compute the incoming direction by subtracting
		//    the intersection point from the light's position.
				incoming.sub(l.position, record.location);
		// 4) Compute the color of the point using the Phong shading model. Add this value
		//    to the output.
				
<<<<<<< HEAD
				double ndotwi = record.normal.dot(incoming);
				
				if (ndotwi >= 0) {
					Vector3 h = new Vector3 (incoming);
					h.add(outgoing);
					h.normalize();
					
=======
				incoming.normalize();
				outgoing.normalize();
				
				double ndotwi = record.normal.dot(incoming);
				
				if (ndotwi >= 0) {
					Vector3 h = new Vector3(incoming);
					h.add(outgoing);
					h.normalize();
>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c
					double ndoth = record.normal.dot(h);
					Color kd = new Color(diffuseColor);
					Color ks = new Color(specularColor);
					Color I = new Color(l.intensity);
					
<<<<<<< HEAD
					I.scale(Math.max(0, ndotwi));
					kd.scale(I);
					
					I = new Color(l.intensity);
					I.scale(Math.pow((Math.max(0, ndoth)), exponent));
					ks.scale(I);
					
					kd.add(ks);
					
					color.add(kd);
				}

				outIntensity.add(color);
			}
		}
		
=======
					kd.scale(Math.max(ndotwi, 0));
					kd.scale(I);
					ks.scale(Math.pow(Math.max(ndoth, 0), exponent));
					ks.scale(I);
					
					kd.add(ks);
					color.add(kd);
					
				}
				outIntensity.add(color);
			}
		}
>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c
	}

}