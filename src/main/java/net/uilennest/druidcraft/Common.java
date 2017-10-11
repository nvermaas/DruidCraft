package net.uilennest.druidcraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;



public class Common {
  public static List<Integer> picks = new ArrayList();
  
  public static Integer pickCard(Integer max)  {
    return Integer.valueOf(new Random().nextInt(max));
  }

  public static List<Integer> pickCards(Integer numberOfCards, Integer max)  {
    ArrayList cards = new ArrayList();
    Random random = new Random();
    int i = 0;
    while (i < numberOfCards.intValue()) {
      Integer picked_id = Integer.valueOf(random.nextInt(max));
      if (!cards.contains(picked_id)) {
        cards.add(picked_id);
        i += 1;
      }
    }
    return cards;
  }

  public static void showDialog(Context context, String message) {
    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
    builder1.setMessage(message);
    builder1.setCancelable(true);

    builder1.setPositiveButton(
            "OK",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });
    AlertDialog alert = builder1.create();
    alert.show();
  }
}

