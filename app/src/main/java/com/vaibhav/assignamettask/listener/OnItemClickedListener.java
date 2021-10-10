package com.vaibhav.assignamettask.listener;


import com.vaibhav.assignamettask.recyclerview.BaseRecyclerListener;

public interface OnItemClickedListener<T> extends BaseRecyclerListener {

    void onItemClicked(T item, int position);


}
