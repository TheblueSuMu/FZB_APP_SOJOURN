package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.SalesDetailsAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.TeamMemberBean;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层5-销售详情-他的顾问
public class SalesDetails extends AppCompatActivity {

    ImageView sales_details_img;
    RecyclerView sales_details_rv;
    SalesDetailsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_details);

        initView();

    }

    private void initView() {
		
		StatusBar.makeStatusBarTransparent(this);
		
        sales_details_img = findViewById(R.id.sales_details_img);
        sales_details_rv = findViewById(R.id.sales_details_rv);

        sales_details_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    @SuppressLint("WrongConstant")
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane("", "3", "", FinalContents.getUserID(), FinalContents.getAgentId());
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final TeamMemberBean teamMemberBean) {
                        LinearLayoutManager manager = new LinearLayoutManager(SalesDetails.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        sales_details_rv.setLayoutManager(manager);
                        adapter = new SalesDetailsAdapter(teamMemberBean.getData().getRows());
                        sales_details_rv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        adapter.setOnItemClickListener(new SalesDetailsAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                finish();
                                FinalContents.setAgentId(teamMemberBean.getData().getRows().get(postion).getId());
                                Intent intent = new Intent( SalesDetails.this, SalesDetailsDetailsActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "TeamMemberActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
