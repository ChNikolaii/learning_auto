package ru.lnauto.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class DistanceTest {

    @Test
public void testDistance() {
    Point p1 = new Point(5, 2);
    Point p2 = new Point(6,3);
        Assert.assertEquals(p1.distance(5,2), p2.distance(6,3));
    }
}
