package com.falkensoft.ai.path;


import javax.vecmath.Point2d;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Falken224 on 5/2/2017.
 */
public class GridMap {

    public int width;
    public int height;

    public GridMap()
    {
        width=0;
        height=0;
    }

    public GridMap(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public Set<Point> openAdjacentLocations(Point location)
    {
        Set<Point> ret = new HashSet<>();
        for(int x=location.x-1; x<location.x+2; x++)
        {
            for(int y=location.y-1; y<location.y+2; y++)
            {
                if(y<0 || x<0 || y>height-1 || x>width-1) continue;
                if(y==location.y && x==location.x) continue;
                ret.add(new Point(x,y));
            }
        }
        return ret;
    }

    public Set<Step<Point>> availableSteps(Step<Point> current)
    {
        Set<Point> points = openAdjacentLocations(current.getLoc());
        Set<Step<Point>> ret = new HashSet<>();
        for(Point point : points)
        {
            ret.add(new Step<>(point,current,Point.distance(current.getLoc().x, current.getLoc().y,point.x,point.y)));
        }
        return ret;
    }
}
