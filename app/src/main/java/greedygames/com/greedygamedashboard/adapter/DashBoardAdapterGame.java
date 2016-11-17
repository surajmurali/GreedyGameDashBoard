package greedygames.com.greedygamedashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import greedygames.com.greedygamedashboard.R;
import greedygames.com.greedygamedashboard.jsonmodels.Game;

/**
 * Created by surajmuralidharagupta on 11/16/16.
 */
public class DashBoardAdapterGame extends BaseAdapter{
    private ArrayList<Game> games;
    private LayoutInflater inflater = null;
    Context mContext;
    public DashBoardAdapterGame(Context context, ArrayList<Game> games) {
        super();
        this.games =games;
        this.mContext=context;
        this.mContext.databaseList();
        inflater=(LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        if(games ==null)return 0;
        return games.size();
    }

    @Override
    public Game getItem(int position) {
        return games.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        TitleViewHolder holder;
        Game game = getItem(position);
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
        holder.title.setText(game.getTitle());
        holder.revenue.setText("$ "+game.getAnalytics().getRevenue());
        holder.brandedSessions.setText("Branded Sessions "+game.getAnalytics().getBranded_sessions());
        holder.ctr.setText("");
        return vi;
    }
    public static class TitleViewHolder {
        public TextView title, revenue,brandedSessions,ctr,ecps;
    }
}
