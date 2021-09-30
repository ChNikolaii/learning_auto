package ru.lnauto.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class DistanceTest {

    @Test
public void testDistance() {
    Point p1 = new Point(9, 5);
    Point p2 = new Point(20,20);
        Assert.assertEquals(p1.distance(p2), 18.601075237738275);
    }
}
