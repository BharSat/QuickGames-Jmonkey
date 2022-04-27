package ;

import com.jme3.app.SimpleApplication;
import quick.game.core.SimpleSceneState;
import com.jme3.math.Vector3f;
import com.jme3.bullet.BulletAppState;

public class SimpleGame extends SimpleApplication {

    public static void main(String[] args){
        SimpleGame app = new SimpleGame();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        stateManager.attach(BulletAppState);
        stateManager.attach(SimpleSceneState);
        stateManager.getState(SimpleSceneState.class).createFloor(new Vector3f(-10f, 0f, -10f), new Vector3f(10f, 0f, 10f));
        // continue...
    }
}