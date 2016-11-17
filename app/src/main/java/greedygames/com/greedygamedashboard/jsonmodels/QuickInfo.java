package greedygames.com.greedygamedashboard.jsonmodels;

import java.util.ArrayList;

/**
 * Created by surajmuralidharagupta on 11/16/16.
 */
public class QuickInfo {
    private String count;
    private QuickInfoAggregation agg;
    private ArrayList<CampaignInfo> results;

    public ArrayList<CampaignInfo> getResults() {
        return results;
    }

    public void setResults(ArrayList<CampaignInfo> results) {
        this.results = results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public QuickInfoAggregation getAgg() {
        return agg;
    }

    public void setAgg(QuickInfoAggregation agg) {
        this.agg = agg;
    }
}
