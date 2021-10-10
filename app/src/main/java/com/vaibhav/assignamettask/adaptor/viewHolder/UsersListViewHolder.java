package com.vaibhav.assignamettask.adaptor.viewHolder;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.vaibhav.assignamettask.R;
import com.vaibhav.assignamettask.listener.OnItemClickedListener;
import com.vaibhav.assignamettask.recyclerview.BaseViewHolder;
import com.vaibhav.domain.model.ProfileDTO;
import com.vaibhav.domain.model.UserSuccessDTO;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class UsersListViewHolder extends BaseViewHolder<UserSuccessDTO, OnItemClickedListener<UserSuccessDTO>> {

    private Context context;
    private Unbinder unbinder;

    //@formatter:off
    @BindView(R.id.iv_profile_img)                              ImageView ivProfileImg;
    @BindView(R.id.tv_name_label)                               TextView tvNameLabel;
    @BindView(R.id.tv_name)                                     TextView tvName;
    @BindView(R.id.tvtv_email_label)                            TextView tvtvEmailLabel;
    @BindView(R.id.tvtv_email)                                  TextView tvtvEmail;
    @BindView(R.id.cl_header)                                   ConstraintLayout clHeader;
    @BindView(R.id.materialCardView)                            CardView materialCardView;
    //@formatter:on


    public UsersListViewHolder(Context context, View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);

        this.context = context;
    }


    @Override
    public void onBind(UserSuccessDTO item, @Nullable OnItemClickedListener<UserSuccessDTO> listener) {
        if (item != null) {
            if (!TextUtils.isEmpty(item.getmFirstName()) && !TextUtils.isEmpty(item.getmLastName())) {
                tvName.setText(item.getmFirstName() + " " + item.getmLastName());

            }

            if (!TextUtils.isEmpty(item.getmEmail())) {
                tvtvEmail.setText(item.getmEmail());
            }


            Glide.with(context)
                    .load(item.getmAvatar())
                    .placeholder(R.drawable.ic_twotone_perm_identity_24)
                    .error(R.drawable.ic_twotone_perm_identity_24)
                    .into(ivProfileImg);
        }


    }


}