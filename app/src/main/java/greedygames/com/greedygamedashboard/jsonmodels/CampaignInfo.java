package greedygames.com.greedygamedashboard.jsonmodels;

/**
 * Created by surajmuralidharagupta on 11/17/16.
 */
public class CampaignInfo {
    private long key;
    private String session;
    private String revenue;
    private String ctr;
    private String country_name;

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getCtr() {
        return ctr;
    }

    public void setCtr(String ctr) {
        this.ctr = ctr;
    }
}
