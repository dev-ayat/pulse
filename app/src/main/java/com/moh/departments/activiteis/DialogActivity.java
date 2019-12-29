package com.moh.departments.activiteis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moh.departments.R;

/**
 * Created by Administrator on 09/08/2016.
 */
public class DialogActivity {

    public static int MODE_WARNING = 1, MODE_CONFIRM = 2;
    public Context cx;
    public Activity act;
    Integer iconTitle;
    ImageView ivIconTitle;
    LinearLayout llBtnYes, llBtnNo;
    DialogClickListener mDialogClickListener;
    Dialog dialog;
    private String title, msg, okTxt = "", cancelTxt = "";
    private Button yes_btn, no_btn;
    private TextView title_tv, msg_tv;

    public DialogActivity(Activity act) {

        this.act = act;
        cx = act.getApplicationContext();
//        dialog = new Dialog(act, R.style.DialogTheme);
//        dialog.setContentView(R.layout.confirm_action_dialog1);
        INIT();

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIconTitle() {
        return iconTitle;
    }

    public void setIconTitle(Integer iconTitle) {
        this.iconTitle = iconTitle;
    }

    public String getOkTxt() {
        return okTxt;
    }

    public void setOkTxt(String okTxt) {

        this.okTxt = okTxt;
    }

    public String getCancelTxt() {
        return cancelTxt;
    }

    public void setCancelTxt(String cancelTxt) {
        this.cancelTxt = cancelTxt;
    }

    public void INIT() {
        title_tv = (TextView) dialog.findViewById(R.id.confirm_dialog_title);
//        llBtnYes = (LinearLayout) dialog.findViewById(R.id.llBtnYes);
//        llBtnNo = (LinearLayout) dialog.findViewById(R.id.llBtnNo);
        msg_tv = (TextView) dialog.findViewById(R.id.confirm_dialog_msg);
        yes_btn = (Button) dialog.findViewById(R.id.btn_yes);
        no_btn = (Button) dialog.findViewById(R.id.btn_no);
//        ivIconTitle = (ImageView) dialog.findViewById(R.id.ivIconTitle);

    }

    public void setConfirm() {
        setOkTxt("نعم");
        setCancelTxt("لا");
        setTitle("رسالة تأكيد");
//        setIconTitle(R.drawable.question);
    }

    public void setWarning() {
        setOkTxt("موافق");
        setTitle("رسالة تنبيه");
        setCancelTxt("");
        //    setIconTitle(R.drawable.warning);
    }

    public void showDialog(int mode, final DialogClickListener mDialogClickListener) {

        if (mode == MODE_WARNING)
            setWarning();
        if (mode == MODE_CONFIRM)
            setConfirm();

        if (!dialog.isShowing()) {
            this.mDialogClickListener = mDialogClickListener;
            title_tv.setText(getTitle());
            msg_tv.setText(getMsg());
            if (getOkTxt().length() > 0) {
                yes_btn.setText(getOkTxt());
                yes_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideDialog();
                        mDialogClickListener.onYesClick();
                    }
                });
            } else
                llBtnYes.setVisibility(View.GONE);
            if (getCancelTxt().length() > 0) {
                llBtnNo.setVisibility(View.VISIBLE);
                no_btn.setText(getCancelTxt());
                no_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideDialog();
                        mDialogClickListener.onNoClick();
                    }
                });
            } else
                llBtnNo.setVisibility(View.GONE);
            if (getIconTitle() == null)
                ivIconTitle.setVisibility(View.GONE);
            else
                ivIconTitle.setImageResource(getIconTitle());
            dialog.show();
        }
    }

    public void hideDialog() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    public interface DialogClickListener {
        public void onYesClick();

        public void onNoClick();
    }
}
