package com.IMaylatov.Recommend.webapp.Service.Person;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 17.05.2015.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SongUrlRate {
    private String name;
    private String author;
    private String url;
    private int rate;

    public SongUrlRate(String name, String url, int rate, String author) {
        this.name = name;
        this.url = url;
        this.rate = rate;
        this.author = author;
    }
}
