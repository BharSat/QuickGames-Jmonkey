# QuickGames-Jmonkey
Get Started:
The basic structure is same as [jme3](https://wiki.jmonkeyengine.org/docs/3.4/documentation.html).
However we extend quick.game.app.SimpleGame instead of SImpleApplication.
```markdown
package ;

import com.jme3.app.SimpleApplication;
import quick.game.app.SimpleGame;
import com.jme3.math.Vector3f;
import com.jme3.bullet.BulletAppState;

public class MyGame extends SimpleGame {

    public static void main(String[] args){
        MyGame app = new MyGame();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        stateManager.attach(BulletAppState); //Physics
        stateManager.attach(SimpleSceneState); // Attach the appstate
        stateManager.getState(SimpleSceneState.class).createFloor(new Vector3f(-10f, 0f, -10f), new Vector3f(10f, 0f, 10f));
        // continue...
    }
}
```
You can continue with:
 [SimpleSceneState class](https://github.com/BharSat/QuickGames-Jmonkey/blob/main/src/quick/game/core/scene/SimpleSceneState.java.)
OR:
[JMonkeyEngine methods](https://github.com/jMonkeyEngine/jmonkeyengine)
