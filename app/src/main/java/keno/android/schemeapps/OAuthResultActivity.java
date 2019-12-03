package keno.android.schemeapps;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Description: 认证结果
 * Author: keno
 * CreateDate: 2019/12/3 22:05
 */

public class OAuthResultActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_result);
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
            String success = uri.getQueryParameter("success");
            String date = uri.getQueryParameter("date");

//            tvContent.setText(accout + "\t" + id);
            tvResult.setText(success + "\n" + date);
        }
    }

    private void initView() {
        tvResult = (TextView) findViewById(R.id.tv_result);
    }
}
