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
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class TimeSpreadActivity
  extends Activity
{
  static final int DIALOG_MEANING = 0;
  static final int MAX_CARDS = 78;
  static final int future = 2;
  static final int hiddencard_future = 3;
  static final int hiddencard_past = 1;
  static final int hiddencard_present = 2;
  static final int past = 0;
  static final int present = 1;
  private TypedArray cards;
  private String currentMeaning = "";
  private boolean futureHidden = true;
  private TypedArray hiddencards;
  private TypedArray meanings;
  private boolean pastHidden = true;
  private List<Integer> picks = new ArrayList();
  private boolean presentHidden = true;
  private Vibrator vibrator;
  
  private void hideSpread()
  {
    Drawable localDrawable = this.hiddencards.getDrawable(1);
    ((ImageView)findViewById(2131165209)).setImageDrawable(localDrawable);
    this.pastHidden = true;
    localDrawable = this.hiddencards.getDrawable(2);
    ((ImageView)findViewById(2131165210)).setImageDrawable(localDrawable);
    this.presentHidden = true;
    localDrawable = this.hiddencards.getDrawable(3);
    ((ImageView)findViewById(2131165211)).setImageDrawable(localDrawable);
    this.futureHidden = true;
    this.currentMeaning = "";
    showMeaning(this.currentMeaning);
  }
  
  private boolean showCardOnPosition(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Drawable localDrawable = this.cards.getDrawable(((Integer)this.picks.get(paramInt1)).intValue());
    String str = this.meanings.getString(((Integer)this.picks.get(paramInt1)).intValue());
    this.currentMeaning = str;
    showMeaning(str);
    if (paramBoolean)
    {
      ((ImageView)findViewById(paramInt2)).setImageDrawable(localDrawable);
      return false;
    }
    doShowCard(localDrawable, str);
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
    showCurrentCard(0, 2131165209, 1, this.pastHidden);
    showCurrentCard(1, 2131165210, 2, this.presentHidden);
    showCurrentCard(2, 2131165211, 3, this.futureHidden);
    showMeaning(this.currentMeaning);
  }
  
  private void showMeaning(String paramString)
  {
    ((TextView)findViewById(2131165212)).setText(paramString);
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
  
  private void toastMeaning(String paramString)
  {
    paramString = Toast.makeText(this, paramString, 0);
    paramString.setGravity(17, 0, 0);
    paramString.setDuration(30);
    paramString.show();
  }
  
  public void doNewSpread(View paramView)
  {
    this.picks = Common.pickCards(Integer.valueOf(3), Integer.valueOf(78));
    hideSpread();
  }
  
  public void doPreviousLayout(View paramView)
  {
    switchLayout1();
    showCurrentSpread();
  }
  
  public void doShowCard(Drawable paramDrawable, String paramString)
  {
    switchLayout2();
    ((ImageView)findViewById(2131165188)).setImageDrawable(paramDrawable);
  }
  
  public void doShowFuture(View paramView)
  {
    this.futureHidden = showCardOnPosition(2, 2131165211, this.futureHidden);
  }
  
  public void doShowMeaning(View paramView)
  {
    paramView = new Bundle();
    paramView.putString("meaning", this.currentMeaning);
    showDialog(0, paramView);
  }
  
  public void doShowPast(View paramView)
  {
    this.pastHidden = showCardOnPosition(0, 2131165209, this.pastHidden);
  }
  
  public void doShowPresent(View paramView)
  {
    this.presentHidden = showCardOnPosition(1, 2131165210, this.presentHidden);
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
    setContentView(2130903045);
    switchLayout1();
    findViewById(2131165188).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TimeSpreadActivity.this.doPreviousLayout(paramAnonymousView);
      }
    });
    findViewById(2131165188).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        TimeSpreadActivity.this.vibrator.vibrate(50L);
        TimeSpreadActivity.this.doShowMeaning(paramAnonymousView);
        return false;
      }
    });
    findViewById(2131165209).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TimeSpreadActivity.this.doShowPast(paramAnonymousView);
      }
    });
    findViewById(2131165209).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!TimeSpreadActivity.this.pastHidden)
        {
          TimeSpreadActivity.this.vibrator.vibrate(50L);
          TimeSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165210).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TimeSpreadActivity.this.doShowPresent(paramAnonymousView);
      }
    });
    findViewById(2131165210).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!TimeSpreadActivity.this.presentHidden)
        {
          TimeSpreadActivity.this.vibrator.vibrate(50L);
          TimeSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    findViewById(2131165211).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TimeSpreadActivity.this.doShowFuture(paramAnonymousView);
      }
    });
    findViewById(2131165211).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        if (!TimeSpreadActivity.this.futureHidden)
        {
          TimeSpreadActivity.this.vibrator.vibrate(50L);
          TimeSpreadActivity.this.doShowMeaning(paramAnonymousView);
        }
        return false;
      }
    });
    hideSpread();
    if (this.picks.size() == 0) {
      this.picks = Common.pickCards(Integer.valueOf(3), Integer.valueOf(78));
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
    switch (paramInt)
    {
    default: 
      return;
    }
    ((AlertDialog)paramDialog).setMessage(this.currentMeaning);
  }
}


/* Location:              R:\source\java_android\DruidCraft\classes-dex2jar.jar!\nl\uilennest\druid\tarot\druidcraft\TimeSpreadActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */