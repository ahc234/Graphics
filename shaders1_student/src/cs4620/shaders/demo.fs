#version 120

// uniforms -- same value is used for every vertex in model
uniform mat4 un_Projection;
uniform mat4 un_ModelView;
uniform vec3 un_DiffuseColor;
uniform vec3 un_SpecularColor;

varying vec3 ex_foo;

void main(void)
{
    float alpha = 0.5 + 0.5*sin(24*ex_foo.y + 48*ex_foo.x);
	gl_FragColor = vec4(mix(un_DiffuseColor, un_SpecularColor, alpha), 1.0);
}

