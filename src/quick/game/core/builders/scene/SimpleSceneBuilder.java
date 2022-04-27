/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quick.game.core.builders.scene;

import com.jme3.math.Vector3f;
import quick.game.core.builders.BaseBuilder;

import quick.game.math.Math;

import com.jme3.scene.*;
import com.jme3.scene.shape.Box;

import com.jme3.material.Material;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
/**
 *
 * @author Bhargav Balaji
 */
public class SimpleSceneBuilder extends BaseBuilder{
    static Node createSimpleFloor(String id, Material mat, Vector3f start, Vector3f end, Boolean useBullet){
        start.y = -1f;
        end.y = -3f;
        Vector3f center = start.clone();
        center.add(Math.average(start, end));
        Vector3f size = Math.subtract(end, start);
        
        Node floor = new Node();
        Box floorMesh = new Box(size.x, size.y, size.z);
        Geometry floorGeo = new Geometry(id, floorMesh);
        floorGeo.setMaterial(mat);
        
        floor.attachChild(floorGeo);
        
        if (useBullet) {
            CollisionShape floorShape = CollisionShapeFactory.createMeshShape(floor);
            RigidBodyControl floorControl = new RigidBodyControl(floorShape, 0);
            floor.addControl(floorControl);
            return floor;
        }
        else {
            return floor;
        }
    }
    
    static Node createSimpleWall(String id, Material mat, Vector3f start, Vector3f end, Boolean useBullet){
        start.y = 2f;
        end.y = -1f;
        Vector3f center = start.clone();
        center.add(Math.average(start, end));
        Vector3f size = Math.subtract(end, start);
        
        Node wall = new Node();
        Box wallMesh = new Box(size.x, size.y, size.z);
        Geometry wallGeo = new Geometry(id, wallMesh);
        wallGeo.setMaterial(mat);
        
        wall.attachChild(wallGeo);
        
        if (useBullet) {
            CollisionShape wallShape = CollisionShapeFactory.createMeshShape(wall);
            RigidBodyControl wallControl = new RigidBodyControl(wallShape, 0);
            wall.addControl(wallControl);
            return wall;
        }
        else {
            return wall;
        }
    }
    
    static void createLight(){}
    
    static void createRegion(){}
}
