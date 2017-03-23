package com.data.app;

import com.data.service.DataService;

import java.util.NoSuchElementException;
import java.util.ServiceLoader;

public class DataServiceProvider
{
    private static class SingletonHelper
    {
        public static final DataServiceProvider instance = new DataServiceProvider();
    }
	
    private ServiceLoader<DataService> loader;

    private DataServiceProvider()
    {
        loader = ServiceLoader.load(DataService.class);
    }

    public static DataServiceProvider getInstance()
    {
        return SingletonHelper.instance;
    }

    public DataService getImpl()
    {
        DataService serviceImpl = loader.iterator().next();
        if(serviceImpl != null)
        {
            return serviceImpl;
        }
        else
        {
            throw new NoSuchElementException("No implementation found for DataService");
        }
    }

}

