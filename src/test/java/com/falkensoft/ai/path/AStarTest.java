package com.falkensoft.ai.path;

import org.testng.annotations.Test;

import java.awt.*;

/**
 * Created by Falken224 on 5/3/2017.
 */
public class AStarTest {

    @Test
    void testStraightLine()
    {
        Point start = new Point(1,1);
        Point end = new Point(10,10);
        GridMap map = new GridMap(20,20);
        AStar.findPath(start,end,map::availableSteps,end::distance);
        //TODO: Put the right assert here . . . but it's 1am and I'm too tired!
    }
}
