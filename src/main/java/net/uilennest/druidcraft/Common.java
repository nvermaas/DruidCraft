package net.uilennest.druidcraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.view.View;


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

  public static void showDialog(Context context, String title, String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setPositiveButton(
            "OK",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });
    AlertDialog alert = builder.create();
    alert.show();
  }

  // ============================================================================
  public static void doShowMeaning(Context context, String meaning) {
    int pos = meaning.indexOf("\n");
    String title = meaning.substring(0,pos).trim();
    String description = meaning.substring(pos).trim();

    showDialog(context,title, description);
    //showTopMessage(this.currentMeaning);
  }
}

