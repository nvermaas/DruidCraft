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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CardGridActivity
  extends Activity
{
  static final int DIALOG_MEANING = 0;
  private TypedArray cards;
  private String currentMeaning = "";
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
  
  public void doPreviousLayout(View paramView)
  {
    switchLayout1();
  }
  
  public void doShowCard(Drawable paramDrawable)
  {
    switchLayout2();
    ((ImageView)findViewById(2131165188)).setImageDrawable(paramDrawable);
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
    setContentView(2130903040);
    this.cards = getResources().obtainTypedArray(2130968576);
    this.meanings = getResources().obtainTypedArray(2130968578);
    this.vibrator = ((Vibrator)getSystemService("vibrator"));
    switchLayout1();
    showGrid();
    findViewById(2131165188).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CardGridActivity.this.doPreviousLayout(paramAnonymousView);
      }
    });
    findViewById(2131165188).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        CardGridActivity.this.vibrator.vibrate(50L);
        CardGridActivity.this.doShowMeaning(paramAnonymousView);
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
  
  public void showGrid()
  {
    GridView localGridView = (GridView)findViewById(2131165186);
    localGridView.setAdapter(new ImageAdapter(this));
    localGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = CardGridActivity.this.cards.getDrawable(paramAnonymousInt);
        CardGridActivity.this.currentMeaning = CardGridActivity.this.meanings.getString(paramAnonymousInt);
        CardGridActivity.this.doShowCard(paramAnonymousAdapterView);
      }
    });
  }
}
