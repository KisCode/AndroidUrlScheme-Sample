package keno.android.oauthapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class OAuthActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvContent;
    private Button btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        initView();
        initSchemeData();
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
            String accout = uri.getQueryParameter("account");
            String id = uri.getQueryParameter("id");

            tvContent.setText(accout + "\t" + id);
        }
    }

    private void initView() {
        tvContent = (TextView) findViewById(R.id.tv_content);
        btnAgree = (Button) findViewById(R.id.btn_agree);

        btnAgree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_agree:
                //back
                oAuthResult();
                break;
        }
    }

    private void oAuthResult() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        StringBuilder urlSb = new StringBuilder();
        String scheme = "login";
        String host = "keno.android.schemeapps";
        String path = "/main";
        int port = 8081;

        //携带参数
        boolean resultParam = true;
        String dateParam = Calendar.getInstance().getTime().toLocaleString();
        urlSb.append(scheme).append("://").append(host).append(":").append(port).append(path)
                .append("?success=").append(resultParam)
                .append("&date=").append(dateParam);
        intent.setData(Uri.parse(urlSb.toString()));
        startActivity(intent);
    }
}
