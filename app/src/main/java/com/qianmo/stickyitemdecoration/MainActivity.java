package com.qianmo.stickyitemdecoration;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.qianmo.stickyitemdecoration.adapter.MyAdapter;
import com.qianmo.stickyitemdecoration.api.MovieApiService;
import com.qianmo.stickyitemdecoration.base.BaseResponse;
import com.qianmo.stickyitemdecoration.bean.ComingBean;
import com.qianmo.stickyitemdecoration.bean.MovieBean;
import com.qianmo.stickyitemdecoration.bean.NameBean;
import com.qianmo.stickyitemdecoration.utils.Constant;
import com.qianmo.stickyitemdecoration.view.FloatingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    private List<ComingBean> datas;
    private Map<Integer,String> keys=new HashMap<>();//存放所有key的位置和内容
    private MyAdapter adapter;
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 请求数据
     */
    private void initData() {
        datas = new ArrayList<>();
        requestNetwork();
    }

    /**
     * 初始化UI
     */
    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recycleview.setLayoutManager(manager);

        keys.put(0,"欧美大片");
        keys.put(2,"国产剧透");
        keys.put(4,"印度神剧");

        final FloatingItemDecoration floatingItemDecoration=new FloatingItemDecoration(this, Color.GRAY,1,1);
        floatingItemDecoration.setKeys(keys);
        floatingItemDecoration.setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics()));
        recycleview.addItemDecoration(floatingItemDecoration);


        adapter = new MyAdapter(mContext, datas);
        recycleview.setHasFixedSize(true);
        recycleview.setAdapter(adapter);
    }



    /**
     * 请求网络
     */
    private void requestNetwork() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        movieApiService.getMovieDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<MovieBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponse<MovieBean> movieBeanBaseResponse) {
                        datas.addAll(movieBeanBaseResponse.getData().getComing());
                        initView();
//                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
