package greedygames.com.greedygamedashboard.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;
import greedygames.com.greedygamedashboard.R;
import greedygames.com.greedygamedashboard.adapter.DashBoardAdapterGame;
import greedygames.com.greedygamedashboard.adapter.DashBoardAdapter;
import greedygames.com.greedygamedashboard.dashboardclient.DashBoardRestClient;
import greedygames.com.greedygamedashboard.jsonmodels.DashBoardCampaignGame;
import greedygames.com.greedygamedashboard.jsonmodels.QuickInfo;

public class DashBoardActivity extends AppCompatActivity {
    private Context mContext;
    private ProgressDialog progressDialog;
    private QuickInfo quickInfo;
    private TextView revenueTextView,brandedTextView,ctrTextView,eCpsTextView;
    private ListView dashboard_listview;
    private DashBoardCampaignGame dashBoardCampaignGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mContext=this;
        progressDialog=new ProgressDialog(mContext);
        progressDialog.setMessage("Loading Dashboard Data");
        initDashBoardViews();
        fetchQuickInfo();
    }
    public void fetchQuickInfo(){
        progressDialog.show();
        DashBoardRestClient.GET("analytics/date", getAnalyticsApiRequestParams(),new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson=new Gson();
                quickInfo=gson.fromJson(new String(responseBody),QuickInfo.class);
                progressDialog.dismiss();
                initQuickInfoData();
                fetchCampaignDetailsWithGameFilter();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public void initDashBoardViews(){
        revenueTextView=(TextView)findViewById(R.id.revenue_number_dashboard);
        brandedTextView=(TextView)findViewById(R.id.branded_number);
        ctrTextView=(TextView)findViewById(R.id.ctr_number);
        eCpsTextView=(TextView)findViewById(R.id.eCps_number);
        dashboard_listview=(ListView)findViewById(R.id.dashboard_listview);
    }
    public void initQuickInfoData(){
        revenueTextView.setText("$"+quickInfo.getAgg().getRevenue());
        brandedTextView.setText(quickInfo.getAgg().getSession());
        ctrTextView.setText(quickInfo.getAgg().getCtr());
        eCpsTextView.setText(computeCps());
    }
    public String computeCps(){
        Double revenue=Double.parseDouble(quickInfo.getAgg().getRevenue());
        Double sessions=Double.parseDouble(quickInfo.getAgg().getSession());
        Double ecps=(revenue/sessions)*1000;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(ecps);
    }
    public void fetchCampaignDetailsWithGameFilter(){
        progressDialog.show();
        DashBoardRestClient.GET("games",null,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson=new Gson();
                dashBoardCampaignGame=gson.fromJson(new String(responseBody),DashBoardCampaignGame.class);
                initDashBoardGameAdapter();
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public void fetchCampaignDetailsWithCountryFilter(){
        progressDialog.show();
        DashBoardRestClient.GET("analytics/country", getAnalyticsApiRequestParams(),new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson=new Gson();
                quickInfo.setResults(gson.fromJson(new String(responseBody),QuickInfo.class).getResults());
                progressDialog.dismiss();
                dashboard_listview.setAdapter(new DashBoardAdapter(mContext,quickInfo.getResults(), DashBoardAdapter.DisplayType.COUNTRY));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public void fetchCampaignDetailsWithDateFilter(){
        progressDialog.show();
        DashBoardRestClient.GET("analytics/date", getAnalyticsApiRequestParams(),new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson=new Gson();
                quickInfo=gson.fromJson(new String(responseBody),QuickInfo.class);
                progressDialog.dismiss();
                dashboard_listview.setAdapter(new DashBoardAdapter(mContext,quickInfo.getResults(), DashBoardAdapter.DisplayType.DATE));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public void initDashBoardGameAdapter(){
        dashboard_listview.setAdapter(new DashBoardAdapterGame(mContext,dashBoardCampaignGame.getResults()));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_date) {
            fetchCampaignDetailsWithDateFilter();
            return true;
        }
        if (id == R.id.menu_country) {
            fetchCampaignDetailsWithCountryFilter();
            return true;
        }
        if (id == R.id.menu_game) {
            fetchCampaignDetailsWithGameFilter();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
        return true;
    }

    public RequestParams getAnalyticsApiRequestParams(){
        RequestParams requestParams=new RequestParams();
        requestParams.add("from_date","1478662218");
        requestParams.add("till_date","1479267018");
        return requestParams;
    }
}
