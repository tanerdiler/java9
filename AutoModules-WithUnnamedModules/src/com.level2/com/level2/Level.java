package com.level2;

public class Level
{
    private String name;
    private Level parent;
    private Level child;

    public Level(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setParent(Level parent)
    {
        this.parent = parent;
    }

    public void setChild(Level child)
    {
        this.child = child;
    }

    @Override
    public String toString()
    {
        return parent +" --> "+ name;
    }
}
