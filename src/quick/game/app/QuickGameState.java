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
    private XmlReaderState XmlParser;
    
    float ground = -1f;
    
    @Override
    protected void initialize(Application appB) {
        this.app = (SimpleApplication) app;
        this.rootNode     = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
        this.inputManager = this.app.getInputManager();
        this.viewPort     = this.app.getViewPort();
        this.physics      = this.stateManager.getState(BulletAppState.class);
        
        this.sceneState = new SceneBuilderState();
        this.stateManager.attach(this.sceneState);
        
        this.XmlParser = new XmlReaderState();
        this.stateManager.attach(this.XmlParser);
    }

    @Override
    protected void cleanup(Application app) {}

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void update(float tpf) {}
    
    public Node

}