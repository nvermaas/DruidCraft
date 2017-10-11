package net.uilennest.druidcraft;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DruidCraftActivity extends Activity
{
  static final int DIALOG_ABOUT = 1;
  static final int DIALOG_MEANING = 0;
  static final int MAX_CARDS = 78;
  static final int MAX_CARDS_GREAT_ARCANA = 22;
  private TypedArray cards;
  private String currentMeaning;
  private TypedArray meanings;
  private Vibrator vibrator;
  
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
  
  public void doCardGrid(View paramView)
  {
    startActivity(new Intent(this, CardGridActivity.class));
  }
  
  public void doChangeCard(View paramView)
  {
    paramView = Common.pickCard(Integer.valueOf(22));
    this.currentMeaning = this.meanings.getString(paramView.intValue());
    paramView = this.cards.getDrawable(paramView.intValue());
    ((ImageView)findViewById(2131165207)).setImageDrawable(paramView);
  }
  
  public void doCircleSpread(View paramView)
  {
    startActivity(new Intent(this, CircleSpreadActivity.class));
  }
  
  public void doGallery(View paramView)
  {
    startActivity(new Intent(this, GalleryActivity.class));
  }
  
  public void doPreviousLayout(View paramView)
  {
    switchLayout1();
  }
  
  public void doPreviousScreen(View paramView)
  {
    setContentView(2130903044);
  }
  
  public void doShowAbout(View paramView)
  {
    showDialog(1);
  }
  
  public void doShowCard(Drawable paramDrawable, String paramString)
  {
    switchLayout2();
    ((ImageView)findViewById(2131165188)).setImageDrawable(paramDrawable);
  }
  
  public void doShowMeaning(View paramView)
  {
    paramView = new Bundle();
    paramView.putString("meaning", this.currentMeaning);
    showDialog(0, paramView);
  }
  
  public void doSingleCard(View paramView)
  {
    Object localObject = Common.pickCard(Integer.valueOf(78));
    paramView = this.cards.getDrawable(((Integer)localObject).intValue());
    localObject = this.meanings.getString(((Integer)localObject).intValue());
    this.currentMeaning = ((String)localObject);
    doShowCard(paramView, (String)localObject);
  }
  
  public void doTimeSpread(View paramView)
  {
    startActivity(new Intent(this, TimeSpreadActivity.class));
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
    this.cards = getResources().obtainTypedArray(2130968576);
    this.meanings = getResources().obtainTypedArray(2130968578);
    this.currentMeaning = this.meanings.getString(0);
    this.vibrator = ((Vibrator)getSystemService("vibrator"));
    setContentView(2130903044);
    switchLayout1();
    findViewById(2131165188).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DruidCraftActivity.this.doPreviousLayout(paramAnonymousView);
      }
    });
    findViewById(2131165188).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        DruidCraftActivity.this.vibrator.vibrate(50L);
        DruidCraftActivity.this.doShowMeaning(paramAnonymousView);
        return false;
      }
    });
    findViewById(2131165207).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DruidCraftActivity.this.doChangeCard(paramAnonymousView);
      }
    });
    findViewById(2131165207).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        DruidCraftActivity.this.doShowMeaning(paramAnonymousView);
        DruidCraftActivity.this.vibrator.vibrate(50L);
        return false;
      }
    });
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      localObject = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject).setMessage(this.currentMeaning).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      });
      return ((AlertDialog.Builder)localObject).create();
    }
    Object localObject = getResources().getString(2131034132);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage((CharSequence)localObject).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    return localBuilder.create();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131099648, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    showDialog(1);
    return true;
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
