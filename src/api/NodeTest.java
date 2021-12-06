package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NodeTest {

    @org.junit.jupiter.api.Test
    void getKeyTest() {
        NodeData n = new Node(35.19589389346247,32.10152879327731,0.0, 0);
        assertEquals(n.getKey(), 0);
        assertNotEquals(n.getKey(), 1);

        n = new Node(35.20319591121872,32.10318254621849,0.0, 1);
        assertEquals(n.getKey(), 1);
        assertNotEquals(n.getKey(), Integer.MIN_VALUE);

        n = new Node(35.19589389346247,32.10152879327731,0.0, Integer.MAX_VALUE);
        assertEquals(n.getKey(), Integer.MAX_VALUE);
        assertNotEquals(n.getKey(), 0);
    }

    @org.junit.jupiter.api.Test
    void getLocationTest() {
        NodeData n = new Node(35.19589389346247,32.10152879327731,0.0, 0);
        String s = n.getLocation().toString();
        GeoLocation gTrue = new Location(35.19589389346247,32.10152879327731,0.0);
        String sTrue = gTrue.toString();
        GeoLocation gFalse = new Location(35.20319591121872,32.10318254621849,0.0);
        String sFalse = gFalse.toString();
        assertEquals(s, sTrue);
        assertNotEquals(s, sFalse);

        n = new Node(35.20319591121872,32.10318254621849,0.0, 1);
        s = n.getLocation().toString();
        gTrue = new Location(35.20319591121872,32.10318254621849,0.0);
        sTrue = gTrue.toString();
        gFalse = new Location(35,32,0);
        sFalse = gFalse.toString();
        assertEquals(s, sTrue);
        assertNotEquals(s, sFalse);

        n = new Node(35.20752617756255,32.1025646605042,0.0, 2);
        s = n.getLocation().toString();
        gTrue = new Location(35.20752617756255,32.1025646605042,0.0);
        sTrue = gTrue.toString();
        gFalse = new Location(Double.MIN_VALUE,Double.MAX_VALUE,0.0);
        sFalse = gFalse.toString();
        assertEquals(s, sTrue);
        assertNotEquals(s, sFalse);
    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        NodeData n = new Node(35.19589389346247,32.10152879327731,0.0, 0);
        GeoLocation g = new Location(35.20319591121872,32.10318254621849,0.0);
        String gStr = g.toString();
        String oldS = n.getLocation().toString();
        n.setLocation(g);
        String newS = n.getLocation().toString();
        assertEquals(gStr, newS);
        assertNotEquals(gStr, oldS);

        g = new Location(Double.MIN_VALUE,Double.MAX_VALUE,0.0);
        gStr = g.toString();
        n.setLocation(g);
        newS = n.getLocation().toString();
        assertEquals(gStr, newS);
        assertNotEquals(gStr, oldS);

        n = new Node(Double.MIN_VALUE,Double.MAX_VALUE,0.0, 0);
        g = new Location(35.20319591121872,32.10318254621849,0.0);
        gStr = g.toString();
        n.setLocation(g);
        newS = n.getLocation().toString();
        assertEquals(gStr, newS);
        assertNotEquals(gStr, oldS);
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        NodeData n = new Node(35.19589389346247,32.10152879327731,0.0, 0);
        assertEquals(n.getWeight(), 0);
        assertNotEquals(n.getWeight(), 1);

        n = new Node(Double.MIN_VALUE,Double.MAX_VALUE,0.0, 0);
    }

    @org.junit.jupiter.api.Test
    void setWeight() {
    }

    @org.junit.jupiter.api.Test
    void getInfo() {
    }

    @org.junit.jupiter.api.Test
    void setInfo() {
    }

    @org.junit.jupiter.api.Test
    void getTag() {
    }

    @org.junit.jupiter.api.Test
    void setTag() {
    }
}