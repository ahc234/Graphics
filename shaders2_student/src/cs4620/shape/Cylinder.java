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
		int numPoints = 4*(rowLength+1)+2;
		
		float[] vertexCoords = new float[numPoints*3];
		float[] normalCoords = new float[numPoints*3];
		float[] textureCoords = new float[numPoints*2];
		int[] triangleVerts = new int[(numPoints-2)*3];
		int[] wireframeVerts = new int[(5*(rowLength+1))*2];
		
		float x = 0;
		float y = 0;
		float z = 0;
		
		int rowPos = 0;
		
		//Center point for the top face
		int vertLength = vertexCoords.length;
		vertexCoords[(vertLength)-6] = 0;
		vertexCoords[(vertLength)-5] = 1;
		vertexCoords[(vertLength)-4] = 0;
		
		//Center point for the bottom face
		vertexCoords[(vertLength)-3] = 0;
		vertexCoords[(vertLength)-2] = -1;
		vertexCoords[(vertLength)-1] = 0;
		
		//Normal for the top face center point
		int normLength = normalCoords.length;
		normalCoords[(normLength)-6] = 0;
		normalCoords[(normLength)-5] = 1;
		normalCoords[(normLength)-4] = 0;
		
		//Normal for the bottom face center point
		normalCoords[(normLength)-3] = 0;
		normalCoords[(normLength)-2] = -1;
		normalCoords[(normLength)-1] = 0;
		
		//Texture for the top face center point
		int textLength = textureCoords.length;
		textureCoords[(textLength)-4] = 0.5f;
		textureCoords[(textLength)-3] = 0.5f;
		
		//Texture for the bottom face center point
		textureCoords[(textLength)-2] = 0.5f;
		textureCoords[(textLength)-1] = 0.5f;
		
		//Populate the points and normals for the top and bottom faces
		for (float u = 0; u <= rowLength; u++) {
			//Top face points
			rowPos = (int)(u*3);
			
			x = (float)(Math.cos((u/rowLength) * 2 * Math.PI));
			y = (float)1;
			z = (float)(Math.sin((u/rowLength) * 2 *  Math.PI));
			
			vertexCoords[rowPos] = x;
			vertexCoords[rowPos+1] = y;
			vertexCoords[rowPos+2] = z;
			
			vertexCoords[rowPos+(rowLength+1)*3] = x;
			vertexCoords[rowPos+(rowLength+1)*3+1] = y;
			vertexCoords[rowPos+(rowLength+1)*3+2] = z;
			
			//Top face normals
			normalCoords[rowPos] = x;
			normalCoords[rowPos+1] = 0;
			normalCoords[rowPos+2] = z;
			
			normalCoords[rowPos+(rowLength+1)*3] = 0;
			normalCoords[rowPos+(rowLength+1)*3+1] = 1;
			normalCoords[rowPos+(rowLength+1)*3+2] = 0;
			
			//Bottom face points
			rowPos = (int)(rowPos + 2*(rowLength+1)*3);

			y = (float)(-1);
			
			vertexCoords[rowPos] = x;
			vertexCoords[rowPos+1] = y;
			vertexCoords[rowPos+2] = z;
			
			vertexCoords[rowPos+(rowLength+1)*3] = 	x;
			vertexCoords[rowPos+(rowLength+1)*3+1] = y;
			vertexCoords[rowPos+(rowLength+1)*3+2] = z;
			
			//Bottom face normals
			normalCoords[rowPos] = x;
			normalCoords[rowPos+1] = 0;
			normalCoords[rowPos+2] = z;
			
			normalCoords[rowPos+(rowLength+1)*3] = 0;
			normalCoords[rowPos+(rowLength+1)*3+1] = -1;
			normalCoords[rowPos+(rowLength+1)*3+2] = 0;
			
			//Vertical Texture
			int texPos = (int)(u*2);
			
			textureCoords[texPos] = u/(rowLength);
			textureCoords[texPos+1] = 0f;
			
			textureCoords[texPos+2*(rowLength+1)*2] = u/(rowLength);
			textureCoords[texPos+2*(rowLength+1)*2 + 1] = 1f;
			
			//Top Face Texture
			texPos = (int)(texPos + (rowLength+1)*2);
		
			textureCoords[texPos] = (x+1f)/2.0f;
			textureCoords[texPos+1] = 1 - (z+1f)/2.0f;
			
			//Bottom Face Texture
			textureCoords[texPos + 2*(rowLength+1)*2] = (x+1f)/2.0f;
			textureCoords[texPos + 2*(rowLength+1)*2 + 1] = 1 - (z+1f)/2.0f;
			
		}
	
		//Populate the triangles and lines
		for (int u = 0; u < rowLength; u++) {
			
			//Vertical triangles
			int trianglePos = u*6;
			
			triangleVerts[trianglePos] = u;
			triangleVerts[trianglePos+1] = u + 1;
			triangleVerts[trianglePos+2] = u + (rowLength+1)*2;
					
			triangleVerts[trianglePos+3] = u + 1;
			triangleVerts[trianglePos+4] = u + (rowLength+1)*2 + 1;
			triangleVerts[trianglePos+5] = u + (rowLength+1)*2;
			
			//Top face triangles
			trianglePos = ((rowLength+1)*6) + (u*3);
			 
			triangleVerts[trianglePos] = 4*(rowLength+1);
			triangleVerts[trianglePos+1] = u + (rowLength+1) + 1;
			triangleVerts[trianglePos+2] = u + (rowLength+1);
			
			//Bottom face triangles
			trianglePos += (rowLength)*3;
			
			triangleVerts[trianglePos] = 4*(rowLength+1)+1;
			triangleVerts[trianglePos+1] = u + 3*(rowLength+1);
			triangleVerts[trianglePos+2] = u + 3*(rowLength+1)+1;
			
			//Vertical lines
			int linePos = (u*2);
			
			wireframeVerts[linePos] = u;
			wireframeVerts[linePos+1] = u + (rowLength + 1)*2;
		
			//Top face lines
			linePos = ((rowLength+1)*2) + (u*4);
			
			wireframeVerts[linePos] = 4*(rowLength+1);
			wireframeVerts[linePos+1] = u + (rowLength+1);
				
			wireframeVerts[linePos+2] = u + (rowLength+1);
			wireframeVerts[linePos+3] = u + (rowLength+1) + 1;
			
			//Bottom face lines
			linePos = 3*((rowLength+1)*2) + (u*4);
			
			wireframeVerts[linePos] = 4*(rowLength+1)+1;
			wireframeVerts[linePos+1] = u + 3*(rowLength+1);
				
			wireframeVerts[linePos+2] = u + 3*(rowLength + 1);
			wireframeVerts[linePos+3] = u + 3*(rowLength+1) + 1;
		
			
		}

		setVertices(gl, vertexCoords);
		setNormals(gl, normalCoords);
		setTriangleIndices(gl, triangleVerts);
		setWireframeIndices(gl, wireframeVerts);
		setTexCoords(gl, textureCoords);
		
		
		// TODO (Shaders 2 P2): Generate texture coordinates for the cylinder also
		
	}
	

	@Override
	public Object getYamlObjectRepresentation()
	{
		Map<Object,Object> result = new HashMap<Object, Object>();
		result.put("type", "Cylinder");
		return result;
	}
}
