package cs4620.material;

import java.util.ArrayList;

import javax.media.opengl.GL2;

import cs4620.framework.VertexArray;
import cs4620.scene.SceneProgram;
import cs4620.scene.ProgramInfo;
import cs4620.shape.Mesh;

public abstract class Material
{
	protected static ArrayList<Material> instances = new ArrayList<Material>();
	
	public abstract void draw(GL2 gl, ProgramInfo info, Mesh mesh, boolean wireframe);
	
	public abstract void drawUsingProgram(GL2 gl, SceneProgram program, Mesh mesh, boolean wireframe);

	public Material()
	{
		synchronized(instances) {
			instances.add(this);
		}
	}

	public abstract Object getYamlObjectRepresentation();
}
