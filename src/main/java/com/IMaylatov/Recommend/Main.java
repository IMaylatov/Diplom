package com.IMaylatov.Recommend;

import com.IMaylatov.Recommend.Data.LoaderData;
import com.IMaylatov.Recommend.Data.LoaderDataFile;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 01.04.2015.
 */
public class Main {
    public static void main(String[] args) {
        LoaderData loaderData = new LoaderDataFile();
        loaderData.loadAll();
    }
}
