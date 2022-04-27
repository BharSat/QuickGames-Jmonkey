/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quick.game.math;

import com.jme3.math.Vector3f;

/**
 *
 * @author Bhargav Balaji
 */
public class Math {
    public static float average(float a, float b) {
        return (a+b)/2;
    }
    
    public static Vector3f average(Vector3f a, Vector3f b) {
        return new Vector3f(average(a.x, b.x), average(a.y, b.y), average(a.z, b.z));
    }
    
    public static Vector3f subtract(Vector3f a, Vector3f b) {
        return new Vector3f((b.x-a.x), (b.y-a.y), (b.z-a.z));
    }
}
