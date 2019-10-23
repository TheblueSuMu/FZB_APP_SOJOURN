package com.xcy.fzb.all.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.persente.StatusBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AboutFZBActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout fzb_return;
    private ImageView fzb_fx;
    private RelativeLayout fzb_jc;
    private RelativeLayout fzb_mz;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_fzb);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        fzb_return = findViewById(R.id.fzb_return);
        fzb_fx = findViewById(R.id.fzb_fx);
        fzb_jc = findViewById(R.id.fzb_jc);
        fzb_mz = findViewById(R.id.fzb_mz);

        fzb_return.setOnClickListener(this);
        fzb_fx.setOnClickListener(this);
        fzb_jc.setOnClickListener(this);
        fzb_mz.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.fzb_return:
                finish();
                break;
//                TODO 分享
            case R.id.fzb_fx:
                FinalContents.showShare("房app下载", "http://admin.fangzuobiao.com:88/fangfang/static/down/index.html", "app下载", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3055880154,1625749017&fm=26&gp=0.jpg", "http://admin.fangzuobiao.com:88/fangfang/static/down/index.html", AboutFZBActivity.this);
                break;
//                TODO 检测版本
            case R.id.fzb_jc:
                Toast.makeText(AboutFZBActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
//                TODO 免责声明
            case R.id.fzb_mz:
                intent = new Intent(AboutFZBActivity.this, DisclaimerActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public final static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     * @param bitmap
     * @param maxkb
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb&& options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }
}
