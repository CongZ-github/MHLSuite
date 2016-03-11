package com.example.zc.mhlsuite;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zc.mhlsuite.fragment.EquipmentFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private Fragment equipmentFragment;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> skillsList = new ArrayList<>();
    private SkillsAdapter skillsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    private void initView() {
        skillsList.add("通常弹强化");
        skillsList.add("自动标记");
        skillsList.add("眠弹追加");
        skillsList.add("麻弹追加");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        skillsAdapter = new SkillsAdapter(this, skillsList);
        recyclerView.setAdapter(skillsAdapter);
    }

    private void setTabSelection(int index) {
        if (null == fragmentManager) {
            fragmentManager = getSupportFragmentManager();
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);

        switch (index) {
            case 0:
                if (null == equipmentFragment) {
                    equipmentFragment = new EquipmentFragment();
                    transaction.add(R.id.fragment_container, equipmentFragment);
                } else {
                    transaction.show(equipmentFragment);
                }
                break;
            case 1:
                break;
            case 2:
                break;
        }

//        transaction.add();

        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
//        if (fragmentMian != null) {
//            transaction.hide(fragmentMian);
//        }
//        if (contactsFragment != null) {
//            transaction.hide(contactsFragment);
//        }
//        if (fragmentLb != null) {
//            transaction.hide(fragmentLb);
//        }
//        if (fragmentPersonal != null) {
//            transaction.hide(fragmentPersonal);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class SkillsAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<String> dataList;

    SkillsAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        dataList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).title.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_content);
        }
    }
}