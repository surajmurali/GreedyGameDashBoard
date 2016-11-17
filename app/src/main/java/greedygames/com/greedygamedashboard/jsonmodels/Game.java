package greedygames.com.greedygamedashboard.jsonmodels;

import greedygames.com.greedygamedashboard.jsonmodels.Analytics;

/**
 * Created by surajmuralidharagupta on 11/16/16.
 */
public class Game {
    private String id;
    private String title;
    private Analytics analytics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Analytics getAnalytics() {
        return analytics;
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }
}
