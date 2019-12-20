package com.xcy.fzb.captain_team.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nanchen.wavesidebar.WaveSideBarView;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.modle.ClientBean;
import com.xcy.fzb.all.persente.ContactModel;
import com.xcy.fzb.all.persente.LetterComparator;
import com.xcy.fzb.all.persente.MyClientName;
import com.xcy.fzb.all.persente.PinnedHeaderDecoration;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.ClientParticularsActivity;
import com.xcy.fzb.all.view.ReportActivity;
import com.xcy.fzb.captain_team.adapter.ContactsAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class Captain_Team_MyClientFragment1 extends Fragment implements ContactsAdapter.ItemOnClick {

    WaveSideBarView mWaveSideBarView;
    RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private List<ClientBean.DataBean> data;
    private Context context;
    private ImageView all_no_information;
    private int size = 0;
    int i = 0;

    public Captain_Team_MyClientFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());
        EventBus.getDefault().register(this);
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_my_client_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        i = 0;
        mWaveSideBarView = getActivity().findViewById(R.id.main_side_bar);
        mRecyclerView = getActivity().findViewById(R.id.main_recycler);
        all_no_information = getActivity().findViewById(R.id.all_no_information);

        inithot("");

    }


    private void initDatas() {
// RecyclerView设置相关
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        Log.i("MyCL", "集合长度：" + mContactModels.size());

        mAdapter = new ContactsAdapter();
        mRecyclerView.addItemDecoration(decoration);
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setItemOnClick(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter = new ContactsAdapter();
        mRecyclerView.addItemDecoration(decoration);
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setItemOnClick(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

// 侧边设置相关
        mWaveSideBarView.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mContactModels.size(); i++) {
                    if (mContactModels.get(i).getIndex().equals(letter)) {
                        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
        Log.i("MyCL", "5555");
        NewlyIncreased.setTest(true);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(MyClientName myClientName) {
        String name = myClientName.getName();
        Log.i("MyCL", "廣播:" + name);
//        if (NewlyIncreased.isTest()) {
            size++;
            inithot(name);
            NewlyIncreased.setTest(false);
//        }
    }

    private void inithot(String name) {
        mContactModels = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        decoration = new PinnedHeaderDecoration();
        Log.i("MyCL", "inithot");
        Retrofit.Builder builder = new Retrofit.Builder();
        Log.i("MyCL", "4");
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        Log.i("MyCL", "3");
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "2");
        Observable<ClientBean> client = fzbInterface.getClient(name, FinalContents.getUserID() + "","1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        mContactModels.clear();
                        data = clientBean.getData();
                        if (data.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < data.size(); ++i) {
                                ContactModel contactModel = new ContactModel(data.get(i).getName() + "@" + data.get(i).getId());
                                mContactModels.add(contactModel);
                            }
                            initDatas();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                        Log.i("MyCL", "客户列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void itemClick(String itemName) {

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append(itemName);

        if (FinalContents.getNUM().equals("1")) {
            for (int j = 0; j < append.length(); ++j) {
                if (append.substring(j, j + 1).equals("@")) {
                    FinalContents.setClientName(append.substring(0, j));
                    FinalContents.setCustomerID(append.substring(j + 1));
                    getActivity().finish();
                    FinalContents.setNUM("0");
                    Intent intent = new Intent(context, ReportActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        } else {
            for (int j = 0; j < append.length(); ++j) {
                if (append.substring(j, j + 1).equals("@")) {
                    FinalContents.setCustomerID(append.substring(j + 1));
                    Intent intent = new Intent(getContext(), ClientParticularsActivity.class);
                    startActivity(intent);
                    Log.i("团队长", "contacts.get(position).getName()：" + append.substring(j + 1));
                    break;
                }
            }

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (i == 0) {
            i = 1;
        } else {
            inithot("");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
