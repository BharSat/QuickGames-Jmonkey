package quick.game.core.scene;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.InputManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;

import com.jme3.math.Vector3f;

public class XmlReaderState extends BaseAppState {
    /*AppState to aid quick.game.file.xml.XmlParser in xml parsing.
    Automatically attaches SimpleSceneState if not
    already attached.
    Main XmlParser in quick.game.file.xml.XmlParser .*/
    private SimpleApplication app;
    private Node              rootNode;
    private AssetManager      assetManager;
    private AppStateManager   stateManager;
    private InputManager      inputManager;
    private ViewPort          viewPort;
    private BulletAppState    physics;
    
    @Override
    protected void initialize(Application appB) {
        this.app = (SimpleApplication) appB;
        this.rootNode     = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
        this.inputManager = this.app.getInputManager();
        this.viewPort     = this.app.getViewPort();
        this.physics      = this.stateManager.getState(BulletAppState.class);
        try {
            this.stateManager.getState(SceneBuilderState.class, true);
        } catch (IllegalArgumentException e) {
            this.stateManager.attach(new SceneBuilderState());
        }
    }

    @Override
    protected void cleanup(Application app) {}

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void update(float tpf) {}

    public void floor(String coords){
        String[] sepCoords = coords.split(",");
        Vector3f start = new Vector3f(Integer.parseInt(sepCoords[0]), 0, Integer.parseInt(sepCoords[1]));
        Vector3f end = new Vector3f(Integer.parseInt(sepCoords[2]), 0, Integer.parseInt(sepCoords[3]));
        SceneBuilderState sceneBuilderState = (SceneBuilderState)stateManager.getState(SceneBuilderState.class);
        sceneBuilderState.createFloor(start, end);
    }

    public void wall(String coords){
        String[] sepCoords = coords.split(",");
        Vector3f start = new Vector3f(Integer.parseInt(sepCoords[0]), 0, Integer.parseInt(sepCoords[1]));
        Vector3f end = new Vector3f(Integer.parseInt(sepCoords[2]), 0, Integer.parseInt(sepCoords[3]));
        SceneBuilderState sceneBuilderState = (SceneBuilderState)stateManager.getState(SceneBuilderState.class);
        sceneBuilderState.createWall(start, end, 3f);
    }

    public void light(String coords){
        String[] sepCoords = coords.split(",");
        Vector3f direction = new Vector3f(Integer.parseInt(sepCoords[0]), Integer.parseInt(sepCoords[1]), Integer.parseInt(sepCoords[2]));
        SceneBuilderState sceneBuilderState = (SceneBuilderState)stateManager.getState(SceneBuilderState.class);
        sceneBuilderState.startLight(direction);
    }

    public void region(String coords, String id0){
        String[] sepCoords = coords.split(",");
        Vector3f start = new Vector3f(Integer.parseInt(sepCoords[0]), 0, Integer.parseInt(sepCoords[1]));
        Vector3f end = new Vector3f(Integer.parseInt(sepCoords[2]), 0, Integer.parseInt(sepCoords[3]));
        SceneBuilderState sceneBuilderState = (SceneBuilderState)stateManager.getState(SceneBuilderState.class);
        sceneBuilderState.createRegion(start, end, id0);
    }

    public void fence(String loc, String scale) {
        String[] sepCoords = loc.split(",");
        Vector3f loc_ = new Vector3f(Integer.parseInt(sepCoords[0]), 0, Integer.parseInt(sepCoords[1]));
        int scaleInt = Integer.parseInt(scale);
        SceneBuilderState sceneBuilderState = (SceneBuilderState)stateManager.getState(SceneBuilderState.class);
        sceneBuilderState.createFence(loc_, scaleInt);
    
    }

}
