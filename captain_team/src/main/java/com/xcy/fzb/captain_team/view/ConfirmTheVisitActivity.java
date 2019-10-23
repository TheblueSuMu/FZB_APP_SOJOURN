package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.VisitSaveBean;
import com.xcy.fzb.captain_team.persente.LocationUtils;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 确认到访
public class ConfirmTheVisitActivity extends AppCompatActivity {
    private TextView comfirm_client_name;
    private TextView comfirm_project_name;
    private TextView comfirm_location;
    private Button confirm_the_visit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_the_visit);
//        initLocation();
        initView();
    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        comfirm_client_name = findViewById(R.id.comfirm_client_name);
        comfirm_project_name = findViewById(R.id.comfirm_project_name);
        comfirm_location = findViewById(R.id.comfirm_location);
        confirm_the_visit_btn = findViewById(R.id.confirm_the_visit_btn);

        Location location = LocationUtils.getInstance(ConfirmTheVisitActivity.this).showLocation();
        if (location != null){
            String address = "纬度："+location.getLatitude()+"经度："+location.getLongitude();
//            String location_string = getAddress(location);
//            comfirm_location.setText(address);
//            Toast.makeText(this, "当前的位置"+location_string, Toast.LENGTH_SHORT).show();
            updateWithNewLocation(location);
        }

        String name = getIntent().getStringExtra("name");
        String project = getIntent().getStringExtra("project");
        comfirm_client_name.setText(name);
        comfirm_project_name.setText(project);

        confirm_the_visit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initVisitSave();
            }
        });
    }


    private void updateWithNewLocation(Location location) {
        String latLongString;
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "纬度:" + lat + "\n经度:" + lng;
        } else {
            latLongString = "无法获取地理信息";
        }
        List<Address> addList = null;
        Geocoder ge = new Geocoder(this, Locale.getDefault());
        try {
            addList = ge.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if(addList!=null && addList.size()>0){
                for(int i=0; i<addList.size(); i++){
                    Address ad = addList.get(i);
                    latLongString += "\n";
                    latLongString += ad.getCountryName() + ";" + ad.getLocality();
                }
            }else {
                Log.i("jwd","判断不通过"+addList.size());
            }
            comfirm_location.setText("您当前的位置是:\n" +latLongString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("jwd","错误"+e);
        }
    }

    //放入经纬度就可以了
    public String getAddress(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String data = address.toString();
                int startCity = data.indexOf("1:\"") + "1:\"".length();
                int endCity = data.indexOf("\"", startCity);
                String city = data.substring(startCity, endCity);

                int startPlace = data.indexOf("feature=") + "feature=".length();
                int endplace = data.indexOf(",", startPlace);
                String place = data.substring(startPlace, endplace);
                return city + place ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "获取失败";
    }


    // TODO 添加到访信息数据
    private void initVisitSave(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<VisitSaveBean> userMessage = fzbInterface.getVisitSave(FinalContents.getPreparationId(),"","","",FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VisitSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(VisitSaveBean visitSaveBean) {
                        Toast.makeText(ConfirmTheVisitActivity.this, visitSaveBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
