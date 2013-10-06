package cs4620.shape;

import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL2;

public class Cylinder extends TriangleMesh
{
	public Cylinder(GL2 gl)
	{
		super(gl);
	}
	
	@Override
	public void buildMesh(GL2 gl, float tolerance)
	{
		// TODO (Scene P2): Implement mesh generation for Cylinder. Your code should
		// fill arrays of vertex positions/normals and vertex indices for triangles/lines
		// and put this information in the GL buffers using the
		//   set*()
		// methods from TriangleMesh.
		
		float numSectors = (float)(2*Math.PI) / tolerance;
		int rowLength = (int)(Math.ceil(numSectors));
		int numPoints = (rowLength+1)*2 + 2;
		
		float[] vertexCoords = new float[numPoints*3];
		float[] normalCoords = new float[numPoints*3];
		int[] triangleVerts = new int[numPoints*3*2];
		int[] wireframeVerts = new int[numPoints*2*2];
		
		float x = 0;
		float y = 0;
		float z = 0;
		
		int rowPos = 0;
		
		//Center point for the top face
		vertexCoords[0] = 0;
		vertexCoords[1] = 1;
		vertexCoords[2] = 0;
		
		//Center point for the bottom face
		vertexCoords[(rowLength)*3] = 0;
		vertexCoords[(rowLength)*3 +2] = -1;
		vertexCoords[(rowLength)*3 +2] = 0;
		
		//Normal for the top face center point
		normalCoords[0] = 0;
		normalCoords[1] = 1;
		normalCoords[2] = 0;
		
		//Normal for the bottom face center point
		normalCoords[(rowLength+1)*3] = 0;
		normalCoords[(rowLength+1)*3+1] = -1;
		normalCoords[(rowLength+1)*3+2] = 0;
		
		//Populate the points and normals for the top and bottom faces
		for (float u = 0; u <= rowLength; u++) {
			//Top face points
			rowPos = (int)((u+1)*3);
			
			x = (float)(Math.cos((u/rowLength) * 2 * Math.PI));
			y = (float)1;
			z = (float)(Math.sin((u/rowLength) * 2 *  Math.PI));
			
			vertexCoords[rowPos] = x;
			vertexCoords[rowPos+1] = y;
			vertexCoords[rowPos+2] = z;
			
			//Top face normals
			normalCoords[rowPos] = 0;
			normalCoords[rowPos+1] = 1;
			normalCoords[rowPos+2] = 0;
			
			//System.out.println("rowLength: " + rowLength + "; rowPos: " + rowPos);
			
			//Bottom face points
			rowPos = (int)((u + rowLength + 1)*3);

			y = (float)-1;
			
			vertexCoords[rowPos] = x;
			vertexCoords[rowPos+1] = y;
			vertexCoords[rowPos+2] = z;
			
			//Bottom face normals
			normalCoords[rowPos] = 0;
			normalCoords[rowPos+1] = -1;
			normalCoords[rowPos+2] = 0;
		}
	
		//Populate the triangles and lines
		for (int u = 0; u < rowLength; u++) {
			//Top face triangles
			rowPos = (int)(u*3);
			
			triangleVerts[rowPos] = 0;
			triangleVerts[rowPos+1] = u+1;
			triangleVerts[rowPos+2] = u+2;
			
			//Bottom face triangles
			rowPos = ((u + rowLength)*3);
			
			triangleVerts[rowPos] = rowLength + 1;
			triangleVerts[rowPos+1] = u + rowLength + 3;
			triangleVerts[rowPos+2] = u + rowLength + 4;
			
			//Top face lines
			int vertPos = (u*4);
			
			wireframeVerts[vertPos] = 0;
			wireframeVerts[vertPos+1] = u+1;
				
			wireframeVerts[vertPos+2] = u+1;
			wireframeVerts[vertPos+3] = u+2;
			
			//Bottom face lines
			vertPos = (u + rowLength)*4;
			
			wireframeVerts[vertPos] = rowLength + 2;
			wireframeVerts[vertPos+1] = u + rowLength + 2;
				
			wireframeVerts[vertPos+2] = u + rowLength + 2;
			wireframeVerts[vertPos+3] = u + rowLength + 3;
		}
		setVertices(gl, vertexCoords);
		setNormals(gl, normalCoords);
		setTriangleIndices(gl, triangleVerts);
		setWireframeIndices(gl, wireframeVerts);
		
	}

	@Override
	public Object getYamlObjectRepresentation()
	{
		Map<Object,Object> result = new HashMap<Object, Object>();
		result.put("type", "Cylinder");
		return result;
	}
}
