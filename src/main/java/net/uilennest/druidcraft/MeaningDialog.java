package net.uilennest.druidcraft;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.os.Bundle;
/**
 * Created by Vermaas on 10/11/2017.
 */

public class MeaningDialog extends DialogFragment {
    @Override

    public Dialog onCreateDialog(Bundle bundle) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}