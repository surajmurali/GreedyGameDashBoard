package greedygames.com.greedygamedashboard.jsonmodels;

/**
 * Created by surajmuralidharagupta on 11/16/16.
 */
public class QuickInfoAggregation {
    private String session;
    private String second_clicks;
    private String ctr;
    private String revenue;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSecond_clicks() {
        return second_clicks;
    }

    public void setSecond_clicks(String second_clicks) {
        this.second_clicks = second_clicks;
    }

    public String getCtr() {
        return ctr;
    }

    public void setCtr(String ctr) {
        this.ctr = ctr;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}
