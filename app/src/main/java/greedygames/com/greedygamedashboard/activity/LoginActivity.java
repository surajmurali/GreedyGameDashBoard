package greedygames.com.greedygamedashboard.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import greedygames.com.greedygamedashboard.constants.DashBoardConstants;
import greedygames.com.greedygamedashboard.R;
import greedygames.com.greedygamedashboard.jsonmodels.LoginResponse;

public class LoginActivity extends AppCompatActivity {
    private Context mContext;
    private ProgressDialog progressDialog;
    private EditText password;
    private AutoCompleteTextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext=this;
        progressDialog=new ProgressDialog(mContext);
        progressDialog.setMessage("Signing in ...");
        email=(AutoCompleteTextView)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
    }
    public void didClickLogin(View view){
        doLogin();
    }
    public void doLogin(){
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();
        StringEntity entity = null;
        try{
            jsonParams.put("email", email.getText().toString());
            jsonParams.put("password", password.getText().toString());
            jsonParams.put("login_as", "publisher");
            entity=new StringEntity(jsonParams.toString());
        }catch (Exception ex){
            System.out.println("Exception ex"+ex.getMessage());
        }
        progressDialog.show();
        client.post(mContext,"http://rest.greedygame.com/users/authenticate/auth",entity,"application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("Success "+new String(responseBody));
                Gson gson=new Gson();
                LoginResponse logingResponse=gson.fromJson(new String(responseBody),LoginResponse.class);
                //hardcoded
                logingResponse.setId("70");
                System.out.println("id "+logingResponse.getId()+" Token "+logingResponse.getToken());
                DashBoardConstants.loginResponse=logingResponse;
                progressDialog.dismiss();
                Intent intent = new Intent(mContext, DashBoardActivity.class);
                startActivity(intent);
//                fetchQuickInfo();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Failure "+error.getMessage());
                progressDialog.dismiss();
            }
        });
    }

}
