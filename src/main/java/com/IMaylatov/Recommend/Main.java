package com.IMaylatov.Recommend;

import com.IMaylatov.Recommend.Data.LoaderData;
import com.IMaylatov.Recommend.Data.LoaderDataFile;

import java.util.HashMap;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 01.04.2015.
 */
public class Main {
    public static void main(String[] args) {
        LoaderData loaderData = new LoaderDataFile();
        HashMap<String, String> files = new HashMap<>();
        files.put("person", "data/person.dat");
        files.put("song", "data/song.dat");
        files.put("rate", "data/ratings.dat");
        loaderData.loadAll(files);
    }
}
