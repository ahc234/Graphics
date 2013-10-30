
#version 120

// uniforms -- same value is used for every vertex in model
uniform mat4 un_Projection;
uniform mat4 un_ModelView;
uniform mat3 un_NormalMatrix;

uniform vec3 un_AmbientColor;
uniform vec3 un_DiffuseColor;
uniform vec3 un_SpecularColor;
uniform float un_Shininess;

uniform vec3 un_LightPositions[16];
uniform vec3 un_LightIntensities[16];
uniform vec3 un_LightAmbientIntensity;

// Transform between the object's local frame and
// the "yellow" light-aligned frame in Figures 5/6/7
uniform mat4 un_FrameToObj;
uniform mat4 un_ObjToFrame;

// TODO: (Shaders 1 Problem 2) Declare any additional uniform variables here
uniform mat4 un_vertTransform;
uniform mat4 un_normTransform;

// vertex attributes -- distinct value used for each vertex
attribute vec3 in_Vertex;
attribute vec3 in_Normal;

// TODO: (Shaders 1 Problem 2) Declare any varying variables here
varying vec3 ex_bentNorm;
varying vec3 ex_bentVert;

void main()
{
	// TODO: (Shaders 1 Problem 2) Implement the vertex shader for the
	// flower shader here
	
	vec3 ex_bentVert = un_ObjToFrame * vec3(in_Vertex, 1.0);
	ex_bentNorm = un_ObjToFrame * vec3(in_Normal, 1.0);
	
	ex_bentVert = un_vertTransform * ex_bentVert;
	ex_bentNorm = un_normTransform * ex_bentNorm;
	
	ex_bentVert = un_FrameToObj * ex_bentVert;
	ex_bentNorm = un_FrameToObj * ex_bentNorm;
	
	ex_bentNorm = normalize(un_NormalMatrix * ex_bentNorm);
	ex_bentVert = un_ModelView * ex_bentVert;
	
    gl_Position = un_Projection * un_ModelView * bentVert;
}

