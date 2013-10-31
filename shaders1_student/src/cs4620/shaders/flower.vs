
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
uniform float un_maxPhi;
uniform float un_maxRadius;
uniform float flowerHeight;

// vertex attributes -- distinct value used for each vertex
attribute vec3 in_Vertex;
attribute vec3 in_Normal;

// TODO: (Shaders 1 Problem 2) Declare any varying variables here
varying vec4 ex_bentVert;
varying vec4 ex_bentNorm;

void main()
{
	// TODO: (Shaders 1 Problem 2) Implement the vertex shader for the
	// flower shader here
	
	ex_bentVert = un_ObjToFrame * vec4(in_Vertex, 1.0);
	vec3 normalized = normalize(un_NormalMatrix * in_Normal);
	ex_bentNorm = un_ObjToFrame * vec4(normalized, 1.0);
	
	float radius = un_maxRadius - ex_bentVert.x;
	float phi = (ex_bentVert.y/flowerHeight) * un_maxPhi;
	
	ex_bentVert.x = un_maxRadius - (radius * cos(phi));
	ex_bentVert.y = 0.0 + radius * sin(phi);
	
	ex_bentVert = un_FrameToObj * ex_bentVert;
	ex_bentNorm = un_FrameToObj * ex_bentNorm;
	
    gl_Position = un_Projection * un_ModelView * ex_bentVert;
}

