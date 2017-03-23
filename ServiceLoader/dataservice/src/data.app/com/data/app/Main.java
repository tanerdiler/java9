package com.data.app;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("PLC Sensor Value : "+ DataServiceProvider.getInstance().getImpl().getData());        
    }
}
