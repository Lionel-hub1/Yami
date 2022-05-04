package lio.foody.yami;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class Coms {
    Context context;

    public Coms(Context context) {
        this.context = context;
    }
    public void message(String title,String mess){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(mess);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("OK",null);
        builder.show();
    }
}
