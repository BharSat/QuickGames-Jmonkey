# QuickGames-Jmonkey
Get Started:
This module helps making games easy with [jme3](https://wiki.jmonkeyengine.org/docs/3.4/documentation.html).
attach the QuickGameState and use methods from there
```markdown
package ;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.bullet.BulletAppState;
import quick.game.app.QuickGameState;

public class MyGame extends SimpleApplication {

    public static void main(String[] args){
        MyGame app = new MyGame();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        stateManager.attach(BulletAppState); //Physics
        stateManager.attach(QuickGameState); // Attach the appstate
        stateManager.getState(QuickGameState.class).createFloor(new Vector3f(-10f, 0f, -10f), new Vector3f(10f, 0f, 10f));
        // continue...
    }
}
```
You can continue with:
 [QuickGameState class](https://github.com/BharSat/QuickGames-Jmonkey/blob/main/src/quick/game/app/QuickGameState.java.)
OR:
[JMonkeyEngine methods](https://github.com/jMonkeyEngine/jmonkeyengine)
