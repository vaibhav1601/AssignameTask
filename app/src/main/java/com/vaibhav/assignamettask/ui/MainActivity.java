package com.vaibhav.assignamettask.ui;

import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vaibhav.assignamettask.MyApplication;
import com.vaibhav.assignamettask.R;
import com.vaibhav.assignamettask.adaptor.ItemListAdapter;
import com.vaibhav.assignamettask.recyclerview.EndlessRecyclerViewScrollListener;
import com.vaibhav.assignamettask.services.ConnectivityReceiver;
import com.vaibhav.assignamettask.utils.APIError;
import com.vaibhav.assignamettask.utils.ApiResponse;
import com.vaibhav.assignamettask.utils.ErrorUtils;
import com.vaibhav.assignamettask.utils.ProgressBar;
import com.vaibhav.assignamettask.viewModel.UserViewModel;
import com.vaibhav.data.source.remote.profile.model.ErrorJsonResponse;
import com.vaibhav.domain.model.ProfileDTO;
import com.vaibhav.domain.model.UserSuccessDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    //@formatter:off
    @BindView(R.id.rv_itemlist)                             RecyclerView rvItemlist;
    @BindView(R.id.swipeRefreshLayout)                      SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rl_user_placeholder)                     RelativeLayout rlUserPlaceHolder;


    //@formatter:on

    private static final int PAGE_SIZE = 5;
    private ItemListAdapter mItemListAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private UserViewModel mViewModel;
    private int totalCount;
    private List<UserSuccessDTO> mUserData;
    private ConnectivityReceiver MyReceiver = null;
    private boolean isFromSwapRefresh;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
        MyReceiver = new ConnectivityReceiver();
        broadcastIntent();
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mViewModel.getmUserLiveData().observe(this, this::consumeResponse);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvItemlist.setLayoutManager(mLinearLayoutManager);
        mItemListAdapter = new ItemListAdapter(this);
        rvItemlist.setAdapter(mItemListAdapter);
        endlessScrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) rvItemlist.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                if (mItemListAdapter.getItemCount() < totalCount) {
                    getUserListSize(page + 1);
                }

            }
        };
        rvItemlist.addOnScrollListener(endlessScrollListener);


        swipeRefreshLayout.setOnRefreshListener(() -> {

            isFromSwapRefresh = true;

            if (checkConnection()) {
                if (mUserData != null) {
                    mUserData.clear();
                    mItemListAdapter.clear();

                }
                getUserListSize(1);
                swipeRefreshLayout.setRefreshing(false);
                rlUserPlaceHolder.setVisibility(View.VISIBLE);
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }

        });


    }


    private void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    private void updateUI() {

        if (checkConnection())
            getUserListSize(1);

    }

    private void getUserListSize(int pageNo) {

        mViewModel.getUserList(pageNo, PAGE_SIZE);

    }

    private int layoutRes() {
        return R.layout.activity_main;
    }


    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {

            case LOADING:

                if (!swipeRefreshLayout.isRefreshing()) {
                    ProgressBar.getInstance().showPwProgressBar(MainActivity.this);
                }
                break;

            case SUCCESS:

                rlUserPlaceHolder.setVisibility(View.GONE);
                ProgressBar.getInstance().hidePwProgressBar();
                renderSuccessResponseGetAllData(apiResponse.data);
                break;

            case ERROR:
                ProgressBar.getInstance().hidePwProgressBar();
                APIError apiResponse1 = ErrorUtils.parseThrowable(apiResponse.error);
                ErrorJsonResponse errorJsonResponse = ErrorUtils.parseCustomError(apiResponse1.getMessage());
                break;

            default:
                break;
        }
    }


    private void renderSuccessResponseGetAllData(Object data) {

        ProfileDTO mProfileData = (ProfileDTO) data;
        totalCount = mProfileData.getTotal();
        int page = mProfileData.getPage();
        if (mUserData == null) {
            mUserData = new ArrayList<>();

        }


        if (mProfileData.getmUserSuccessDTOS().size() > 0) {
            mUserData.addAll(mProfileData.getmUserSuccessDTOS());
            mItemListAdapter.setItems(mUserData);
            rvItemlist.setVisibility(View.VISIBLE);

        } else {
            if (mProfileData.getmUserSuccessDTOS().isEmpty()) {
                rvItemlist.setVisibility(View.GONE);

            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (MyReceiver != null) {
            unregisterReceiver(MyReceiver);
            MyReceiver = null;
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }


    private void showSnack(boolean isConnected) {

        String message;
        int color;
        if (!isConnected) {

            message = "Sorry! Not connected to internet";
            color = Color.RED;
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
            View view = toast.getView();
            view.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            TextView text = view.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            toast.show();
            isFromSwapRefresh = false;

        } else if (isConnected && !isFromSwapRefresh) {
            message = "Good! Connected to Internet";
            color = Color.GREEN;
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
            View view = toast.getView();
            view.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            TextView text = view.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            toast.show();
            isFromSwapRefresh = false;

        }


    }

    public boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected(getApplicationContext());
        showSnack(isConnected);

        return isConnected;
    }


}
