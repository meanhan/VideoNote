package com.xuhan.videonote.bean;

import java.util.List;

/**
 * Created by xuhan on 18-8-2.
 */

public class MovieEntity {

    /**
     * count : 数量
     * start : 起始
     * total : 总数
     * subjects : 电影条目
     * title : 标题
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsEntity> subjects;

    public void setCount(int count) {
        this.count = count;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubjects(List<SubjectsEntity> subjects) {
        this.subjects = subjects;
    }

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

    public String getTitle() {
        return title;
    }

    public List<SubjectsEntity> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }

    public static class SubjectsEntity {
        /**
         * 电影条目
         * rating : 评分
         * genres : 类型(喜剧/动作/悬疑等)
         * title : 中文名
         * casts : 演员
         * collect_count :
         * original_title : 原名
         * subtype : 条目分类, movie或者tv
         * directors : 导演
         * year : 年代
         * images : 电影海报图，分别提供288px x 465px(大)，96px x 155px(中) 64px x 103px(小)尺寸
         * alt : 条目URL
         * id : 条目id
         */

        private RatingEntity rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesEntity images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsEntity> casts;
        private List<DirectorsEntity> directors;

        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setImages(ImagesEntity images) {
            this.images = images;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public void setCasts(List<CastsEntity> casts) {
            this.casts = casts;
        }

        public void setDirectors(List<DirectorsEntity> directors) {
            this.directors = directors;
        }

        public RatingEntity getRating() {
            return rating;
        }

        public String getTitle() {
            return title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public String getYear() {
            return year;
        }

        public ImagesEntity getImages() {
            return images;
        }

        public String getAlt() {
            return alt;
        }

        public String getId() {
            return id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public List<CastsEntity> getCasts() {
            return casts;
        }

        public List<DirectorsEntity> getDirectors() {
            return directors;
        }

        @Override
        public String toString() {
            return "SubjectsEntity{" +
                    "rating=" + rating +
                    ", title='" + title + '\'' +
                    ", collect_count=" + collect_count +
                    ", original_title='" + original_title + '\'' +
                    ", subtype='" + subtype + '\'' +
                    ", year='" + year + '\'' +
                    ", images=" + images +
                    ", alt='" + alt + '\'' +
                    ", id='" + id + '\'' +
                    ", genres=" + genres +
                    ", casts=" + casts +
                    ", directors=" + directors +
                    '}';
        }
    }

    public static class RatingEntity {
        /**
         * 评分
         * max : 最高评分
         * average : 评分
         * stars : 星级评价
         * min : 最低评分
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public void setMax(int max) {
            this.max = max;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public double getAverage() {
            return average;
        }

        public String getStars() {
            return stars;
        }

        public int getMin() {
            return min;
        }
    }

    public static class ImagesEntity {
        /**
         * small : 64px x 103px(小)
         * large : 288px x 465px(大)
         * medium : 96px x 155px(中)
         */

        private String small;
        private String large;
        private String medium;

        public void setSmall(String small) {
            this.small = small;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getSmall() {
            return small;
        }

        public String getLarge() {
            return large;
        }

        public String getMedium() {
            return medium;
        }
    }

    public static class CastsEntity {
        /**
         * 演员信息
         * alt : 条目url
         * avatars : 影人头像，分别提供420px x 600px(大)，140px x 200px(中) 70px x 100px(小)尺寸
         * name : 中文名
         * id : 影人条目id
         */

        private String alt;
        private AvatarsEntity avatars;
        private String name;
        private String id;

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setAvatars(AvatarsEntity avatars) {
            this.avatars = avatars;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAlt() {
            return alt;
        }

        public AvatarsEntity getAvatars() {
            return avatars;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }


    }

    public static class AvatarsEntity {
        /**
         * 影人头像
         * small : 70px x 100px(小)
         * large : 420px x 600px(大)
         * medium : 140px x 200px(中)
         */

        private String small;
        private String large;
        private String medium;

        public void setSmall(String small) {
            this.small = small;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getSmall() {
            return small;
        }

        public String getLarge() {
            return large;
        }

        public String getMedium() {
            return medium;
        }
    }

    public static class DirectorsEntity {
        /**
         * 导演信息
         * alt : 条目url
         * avatars : 演员头像
         * name : 姓名
         * id : 条目id
         */

        private String alt;
        private AvatarsEntity avatars;
        private String name;
        private String id;

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setAvatars(AvatarsEntity avatars) {
            this.avatars = avatars;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAlt() {
            return alt;
        }

        public AvatarsEntity getAvatars() {
            return avatars;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }
}
