package keno.android.schemeapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
* Description: mock App OAuth2 login
* Author: keno
* Date : 2019/12/3 15:02
**/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOauth;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSchemeData();
    }

    private void initView() {
        btnOauth = (Button) findViewById(R.id.btn_oauth);

        btnOauth.setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_oauth:
                startOAuth();
                break;
        }
    }

    private void initSchemeData() {
        Uri uri = getIntent().getData();
        if (uri != null) {
            Log.i("initSchemeData", uri.toString());
            String scheme = uri.getScheme();
            String host = uri.getHost();
            String path = uri.getPath();
            int port = uri.getPort();

            //参数部分
            String success = uri.getQueryParameter("success");
            String date = uri.getQueryParameter("date");

//            tvContent.setText(accout + "\t" + id);
            tvResult.setText(success + "\n" + date);
        }
    }

    private void startOAuth() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        StringBuilder urlSb = new StringBuilder();
        String scheme = "oauth";
        String host = "keno.android.oauthapp";
        String path = "/login";
        int port = 2202;

        //携带参数
        String accountParam = "keno@gmail.com";
        int idParam = 9527;
        urlSb.append(scheme).append("://").append(host).append(":").append(port).append(path)
                .append("?account=").append(accountParam)
                .append("&id=").append(idParam);
        intent.setData(Uri.parse(urlSb.toString()));
        startActivity(intent);
    }
}
