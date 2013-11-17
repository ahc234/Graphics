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

uniform sampler2D un_NoiseTexture;
uniform sampler2D un_FireTexture;
uniform float un_Time;

uniform vec3 un_ScrollSpeeds;

// TODO (Shaders 2 P3): Declare varying variables here
varying vec3 ex_Vertex;
varying vec2 ex_TexCoord;
varying float noise;

void main() {
	// TODO (Shaders 2 P3): Implement the fire fragment shader here
  /*  vec2 set1 = (ex_TexCoord);
    vec2 set2 = (ex_TexCoord * 2);
    vec2 set3 = (ex_TexCoord * 3);
    
    set1.y = set1.y + un_Time * un_ScrollSpeeds.x;
    set2.y = set2.y + un_Time * un_ScrollSpeeds.y;
    set3.y = set3.y + un_Time * un_ScrollSpeeds.z;
    
    float colorRGB1 = texture2D(un_NoiseTexture, set1);
    float colorRGB2 = texture2D(un_NoiseTexture, set2);
    float colorRGB3 = texture2D(un_NoiseTexture, set3);
    
    vec2 colorRGB = (1/3)*(colorRGB1 + colorRGB2 + colorRGB3);
    
    colorRGB = texture2D(un_FireTexture, colorRGB).rgb;
    
	gl_FragColor = vec4(colorRGB, 1);*/
    
    //float r = .01 * random( vec3( 12.9898, 78.233, 151.7182 ), 0.0 );
    // lookup vertically in the texture, using noise and offset
    // to get the right RGB colour
    vec2 tPos = vec2( 0, 1.0 - 1.3 * noise + .01 );
    vec4 color = texture2D( un_FireTexture, tPos );
    
    //vec3 color = vec3( ex_TexCoord * ( 1. - 2. * noise ), 0.0 );
    gl_FragColor = vec4( color.rgb, 1.0 );
}