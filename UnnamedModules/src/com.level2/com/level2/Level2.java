package com.level2;

public class Level2 {

    private Level parent;

    private Level2()
    {
        // empty block
    }

    public static Level2 under(Level parent)
    {
         Level2 level2 = new Level2();
         level2.parent = parent;
         return level2;
    }

    public Level aNew(String name)
    {
         Level level = new Level(name);
         parent.setChild(level);
         level.setParent(parent);
         return level;
    }

}
