
package ray.tracer;

import ray.Image;
import ray.IntersectionRecord;
import ray.Ray;
import ray.Scene;
import ray.Workspace;
import ray.camera.Camera;
import ray.math.Color;
import ray.math.Point3;
import ray.math.Vector3;
import ray.shader.Shader;

/**
 * A simple ray tracer.
 *
 * @author ags, pramook
 */
public class BasicRayTracer extends RayTracer {

	/**
	 * The main method takes all the parameters and assumes they are input files
	 * for the ray tracer. It tries to render each one and write it out to a PNG
	 * file named <input_file>.png.
	 *
	 * @param args
	 */
	public static final void main(String[] args) {
		BasicRayTracer rayTracer = new BasicRayTracer();
		rayTracer.run("data/scenes/basic_ray_tracer", args);
	}		

	/**
	 * The renderImage method renders the entire scene.
	 *
	 * @param scene The scene to be rendered
	 */
	@Override
	protected void renderImage(Scene scene) {

		// Get the output image
		Image image = scene.getImage();
		Camera cam = scene.getCamera();

		// Set the camera aspect ratio to match output image
		int width = image.getWidth();
		int height = image.getHeight();
		double dWidth = (double)width;
		double dHeight = (double)height;

		// Timing counters
		long startTime = System.currentTimeMillis();

		// Do some basic setup
		Workspace work = new Workspace();
		Ray ray = work.eyeRay;
		Color pixelColor = work.pixelColor;
		Color rayColor = work.rayColor;

		int total = height * width;
		int counter = 0;
		int lastShownPercent = 0;

		// Loop through each pixel.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				// TODO: Compute the viewing ray,
				//       and call shadeRay on it to get the ray's color.
<<<<<<< HEAD
				double adjU = x/dWidth;
				double adjV = y/dHeight;
				cam.getRay(ray, adjU, adjV);
				shadeRay(rayColor, scene, ray, work);
				
				
				
				//TODO: remove this when code works
				if (scene.getAnyIntersection(ray)){
					pixelColor.set(new Color(0, 255, 0));
					}
				else{
					pixelColor.set(new Color(255, 0, 0));
					}
				//TODO: remove this when code works ^^^^^^^

=======
				double r = 1;
				double l = 0;
				double t = 1;
				double b = 0;
				double adjU = l + (r-l)*(x + 0.5)/dWidth;
				double adjV = b + (t-b)*(y + 0.5)/dHeight;
				cam.getRay(ray, adjU, adjV);
				shadeRay(pixelColor, scene, ray, work);
		
>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c
				image.setPixelColor(pixelColor, x, y);

				counter ++;
				if((int)(100.0 * counter / total) != lastShownPercent) {
					lastShownPercent = (int)(100.0*counter / total);
					System.out.println(lastShownPercent + "%");
				}
			}
		}

		// Output time
		long totalTime = (System.currentTimeMillis() - startTime);
		System.out.println("Done.  Total rendering time: "
				+ (totalTime / 1000.0) + " seconds");

	}

	/**
	 * This method returns the color along a single ray in outColor.
	 *
	 * @param outColor output space
	 * @param scene the scene
	 * @param ray the ray to shade
	 */
	public void shadeRay(Color outColor, Scene scene, Ray ray, Workspace workspace) 
	{
		// Reset the output color
		outColor.set(scene.getBackColor());

		IntersectionRecord intersectionRecord = workspace.intersectionRecord;
		Vector3 outgoing = workspace.outgoingDirection;

		// TODO: Compute the color of the intersection point.
		// 1) Find the first intersection of "ray" with the scene.
		// Record intersection in intersectionRecord. If it doesn't hit anything, just return.
		if (!scene.getFirstIntersection(intersectionRecord, ray)) {
			return;
<<<<<<< HEAD
		}
=======
		}	
>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c
		// 2) Compute the direction of outgoing light, by subtracting the
		//	  intersection point from the origin of the ray.
		outgoing.sub(ray.origin, intersectionRecord.location);
		// 3) Get the shader from the intersection record.
		Shader recordShader = intersectionRecord.surface.getShader();
		// 4) Call the shader's shade() method to set the color for this ray.
<<<<<<< HEAD
=======
		
>>>>>>> 91cb0647527d71242e3026dfc350eedcc383472c
		recordShader.shade(outColor, scene, workspace);
	}
}