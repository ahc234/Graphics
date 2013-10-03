package cs4620.demos;

import java.awt.BorderLayout;

import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.swing.JFrame;
import javax.vecmath.Matrix3f;

import cs4620.framework.GlslException;
import cs4620.framework.IndexBuffer;
import cs4620.framework.Program;
import cs4620.framework.Transforms;
import cs4620.framework.TwoDimProgram;
import cs4620.framework.VertexArray;
import cs4620.framework.VertexBuffer;


public class TwoBoxes implements GLEventListener {
	
	// GL resources
	boolean initialized = false;
	VertexArray boxArray;
	VertexArray boxLinesArray;
	
	TwoDimProgram program;
	
	Matrix3f shiftLeftTransform;
	Matrix3f shiftRightTransform;
	
	public TwoBoxes() {
		// NOP
	}
	
	public static void main(String[] args) {	
		TwoBoxes app = new TwoBoxes();
		
		GLProfile profile = GLProfile.getDefault();
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas(capabilities);
		canvas.addGLEventListener(app);
		
		JFrame frame = new JFrame("CS 4620 Demo: Two Boxes");
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(512, 512);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		if(initialized)
			return;
		
		GL2 gl = drawable.getGL().getGL2();
		
		// define vertex positions
		float [] vertexPositions = {
				-0.5f, -0.5f,      // vertex 0
				 0.5f, -0.5f,      // vertex 1
				 0.5f,  0.5f,      // vertex 2
				-0.5f,  0.5f       // vertex 3
		};
		
		VertexBuffer vertexBuffer = new VertexBuffer(gl, vertexPositions, 2);
		
		// organize vertices into primitives
		int [] linesIndices = {
			0, 1,
			1, 2,
			2, 3,
			3, 0
		};
		
		IndexBuffer linesBuffer = new IndexBuffer(gl, linesIndices);
		
		int [] trianglesIndices = {
			0, 1, 2,
			0, 2, 3
		};
		
		IndexBuffer trianglesBuffer = new IndexBuffer(gl, trianglesIndices);
		
		// assemble vertex positions and indices into vertex arrays
		boxArray = new VertexArray(gl, GL2.GL_TRIANGLES);
		boxArray.setIndexBuffer(gl, trianglesBuffer);
		boxArray.setAttributeBuffer(gl, TwoDimProgram.VERTEX_INDEX, vertexBuffer);
		
		boxLinesArray = new VertexArray(gl, GL2.GL_LINES);
		boxLinesArray.setIndexBuffer(gl, linesBuffer);
		boxLinesArray.setAttributeBuffer(gl, TwoDimProgram.VERTEX_INDEX, vertexBuffer);
		
		// create shader program for drawing
		try {
			program = new TwoDimProgram(gl, "flatcolor2d.vs", "flatcolor2d.fs");
		} catch (GlslException e) {
			System.err.println("FAIL: creating program");
			e.printStackTrace();
			System.exit(1);
		}
		
		// initialize transforms to identity matrix
		program.setProjection(gl, Transforms.identity2DH());
		program.setModelView(gl, Transforms.identity2DH());
		
		// create transforms for two-box
		Matrix3f scale = Transforms.scale2DH(0.5f);
		
		shiftLeftTransform = Transforms.translate2DH(-0.25f - 0.5f/3, 0.0f);
		shiftLeftTransform.mul(scale);
		
		shiftRightTransform = Transforms.translate2DH(0.25f + 0.5f/3, 0.0f);
		shiftRightTransform.mul(scale);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		Program.use(gl, program);
		
		program.setColor(gl, 1.0f, 1.0f, 1.0f);
		
		program.setModelView(gl, shiftLeftTransform);
		boxArray.draw(gl);
		
		program.setModelView(gl, shiftRightTransform);
		boxLinesArray.draw(gl);
		
		Program.unuse(gl);
	}

	@Override
	public void reshape(GLAutoDrawable drawble, 
			int x, int y, int width, int height) {
		// NOP
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// NOP		
	}
}
