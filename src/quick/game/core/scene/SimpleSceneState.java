/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quick.game.core.scene;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.app.state.AppStateManager;

import com.jme3.bullet.BulletAppState;

import com.jme3.scene.Node;

import com.jme3.math.Vector3f;

/**
 *
 * @author Bhargav Balaji
 */
public abstract class SimpleSceneState extends BaseAppState{
    SimpleApplication app;
    AppStateManager stateManager;
    Node rootNode;
    BulletAppState bullet = new BulletAppState();
    
    String[] items;
    Float Ground = -1f;
    
    @Override
    protected void initialize(Application arg0) {
        this.app = (SimpleApplication)arg0;
        this.stateManager = app.getStateManager();
        this.stateManager.attach(bullet);
        this.rootNode = this.app.getRootNode();
    }

    @Override
    protected void cleanup(Application arg0) {}

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}
    
    void initBasicMaze(){}
    
    void createFloor(Vector3f start, Vector3f end){
        
    }
    
    void createWall(){}
    
    void createLight(){}
    
    void createRegion(){}
    
    void deleteItem(){}
    
}
