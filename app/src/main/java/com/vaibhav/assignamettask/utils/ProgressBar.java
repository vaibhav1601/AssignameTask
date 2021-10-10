package com.vaibhav.assignamettask.utils;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.vaibhav.assignamettask.R;


public class ProgressBar {
    static ProgressBar sProgressBar;
    Dialog dialogProgressBar;
    Context context;

    public static ProgressBar getInstance() {

        if (sProgressBar == null) {
            sProgressBar = new ProgressBar();
        }

        return sProgressBar;
    }

    public void showPwProgressBar(Context context) {

        if (dialogProgressBar == null || !dialogProgressBar.isShowing()) {
            this.context = context;
            CreateDialog(this.context);

            dialogProgressBar.show();
        }
    }

    public void hidePwProgressBar() {
        if (dialogProgressBar != null) {
//            changeColor(R.color.white);
            dialogProgressBar.dismiss();
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void CreateDialog(Context context) {
//        changeColor(R.color.transparentForBottomSheet);
        dialogProgressBar = new Dialog(context);
        dialogProgressBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogProgressBar.setContentView(R.layout.progressbar);

        android.widget.ProgressBar progressBar1 = dialogProgressBar.findViewById(R.id.progressBar1);
        if (Build.VERSION.SDK_INT > 20) {
            int colorCodeDark = context.getResources().getColor(R.color.teal_200);
            progressBar1.setIndeterminateTintList(ColorStateList.valueOf(colorCodeDark));

        }


        dialogProgressBar.setCancelable(false);
        int colorCodeDark = context.getResources().getColor(R.color.white_tran);
        //  dialogProgressBar.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogProgressBar.getWindow().setBackgroundDrawable(new ColorDrawable(colorCodeDark));
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        dialogProgressBar.getWindow().setLayout(width, height);

    }


}
