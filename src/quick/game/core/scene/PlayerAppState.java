package quick.game.core.scene;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.InputManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
        
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

import com.jme3.math.Vector3f;


public class PlayerAppState extends BaseAppState implements ActionListener{
    
    private SimpleApplication app;
    private Node              rootNode;
    private AssetManager      assetManager;
    private AppStateManager   stateManager;
    private InputManager      inputManager;
    private ViewPort          viewPort;
    private BulletAppState    physics;
    public CharacterControl player;
    private Camera cam;
    
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    
    public Vector3f camDir = new Vector3f();
    public Vector3f camLeft = new Vector3f();
    public Vector3f walkDirection = new Vector3f();

    
    @Override
    protected void initialize(Application appB) {
        this.app = (SimpleApplication) appB;
    this.rootNode     = this.app.getRootNode();
    this.assetManager = this.app.getAssetManager();
    this.stateManager = this.app.getStateManager();
    this.inputManager = this.app.getInputManager();
    this.viewPort     = this.app.getViewPort();
    this.physics      = this.stateManager.getState(BulletAppState.class);
    this.cam = this.app.getCamera();
    }

    @Override
    protected void cleanup(Application app) {}

    @Override
    protected void onEnable() {
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(-30f);
        player.setPhysicsLocation(new Vector3f(0, 10f, 0));

        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addListener(this, "Left");
        inputManager.addListener(this, "Right");
        inputManager.addListener(this, "Up");
        inputManager.addListener(this, "Down");
        
        physics.getPhysicsSpace().add(player);
    }

    @Override
    protected void onDisable() {
        physics.getPhysicsSpace().remove(player);
    }

    @Override
    public void update(float tpf) {
        camDir.set(cam.getDirection()).multLocal(0.6f);
        camLeft.set(cam.getLeft()).multLocal(0.4f);
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (up) {
            walkDirection.addLocal(camDir);
        }
        if (down) {
            walkDirection.addLocal(camDir.negate());
        }
        
        walkDirection.y = 0;
        player.setWalkDirection(walkDirection);
        cam.setLocation(player.getPhysicsLocation());
    }

    @Override
    public void onAction(String action, boolean ispressed, float tpf) {
        if (ispressed) {
            left = action.equalsIgnoreCase("Left");
            right = action.equalsIgnoreCase("Right");
            up = action.equalsIgnoreCase("Up");
            down = action.equalsIgnoreCase("Down");
        }
    }
}