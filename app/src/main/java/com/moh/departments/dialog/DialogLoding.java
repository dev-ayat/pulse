package com.moh.departments.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.moh.departments.R;

public class DialogLoding {
    private static Dialog pDialog;
    TextView msg;

    public DialogLoding(Context act) {
        pDialog = new Dialog(act);
        pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pDialog.setContentView(R.layout.custom_dialog2);
        msg = pDialog.findViewById(R.id.loading_message);
//        img = (ImageView)pDialog.findViewById(R.id.loading_img);
        //animation = AnimationUtils.loadAnimation(act, R.anim.fade_in);
        //msg.startAnimation(animation);
        Log.e("dialog", "created");
        pDialog.setCancelable(false);
    }

    public void showDialog(String my_msg) {
        msg.setText(my_msg);
        Log.e("dialog", my_msg);
        pDialog.show();
    }

    public void hideDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
        /*android.os.Handler mHandler=new android.os.Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
        },3000);*/


    }


}
