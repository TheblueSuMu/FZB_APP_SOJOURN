package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nanchen.wavesidebar.WaveSideBarView;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.PopAdapter;
import com.xcy.fzb.captain_team.adapter.TeamMemberAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.persente.ContactModel;
import com.xcy.fzb.captain_team.persente.LetterComparator;
import com.xcy.fzb.captain_team.persente.PinnedHeaderDecoration;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.database.TeamMemberBean;
import com.xcy.fzb.captain_team.persente.TeamMemberData;
import com.xcy.fzb.captain_team.service.MyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//TODO 圈层5-1团队人员
public class TeamMemberActivity extends AppCompatActivity implements View.OnClickListener, TeamMemberAdapter.ItemOnClick {

    ImageView team_member_img1;
    ImageView team_member_img2;

    TextView team_member_tv;

    EditText team_member_et;

    LinearLayout team_member_ll1;
    LinearLayout team_member_ll2;
    LinearLayout team_member_ll3;
    LinearLayout team_member_ll4;

    RecyclerView team_member_rv;

    WaveSideBarView mWaveSideBarView;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    TeamMemberAdapter mAdapter;
    private Intent intent;
    private List<TeamMemberBean.DataBean.RowsBean> rows;
    private List<TeamMemberData> list;
    int flag = 0;
    private PopupWindow p;
    private String status = "";
    private String string1 = "添加销售";
    private String string2 = "批量修改销售级别";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_member);


        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        if (FinalContents.getIdentity().equals("62")) {
            team_member_img2.setVisibility(View.GONE);
        }

        team_member_img1 = findViewById(R.id.team_member_img1);
        team_member_img2 = findViewById(R.id.team_member_img2);

        team_member_tv = findViewById(R.id.team_member_tv);

        team_member_et = findViewById(R.id.team_member_et);

        team_member_ll1 = findViewById(R.id.team_member_ll1);
        team_member_ll2 = findViewById(R.id.team_member_ll2);
        team_member_ll3 = findViewById(R.id.team_member_ll3);
        team_member_ll4 = findViewById(R.id.team_member_ll4);

        team_member_rv = findViewById(R.id.team_member_rv);

        mWaveSideBarView = findViewById(R.id.member_team_side_bar);

        team_member_img1.setOnClickListener(this);
        team_member_img2.setOnClickListener(this);
        team_member_tv.setOnClickListener(this);
        team_member_ll1.setOnClickListener(this);
        team_member_ll3.setOnClickListener(this);

        mContactModels = new ArrayList<>();
        list = new ArrayList<>();
        decoration = new PinnedHeaderDecoration();
        initData("", "2", "");

        team_member_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = team_member_et.getText().toString();
                    if (flag == 0) {
                        if (team_member_ll2.getVisibility() == View.VISIBLE) {
                            String s2 = team_member_tv.getText().toString();
                            if (s2.equals("全部")) {
                                initData(s, "2", "");
                            } else if (s2.equals("不看禁用")) {
                                initData(s, "2", "1");
                            } else if (s2.equals("只看禁用")) {
                                initData(s, "2", "0");
                            }
                        } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                            String s2 = team_member_tv.getText().toString();
                            if (s2.equals("全部")) {
                                initData(s, "3", "");
                            } else if (s2.equals("不看禁用")) {
                                initData(s, "3", "1");
                            } else if (s2.equals("只看禁用")) {
                                initData(s, "3", "0");
                            }
                        }
                        flag = 1;
                    }
                    return true;
                }
                return false;
            }
        });

    }

    //TODO 点击事件
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一级
            case R.id.team_member_img1:
                finish();
                break;
//            TODO 菜单栏
            case R.id.team_member_img2:
                PopWindow1();
                break;
//            TODO 全部
            case R.id.team_member_tv:
                PopWindow();
                break;
//            TODO 销售
            case R.id.team_member_ll1:
                if (FinalContents.getIdentity().equals("60")) {
                    string1 = "添加销售";
                    string2 = "批量修改销售级别";
                }
                team_member_ll2.setVisibility(View.VISIBLE);
                team_member_ll4.setVisibility(View.GONE);
                String s = team_member_et.getText().toString();
                String s2 = team_member_tv.getText().toString();
                if (s2.equals("全部")) {
                    initData(s, "2", "");
                } else if (s2.equals("不看禁用")) {
                    initData(s, "2", "1");
                } else if (s2.equals("只看禁用")) {
                    initData(s, "2", "0");
                }
                break;
//            TODO 顾问
            case R.id.team_member_ll3:
                if (FinalContents.getIdentity().equals("61")) {
                    string1 = "添加顾问";
                    string2 = "批量修改顾问级别";
                }
                team_member_ll2.setVisibility(View.GONE);
                team_member_ll4.setVisibility(View.VISIBLE);
                String s1 = team_member_et.getText().toString();
                String s3 = team_member_tv.getText().toString();
                if (s3.equals("全部")) {
                    initData(s1, "3", "");
                } else if (s3.equals("不看禁用")) {
                    initData(s1, "3", "1");
                } else if (s3.equals("只看禁用")) {
                    initData(s1, "3", "0");
                }
                break;
        }

    }

    //TODO 弹窗
    private void PopWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_popup, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        p.setTouchable(true);
        p.setFocusable(true);
        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOff;
        int buttonWidth = team_member_tv.getWidth();
        int popupwindowWidth = p.getContentView().getMeasuredWidth();
        xOff = buttonWidth - popupwindowWidth;
        p.showAsDropDown(team_member_tv, xOff, 0);

        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }

    // TODO 填写数据
    private void handleListView(View contentView) {

        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("不看禁用");
        list.add("只看禁用");

        PopAdapter popAdapter = new PopAdapter(list);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                String s = team_member_et.getText().toString();
                if (postion == 0) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "");
                    }
                } else if (postion == 1) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "1");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "1");
                    }
                } else if (postion == 2) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "0");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "0");
                    }
                }
                team_member_tv.setText(list.get(postion));
                p.dismiss();
            }
        });

    }

    //TODO 弹窗
    private void PopWindow1(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_popup, null);
        //处理popWindow 显示内容
        handleListView1(contentView);
        //创建并显示popWindow
        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        p.setTouchable(true);
        p.setFocusable(true);
        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOff;
        int buttonWidth = team_member_img2.getWidth();
        int popupwindowWidth = p.getContentView().getMeasuredWidth();
        xOff = buttonWidth - popupwindowWidth;
        p.showAsDropDown(team_member_img2,xOff,0);

        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }

    // TODO 填写数据
    private void handleListView1(View contentView) {

        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final List<String> list = new ArrayList<>();
        list.add(string1);
        list.add(string2);

        PopAdapter popAdapter = new PopAdapter(list);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                if (postion == 0) {
                    if (string1.equals("添加顾问")) {
                        Intent intent = new Intent(TeamMemberActivity.this,AddAConsultantActivity.class);
                        startActivity(intent);
                    } else if (string1.equals("添加销售")) {
                        Intent intent = new Intent(TeamMemberActivity.this,AddSalesActivity.class);
                        startActivity(intent);
                    }
                } else if (postion == 1) {
                    Intent intent = new Intent(TeamMemberActivity.this,BatchModifyingActivity.class);
                    startActivity(intent);
                }

                p.dismiss();
            }
        });

    }

    //TODO 获取数据
    @SuppressLint("WrongConstant")
    private void initData(String searcName, String type, String loginFlag) {
        mContactModels.clear();
        list.clear();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        team_member_rv.setLayoutManager(manager);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane(searcName, type, loginFlag, FinalContents.getUserID(), FinalContents.getUserID());
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamMemberBean teamMemberBean) {
                        rows = teamMemberBean.getData().getRows();
                        for (int i = 0; i < rows.size(); ++i) {
                            ContactModel contactModel = new ContactModel(rows.get(i).getName());
                            TeamMemberData data = new TeamMemberData();
                            data.setIdentity(rows.get(i).getIdentity());
                            data.setLoginDate(rows.get(i).getLoginDate());
                            data.setLoginFlag(rows.get(i).getLoginFlag());
                            data.setPhone(rows.get(i).getPhone());
                            data.setPhoto(rows.get(i).getPhoto());
                            data.setType(rows.get(i).getType());
                            list.add(data);
                            mContactModels.add(contactModel);
                        }
                        initDatas();
                        flag = 0;
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

    //TODO 通讯设置
    private void initDatas() {
// RecyclerView设置相关
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        team_member_rv.addItemDecoration(decoration);
        mAdapter = new TeamMemberAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setList(list);
        mAdapter.setItemOnClick(this);
        team_member_rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

// 侧边设置相关
        mWaveSideBarView.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mContactModels.size(); i++) {
                    if (mContactModels.get(i).getIndex().equals(letter)) {
                        ((LinearLayoutManager) team_member_rv.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });

    }

    //TODO item点击事件
    @Override
    public void itemClick(String itemName) {
        for (int i = 0;i < rows.size();i++){
            if (rows.get(i).getName().equals(itemName)) {
                FinalContents.setAgentId(rows.get(i).getId());
                Intent intent = new Intent(TeamMemberActivity.this,SalesDetailsDetailsActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String s = team_member_et.getText().toString();
        if (team_member_ll2.getVisibility() == View.VISIBLE) {
            String s2 = team_member_tv.getText().toString();
            if (s2.equals("全部")) {
                initData(s, "2", "");
            } else if (s2.equals("不看禁用")) {
                initData(s, "2", "1");
            } else if (s2.equals("只看禁用")) {
                initData(s, "2", "0");
            }
        } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
            String s2 = team_member_tv.getText().toString();
            if (s2.equals("全部")) {
                initData(s, "3", "");
            } else if (s2.equals("不看禁用")) {
                initData(s, "3", "1");
            } else if (s2.equals("只看禁用")) {
                initData(s, "3", "0");
            }
        }

    }
}
