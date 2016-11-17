package greedygames.com.greedygamedashboard.dashboardclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import greedygames.com.greedygamedashboard.constants.DashBoardConstants;

/**
 * Created by surajmuralidharagupta on 11/17/16.
 */
public class DashBoardRestClient {
    private static final String BASE_URL = "http://rest.greedygame.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void GET(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        addHeaders();
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
    private static void addHeaders(){
        client.addHeader("Authorization","Token"+" "+ DashBoardConstants.loginResponse.getToken());
        client.addHeader("UserGroup","70");
    }
}
