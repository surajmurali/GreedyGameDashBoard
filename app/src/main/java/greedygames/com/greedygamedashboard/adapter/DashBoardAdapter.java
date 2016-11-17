package greedygames.com.greedygamedashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import greedygames.com.greedygamedashboard.R;
import greedygames.com.greedygamedashboard.jsonmodels.CampaignInfo;


/**
 * Created by surajmuralidharagupta on 11/17/16.
 */
public class DashBoardAdapter extends BaseAdapter{
    private ArrayList<CampaignInfo> campaignInfoResult;
    private LayoutInflater inflater = null;
    private SimpleDateFormat dateFormatter;
    private Context mContext;
    private DisplayType displayType;
    public enum DisplayType {
        COUNTRY, DATE, GAME
    }
    public DashBoardAdapter(Context context, ArrayList<CampaignInfo> campaignInfo, DisplayType displayType) {
        super();
        this.campaignInfoResult =campaignInfo;
        this.mContext=context;
        dateFormatter= new SimpleDateFormat("yyyy-MM-dd");
        this.mContext.databaseList();
        this.displayType=displayType;
        inflater=(LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        if(campaignInfoResult ==null)return 0;
        return campaignInfoResult.size();
    }

    @Override
    public CampaignInfo getItem(int position) {
        return campaignInfoResult.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        TitleViewHolder holder;
        CampaignInfo campaignInfo = getItem(position);
        if (convertView == null) {
            vi = inflater.inflate(R.layout.dashboard_list_item, null);
            holder = new TitleViewHolder();
            holder.title=(TextView)vi.findViewById(R.id.title);
            holder.revenue =(TextView)vi.findViewById(R.id.revenue);
            holder.brandedSessions=(TextView) vi.findViewById(R.id.branded_sessions);
            holder.ctr=(TextView) vi.findViewById(R.id.ctr);
            holder.ecps=(TextView)vi.findViewById(R.id.ecps);
            vi.setTag(holder);
        } else {
            holder = (TitleViewHolder) vi.getTag();
        }
        holder.title.setText(parseTitle(campaignInfo));
        holder.revenue.setText("$ "+campaignInfo.getRevenue());
        holder.brandedSessions.setText("Branded Sessions "+campaignInfo.getSession());
        holder.ctr.setText("Ctr "+campaignInfo.getCtr());
        return vi;
    }
    public String parseTitle(CampaignInfo campaignInfo){
        switch (displayType){
            case COUNTRY:
                    return campaignInfo.getCountry_name();
            case DATE:
                return parseDate(campaignInfo.getKey());
        }
        return "";
    }
    public String parseDate(long epochDate){
        Date date=new Date((epochDate*1000));
        String format = dateFormatter.format(date);
        return format;
    }
    public static class TitleViewHolder {
        public TextView title, revenue,brandedSessions,ctr,ecps;
    }
}
