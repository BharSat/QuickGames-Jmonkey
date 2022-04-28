/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quick.game.core.builders;

import com.jme3.math.Vector3f;
import com.jme3.math.ColorRGBA;

import quick.game.core.builders.BaseBuilder;
import quick.game.math.Math;

import com.jme3.scene.*;
import com.jme3.scene.shape.Box;

import com.jme3.material.Material;

import com.jme3.light.DirectionalLight;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
/**
 *
 * @author Bhargav Balaji
 */
public class SimpleSceneBuilder extends BaseBuilder{
    /*A simple builder that creates objects in the scene with no standard start.
    While using this class, all the start positions/center positions should be similar with reference to each other.
    Drawback of not having standard start location is that objects such as walls, and floors have to be manually standardised by the user.
    Standard methoods are provided in quick.game.core.scene.SimpleSceneState. Further classes are also planned.*/

    static Node createSimpleBlock (String id, Material mat, Vector3f start, Vector3f end, Boolean useBullet){
        /*Simple methd to create a box and return a node containing the block.
        @param start, end - start and end points of the block.
        @param id - unique string for the object.
        @param mat - material for the Block
        @param useBullet - whether to add a collision hitbox the node.
        @returns - a Node containing the Block*/
        Vector3f center = start.clone();
        center.add(Math.average(start, end));
        Vector3f size = Math.subtract(end, start);
        
        Node Block = new Node();
        Box BlockMesh = new Box(size.x, size.y, size.z);
        Geometry BlockGeo = new Geometry(id, BlockMesh);
        BlockGeo.setMaterial(mat);
        
        Block.attachChild(BlockGeo);
        
        if (useBullet) {
            CollisionShape BlockShape = CollisionShapeFactory.createMeshShape(Block);
            RigidBodyControl RigidBoxControl = new RigidBodyControl(BlockShape, 0);
            Block.addControl(RigidBoxControl);
            return Block;
        }
        else {
            return Block;
        }
    }
    
    static Node createDirectionalLight(Vector3f Direction){
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(Direction.normalizeLocal());
        sun.setColor(ColorRGBA.White);
    }
    static Node createDirectionalLight(){
        createDirectionalLight(new Vector3f(0f, 0f, 0f));
    }

    static Node createRoundWall(Material mat, Vector3f loc, Vector3f scale){
        Spatial wall = assetManager.loadModel("Models/curvedWall.j3o");
        wall.setMaterial(mat);
        wallN = new Node();
        wallN.attachChild(wall);
        wallN.setLocalTranslation(loc);
        wallN.setScale(scale);
        if (useBullet) {
            CollisionShape WallShape = CollisionShapeFactory.createMeshShape(WallN);
            RigidBodyControl RigidWallControl = new RigidBodyControl(WallShape, 0);
            Block.addControl(RigidWallControl);
            return WallN;
        }
        else {
            return WallN;
        }
    }
}
