package cs4620.shape;

import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL2;

public class Sphere extends TriangleMesh {

	public Sphere(GL2 gl) {
		super(gl);
	}

	@Override
	public void buildMesh(GL2 gl, float tolerance)
	{
		// TODO (Scene P2): Implement mesh generation for Sphere. Your code should
		// fill arrays of vertex positions/normals and vertex indices for triangles/lines
		// and put this information in the GL buffers using the
		//   set*()
		// methods from TriangleMesh.
		
		double numSectors = (2*Math.PI) / tolerance;
		int rowLength = (int)(Math.ceil(numSectors));
		double sideLength = (2*Math.PI/numSectors);
		int numPoints = rowLength * rowLength;
		
		double[] vertexCoords = new double[numPoints*3];
		double[] normalCoords = new double[numPoints*3];
		double[] triangleVerts = new double[numPoints*3];
		
		double x = 0;
		double y = 0;
		double z = 0;
		
		double phi = 0;
		double theta = 0;
		
		for (int v = 0; v < rowLength; v++) {
			for (int u = 0; u < rowLength; u++) {
				int rowPos = v*u*3;
				
				phi = (v/rowLength) * Math.PI;
				theta = 2 * (u/rowLength) * Math.PI;
				
				x = (Math.sin(phi) * Math.cos(theta));
				y = (Math.sin(phi) * Math.sin(theta));
				z = Math.cos(phi);
				
				vertexCoords[rowPos] = x;
				vertexCoords[rowPos+1] = y;
				vertexCoords[rowPos+2] = z;
			}
		}
		
	}

	@Override
	public Object getYamlObjectRepresentation()
	{
		Map<Object,Object> result = new HashMap<Object, Object>();
		result.put("type", "Sphere");
		return result;
	}


}
