package ray.tracer;

import java.io.File;
import java.util.ArrayList;

import ray.Parser;
import ray.Scene;
import ray.TestFolderPath;

public abstract class RayTracer {	
	/**
	 * If filename is a directory, set testFolderPath = fn.
	 * And return a list of all .xml files inside the directory
	 * @param fn Filename or directory
	 * @return fn itself in case fn is a file, or all .xml files inside fn
	 */
	public ArrayList<String> getFileLists(String fn) {
		if(fn.endsWith("/"))
			fn.substring(0, fn.length()-1);

		File file = new File(fn);
		ArrayList<String> output = new ArrayList<String>();
		if(file.exists()) {
			if(file.isFile()) {
				// Extract the folder part of the name
				int dir_index = fn.lastIndexOf('/');
				if (dir_index > 0 && dir_index < fn.length()) {
					TestFolderPath.set(fn.substring(0, dir_index + 1));
				} else {  
					TestFolderPath.set("");
				}
				output.add(fn);
			} else {
				TestFolderPath.set(fn + "/");				
				for(String fl : file.list()) {
					if(fl.endsWith(".xml")) {
						output.add(TestFolderPath.get() + fl);
					}
				}  
			}
		} else {
			System.err.println("Warning: file does not exist: "+file);
		}
		return output;
	}

	/**
	 * The run method takes all the parameters and assumes they are input files
	 * for the ray tracer. It tries to render each one and write it out to a PNG
	 * file named <input_file>.png.
	 *
	 * @param args
	 */
	public void run(String directory, String[] args) {

		Parser parser = new Parser();
		for (int ctr = 0; ctr < args.length; ctr++) {

			ArrayList<String> fileLists = getFileLists(directory + "/" + args[ctr]);

			for (String inputFilename : fileLists) {
				String outputFilename = inputFilename + ".png";

				// Parse the input file
				Scene scene = (Scene) parser.parse(inputFilename, Scene.class);

				// Render the scene
				renderImage(scene);

				// Write the image out
				scene.getImage().write(outputFilename);
			}
		}
	}
	
	protected abstract void renderImage(Scene scene);	
}
