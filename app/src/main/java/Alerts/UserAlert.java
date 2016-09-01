package Alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class UserAlert
{

    public void DisplayInvalidLoginAlert(Activity object)
    {
        AlertDialog.Builder newbuilder=new AlertDialog.Builder(object);
        newbuilder.setTitle("התראה                    ");
        newbuilder.setMessage("שם המשתמש או הסיסמא שגויים");
        newbuilder.setCancelable(true);
        newbuilder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        AlertDialog builder=newbuilder.create();
        builder.show();
    }
}
