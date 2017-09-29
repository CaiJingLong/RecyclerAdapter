package com.sxw365.recycleradapterhelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxw365.recycleradapterhelper.item.String1Cell;
import com.sxw365.recycleradapterhelper.item.String2Cell;
import com.sxw365.recycleradapterhelper.item.StringWrapper;
import com.sxw365.recyclerhelper.adapter.RVAdapter;
import com.sxw365.recyclerhelper.adapter.RVHolder;
import com.sxw365.recyclerhelper.adapter.loadmore.RvLoadMoreAdapter;
import com.sxw365.recyclerhelper.refresh.RefreshHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private List<Object> list = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    private RVAdapter<Object> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_list);

        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RvLoadMoreAdapter<>(list);

        StringWrapper str1 = new StringWrapper("1") {
            @Override
            public int getType() {
                return 1;
            }
        };
        StringWrapper str2 = new StringWrapper("2") {
            @Override
            public int getType() {
                return 2;
            }
        };
        adapter.addType(1, new String1Cell() {
            @Override
            public void bindData(RVHolder holder, int position) {
                Object o = list.get(position);
                if (o instanceof StringWrapper) {
                    holder.getTextView(R.id.tv_name).setText(((StringWrapper) o).getContent());
                }
            }
        });
        adapter.addType(0, new String2Cell());
        adapter.addType(2, new String2Cell());
        adapter.addType(3, new String2Cell());
        adapter.addType(4, new String2Cell());
        adapter.setProcessor(new RVAdapter.TypeProcessor() {
            @Override
            public int type(int position) {
                Object o = list.get(position);
                if (o instanceof StringWrapper) {
                    return ((StringWrapper) o).getType();
                }
                return 0;
            }
        });

        list.add(str1);
        list.add(str1);
        list.add(str2);
        list.add(str1);
        list.add(str2);
        list.add(str1);
        list.add(str2);
        list.add(str1);
        list.add(str2);
        list.add(str1);
        list.add(str2);
        list.add(str1);
        list.add(str2);
        list.add(str2);
        list.add(str1);

        rv.setAdapter(adapter);

        refreshLayout = RefreshHelper.INSTANCE.attachToSwipeRefreshLayout(rv, this);
    }

    private static Handler handler = new Handler();

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
//                StringWrapper stringWrapper = new StringWrapper("3");
//                list.add(0, stringWrapper);
//                list.add(0, stringWrapper);
//                list.add(0, stringWrapper);
////                adapter.notifyDataSetChanged();
//                adapter.notifyItemRangeInserted(0, 3);

                Collections.swap(list, 2, 4);

                adapter.notifyItemMoved(2, 4);
            }
        }, 3000);
    }
}
