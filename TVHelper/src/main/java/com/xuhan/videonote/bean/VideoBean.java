package com.xuhan.videonote.bean;

/**
 * Created by meanhan on 2017/5/6.
 */

public class VideoBean {
    /**
     * 名称
     */
    private String name;
    /**
     * 集数
     */
    private String episodes;

    public VideoBean(String name, String episodes) {
        this.name = name;
        this.episodes = episodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }
}
