package com.vaibhav.assignamettask.adaptor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.card.MaterialCardView;
import com.vaibhav.assignamettask.R;
import com.vaibhav.assignamettask.adaptor.viewHolder.UsersListViewHolder;
import com.vaibhav.assignamettask.listener.OnItemClickedListener;
import com.vaibhav.assignamettask.recyclerview.GenericRecyclerAdapter;
import com.vaibhav.domain.model.ProfileDTO;
import com.vaibhav.domain.model.UserSuccessDTO;

import butterknife.BindView;


public class ItemListAdapter extends GenericRecyclerAdapter<UserSuccessDTO, OnItemClickedListener<UserSuccessDTO>, UsersListViewHolder> {
    /**
     * Base constructor.
     * Allocate adapter-related objects here if needed.
     *
     * @param context Context needed to retrieve LayoutInflater
     */
    Context mContext;


    public ItemListAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public UsersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UsersListViewHolder(mContext, inflate(R.layout.template_item_list, parent));


    }
}
