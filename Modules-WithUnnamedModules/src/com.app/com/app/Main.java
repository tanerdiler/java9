package com.app;

import com.level1.Level1;
import com.level2.Level;
import com.level2.Level2;

public class Main
{
    public static void main(String[] args)
    {
	Level parent = Level1.aNew("1. Seviye");
	Level child = Level2.under(parent).aNew("2. Seviye");
	System.out.println(child);
    }
}
