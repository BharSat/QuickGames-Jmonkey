/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quick.game.core.scene;

import java.util.List;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;

import com.jme3.bullet.BulletAppState;

import com.jme3.scene.*;

import com.jme3.math.Vector3f;
import com.jme3.math.ColorRGBA;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;

import quick.game.core.builders.SimpleSceneBuilder;

/**
 *
 * @author Bhargav Balaji
 */
public class SceneBuilderState extends BaseAppState{
    private SimpleApplication app;
    private AppStateManager stateManager;
    private Node rootNode;
    private AssetManager assetManager;
    private Boolean bullet = true; //asumes that BulletAppState is Attached till initialize is called.
    
    private Float Ground = -1f;
    private Integer floors = 0;
    private Integer walls = 0;
    private Integer lights = 0;

    private List<String> items;
    private List<Spatial> regions;

    private Node regionNode = new Node();

    private Material floorMat() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/floor/Floor.png"));
        return mat;
    }

    private Material wallMat() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/wall/Wall.png"));
        return mat;
    }

    private Material regionMat() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat.setTexture("ColorMap", assetManager.loadTexture("Textures/floor/Floor.png"));
    mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
    return mat;
    }

    @Override
    protected void initialize(Application arg0) {
        this.app = (SimpleApplication)arg0;
        this.stateManager = app.getStateManager();
        //check whether to use collision or not.
        if (stateManager.hasState(new BulletAppState())) {this.bullet = false;}
        else {this.bullet = true;}
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
    }

    @Override
    protected void cleanup(Application arg0) {}

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    //set the ground level of the appstate. This is used as a reference to create Items. raised floors and walls can be created by altering this value
    public void setGroundLevel(float ground) {this.Ground = ground;}
    
    //creation methods. Mainly use methods from quick.game.core.builders.scene.SimpleSceneBuilder    
    public Node createFloor(Vector3f start, Vector3f end) {
        //create a flat floor and attach it to the rootNode
        //@param start - top left corner of the floor(Birds eye view). y coordinate is taken from the ground value.
        //@param end - Bottom right corner of the floor y coordinate is taken from the ground value.
        start.y = this.Ground;
        end.y = this.Ground - 1f;

        String id0 = "floor" + this.floors.toString();
        Node toReturn = SimpleSceneBuilder.createSimpleBlock(id0, floorMat(), start, end, this.bullet);
        rootNode.attachChild(toReturn);
        this.floors++;
        this.items.add(id0);
        return toReturn;
    }

    public Node createWall(Vector3f start, Vector3f end, float height) {
        //create a flat wall and attach it to the rootNode
        //@param start - top left corner of the floor(Birds eye view). y coordinate is taken from the ground value(added to wall height).
        //@param end - Bottom right corner of the floor y coordinate is taken from the ground value.
        start.y = this.Ground + height;
        end.y = this.Ground;

        String id0 = "wall" + this.walls.toString();
        Node toReturn = SimpleSceneBuilder.createSimpleBlock(id0, wallMat(), start, end, this.bullet);
        rootNode.attachChild(toReturn);
        this.walls++;
        this.items.add(id0);
        return toReturn;
    }

    public Node createFence(Vector3f loc, float scale) {
        String id0 = "wall" + this.walls.toString();
        Node toReturn = SimpleSceneBuilder.createRoundWall(wallMat(), loc, scale, this.bullet, this.assetManager);
        rootNode.attachChild(toReturn);
        this.walls++;
        this.items.add(id0);
        return toReturn;
    }
    
    public void startLight(Vector3f Direction) {
        rootNode.addLight(SimpleSceneBuilder.createDirectionalLight(Direction));
        this.items.add("Light" + this.lights.toString());
        this.lights++;
    }

    void startLight() {
        rootNode.addLight(SimpleSceneBuilder.createDirectionalLight());
        this.items.add("Light" + this.lights.toString());
        this.lights++;
    }
    
    void createRegion(Vector3f start, Vector3f end, String id0) {
        Spatial region = SimpleSceneBuilder.createSimpleBlock(id0, regionMat(), start, end, false);
        regionNode.attachChild(region);
        regions.add(region);
        System.out.println("notImplemented yet.");
    }
    
    void deleteItem(){}

    @Override
    public void update(float tpf) {
        //TODO: implement logic to check if character is in region.
    }
    
    Boolean check() {return true;}
    
}
