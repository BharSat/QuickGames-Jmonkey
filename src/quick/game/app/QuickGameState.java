package quick.game.app;

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
import quick.game.core.scene.*;
import quick.game.file.xml.XmlParser;

public class QuickGameState extends BaseAppState {
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
    private SceneBuilderState sceneState;
    private XmlReaderState xmlParser;
    
    float ground = -1f;
    
    @Override
    protected void initialize(Application appB) {
        this.app = (SimpleApplication) appB;
        this.rootNode     = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
        this.inputManager = this.app.getInputManager();
        this.viewPort     = this.app.getViewPort();
        this.physics      = this.stateManager.getState(BulletAppState.class);
        
        this.sceneState = new SceneBuilderState();
        this.stateManager.attach(this.sceneState);
        
        this.xmlParser = new XmlReaderState();
        this.stateManager.attach(this.xmlParser);
    }

    @Override
    protected void cleanup(Application app) {}

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void update(float tpf) {}
    
    public Node createFloor(Vector3f start, Vector3f end) {return this.sceneState.createFloor(start, end);}
    
    public Node createWall(Vector3f start, Vector3f end, float height) {return this.sceneState.createWall(start, end, height);}
    
    public Node createFence(Vector3f start, float height) {return this.sceneState.createFence(start, height);}
    
    public void startSun(Vector3f Direction) {this.sceneState.startLight(Direction);}
    
    public void parse(String path_to_file) {
        XmlParser.parse(path_to_file, xmlParser);
    }

}
