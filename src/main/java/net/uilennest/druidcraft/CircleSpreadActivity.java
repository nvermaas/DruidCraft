package net.uilennest.druidcraft;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class CircleSpreadActivity
  extends Activity
{
  static final int DIALOG_MEANING = 0;
  static final int MAX_CARDS = 78;
  static final int NR_PICKS = 7;
  static final int ancestors = 0;
  static final int hiddencard_ancestors = 4;
  static final int hiddencard_future = 3;
  static final int hiddencard_inspiration = 9;
  static final int hiddencard_journey = 8;
  static final int hiddencard_past = 1;
  static final int hiddencard_place = 7;
  static final int hiddencard_present = 2;
  static final int hiddencard_self = 10;
  static final int hiddencard_time = 5;
  static final int hiddencard_tribe = 6;
  static final int info_circlespead = 11;
  static final int inspiration = 5;
  static final int journey = 4;
  static final int place = 3;
  static final int self = 6;
  static final int time = 1;
  static final int tribe = 2;
  private boolean ancestorsHidden = true;
  private TypedArray cards;
  private String currentMeaning = "";
  private TypedArray hiddencards;
  private boolean inspirationHidden = true;
  private boolean journeyHidden = true;
  private TypedArray meanings;
  private List<Integer> picks = new ArrayList();
  private boolean placeHidden = true;
  private boolean selfHidden = true;
  private boolean timeHidden = true;
  private boolean tribeHidden = true;
  private Vibrator vibrator;
  
  private void hideSpread()
  {
    Drawable localDrawable = this.hiddencards.getDrawable(4);
    ((ImageView)findViewById(2131165190)).setImageDrawable(localDrawable);
    this.ancestorsHidden = true;
    localDrawable = this.hiddencards.getDrawable(5);
    ((ImageView)findViewById(2131165196)).setImageDrawable(localDrawable);
    this.timeHidden = true;
    localDrawable = this.hiddencards.getDrawable(6);
    ((ImageView)findViewById(2131165197)).setImageDrawable(localDrawable);
    this.tribeHidden = true;
    localDrawable = this.hiddencards.getDrawable(7);
    ((ImageView)findViewById(2131165191)).setImageDrawable(localDrawable);
    this.placeHidden = true;
    localDrawable = this.hiddencards.getDrawable(8);
    ((ImageView)findViewById(2131165195)).setImageDrawable(localDrawable);
    this.journeyHidden = true;
    localDrawable = this.hiddencards.getDrawable(9);
    ((ImageView)findViewById(2131165193)).setImageDrawable(localDrawable);
    this.inspirationHidden = true;
    localDrawable = this.hiddencards.getDrawable(10);
    ((ImageView)findViewById(2131165194)).setImageDrawable(localDrawable);
    this.selfHidden = true;
  }
  
  private boolean showCardOnPosition(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Drawable localDrawable = this.cards.getDrawable(((Integer)this.picks.get(paramInt1)).intValue());
    this.currentMeaning = this.meanings.getString(((Integer)this.picks.get(paramInt1)).intValue());
    if (paramBoolean)
    {
      ((ImageView)findViewById(paramInt2)).setImageDrawable(localDrawable);
      return false;
    }
    doShowCard(localDrawable);
    return paramBoolean;
  }
  
  private void showCurrentCard(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Drawable localDrawable = this.hiddencards.getDrawable(paramInt3);; localDrawable = this.cards.getDrawable(((Integer)this.picks.get(paramInt1)).intValue()))
    {
      ((ImageView)findViewById(paramInt2)).setImageDrawable(localDrawable);
      return;
    }
  }
  
  private void showCurrentSpread()
  {
    showCurrentCard(0, 2131165190, 4, this.ancestorsHidden);
    showCurrentCard(1, 2131165196, 5, this.timeHidden);
    showCurrentCard(2, 2131165197, 6, this.tribeHidden);
    showCurrentCard(3, 2131165191, 7, this.placeHidden);
    showCurrentCard(4, 2131165195, 8, this.journeyHidden);
    showCurrentCard(5, 2131165193, 9, this.inspirationHidden);
    showCurrentCard(6, 2131165194, 10, this.selfHidden);
  }
  
  private void switchLayout1()
  {
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131165185);
    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131165187);
    localLinearLayout1.setVisibility(0);
    localLinearLayout2.setVisibility(8);
  }
  
  private void switchLayout2()
  {
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131165185);
    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131165187);
    localLinearLayout1.setVisibility(8);
    localLinearLayout2.setVisibility(0);
  }
  
  public void doInfo(View paramView)
  {
    doShowCard(this.hiddencards.getDrawable(11));
  }
  
  public void doNewSpread(View paramView)
  {
    this.picks = Common.pickCards(Integer.valueOf(7), Integer.valueOf(78));
    hideSpread();
  }
  
  public void doPreviousLayout(View paramView)
  {
    switchLayout1();
    showCurrentSpread();
  }
  
  public void doShowAncestors(View paramView)
  {
    this.ancestorsHidden = showCardOnPosition(0, 2131165190, this.ancestorsHidden);
  }
  
  public void doShowCard(Drawable paramDrawable)
  {
    switchLayout2();
    ((ImageView)findViewById(2131165188)).setImageDrawable(paramDrawable);
  }
  
  public void doShowInspiration(View paramView)
  {
    this.inspirationHidden = showCardOnPosition(5, 2131165193, this.inspirationHidden);
  }
  
  public void doShowJourney(View paramView)
  {
    this.journeyHidden = showCardOnPosition(4, 2131165195, this.journeyHidden);
  }
  
  public void doShowMeaning(View paramView)
  {
    paramView = new Bundle();
    paramView.putString("meaning", this.currentMeaning);
    showDialog(0, paramView);
  }
  
  public void doShowPlace(View paramView)
  {
    this.placeHidden = showCardOnPosition(3, 2131165191, this.placeHidden);
  }
  
  public void doShowSelf(View paramView)
  {
    this.selfHidden = showCardOnPosition(6, 2131165194, this.selfHidden);
  }
  
  public void doShowTime(View paramView)
  {
    this.timeHidden = showCardOnPosition(1, 2131165196, this.timeHidden);
  }
  
  public void doShowTribe(View paramView)
  {
    this.tribeHidden = showCardOnPosition(2, 2131165197, this.tribeHidden);
  }
  
  public void onBackPressed()
  {
    if (((LinearLayout)findViewById(2131165187)).getVisibility() == 0)
    {
      switchLayout1();
      return;
    }
    super.onBackPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.meanings = getResources().obtainTypedArray(2130968578);
    this.cards = getResources().obtainTypedArray(2130968576);
    this.hiddencards = getResources().obtainTypedArray(2130968577);
    this.vibrator = ((Vibrator)getSystemService("vibrator"));
    setContentView(2130903042);
    switchLayout1();
    findViewById(2131165188).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doPreviousLayout(paramAnonymousView);
      }
    });
    findViewById(2131165188).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.vibrator.vibrate(50L);
        CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        return false;
      }
    });
    findViewById(2131165190).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doShowAncestors(paramAnonymousView);
      }
    });
    findViewById(2131165190).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!CircleSpreadActivity.this.ancestorsHidden)
        {
          CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165196).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doShowTime(paramAnonymousView);
      }
    });
    findViewById(2131165196).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!CircleSpreadActivity.this.timeHidden)
        {
          CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165197).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doShowTribe(paramAnonymousView);
      }
    });
    findViewById(2131165197).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!CircleSpreadActivity.this.tribeHidden)
        {
          CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165191).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doShowPlace(paramAnonymousView);
      }
    });
    findViewById(2131165191).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!CircleSpreadActivity.this.placeHidden)
        {
          CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165195).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doShowJourney(paramAnonymousView);
      }
    });
    findViewById(2131165195).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!CircleSpreadActivity.this.journeyHidden)
        {
          CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165193).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doShowInspiration(paramAnonymousView);
      }
    });
    findViewById(2131165193).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!CircleSpreadActivity.this.inspirationHidden)
        {
          CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165194).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CircleSpreadActivity.this.doShowSelf(paramAnonymousView);
      }
    });
    findViewById(2131165194).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!CircleSpreadActivity.this.selfHidden)
        {
          CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    hideSpread();
    if (this.picks.size() == 0) {
      this.picks = Common.pickCards(Integer.valueOf(7), Integer.valueOf(78));
    }
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(this.currentMeaning).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    return localBuilder.create();
  }
  
  protected void onPrepareDialog(int paramInt, Dialog paramDialog, Bundle paramBundle)
  {
    paramBundle.getString("meaning");
    switch (paramInt)
    {
    default: 
      return;
    }
    ((AlertDialog)paramDialog).setMessage(this.currentMeaning);
  }
}


/* Location:              R:\source\java_android\DruidCraft\classes-dex2jar.jar!\nl\uilennest\druid\tarot\druidcraft\CircleSpreadActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */