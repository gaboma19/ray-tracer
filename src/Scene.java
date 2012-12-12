
public class Scene {
	Camera c;
	LightCollection l;
	MaterialCollection m;
	RenderObjectCollection o;
	int[] ac;
	
	
	public Scene (Camera camera, LightCollection lights, MaterialCollection materials, RenderObjectCollection objects, int[] ambientColor){
		this.c = camera;
		this.l = lights;
		this.m = materials;
		this.o = objects;
		this.ac = ambientColor; 
	}
}
