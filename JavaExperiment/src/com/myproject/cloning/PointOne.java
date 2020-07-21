package com.myproject.cloning;

public class PointOne {
    private Integer x;
    private Integer y;
 
    public PointOne(PointOne point){
        this.x = point.x;
        this.y = point.y;
    }
    
    public PointOne(Integer x, Integer y)
    {
        this.x = x;
        this.y = y;
    }
}