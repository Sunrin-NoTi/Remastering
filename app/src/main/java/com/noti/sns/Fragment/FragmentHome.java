package com.noti.sns.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.noti.sns.Activity.ActivitySetting;
import com.noti.sns.ListItem.ListItemHomeCard;
import com.noti.sns.R;
import com.noti.sns.Utility.UListsave;
import com.noti.sns.ViewAdapter.ViewAdapterHome;
import com.noti.sns.ViewAdapter.ViewHomeItemTouchHelper;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements ViewAdapterHome.OnStartDragListener {

    public static ItemTouchHelper mItemTouchHelper;
    RecyclerView recyclerView;
    ViewAdapterHome adapter;
    ArrayList<ListItemHomeCard> contacts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frg_nemu_home, container, false);
        Intent intent_settitng = new Intent(getActivity(), ActivitySetting.class);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        contacts = UListsave.HomeCardList.get_Home_List();
        adapter = new ViewAdapterHome(getActivity(), contacts, this);
        ViewHomeItemTouchHelper mCallback = new ViewHomeItemTouchHelper(adapter);
        mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        ImageView goToSetting_Home = rootView.findViewById(R.id.goToSetting_Home);
        goToSetting_Home.setOnClickListener(view -> startActivity(intent_settitng));

        return rootView;
    }

    @Override
    public void onPause() {
        UListsave.HomeCardList.put_Home_List(contacts);
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStartDrag(ViewAdapterHome.Holder holder) {
        mItemTouchHelper.startDrag(holder);
    }
}
