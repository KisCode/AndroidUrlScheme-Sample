package keno.android.schemeapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Description: mock App OAuth2 login
 * Author: keno
 * Date : 2019/12/3 15:02
 **/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivOauth;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ivOauth = findViewById(R.id.iv_oauth);

        ivOauth.setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_oauth:
                startOAuth();
                break;
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
        String appNameParam = getString(R.string.app_name);
        int idParam = 9527;
        urlSb.append(scheme).append("://").append(host).append(":").append(port).append(path)
                .append("?appName=").append(appNameParam)
                .append("&id=").append(idParam);
        intent.setData(Uri.parse(urlSb.toString()));
        startActivity(intent);
    }
}
