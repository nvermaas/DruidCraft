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
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GalleryActivity
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
    setContentView(2130903043);
    this.cards = getResources().obtainTypedArray(2130968576);
    this.meanings = getResources().obtainTypedArray(2130968578);
    this.vibrator = ((Vibrator)getSystemService("vibrator"));
    switchLayout1();
    showGallery();
    findViewById(2131165188).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GalleryActivity.this.doPreviousLayout(paramAnonymousView);
      }
    });
    findViewById(2131165188).setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        GalleryActivity.this.vibrator.vibrate(50L);
        GalleryActivity.this.doShowMeaning(paramAnonymousView);
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
  
  public void showGallery()
  {
    Gallery localGallery = (Gallery)findViewById(2131165200);
    localGallery.setAdapter(new ImageAdapterGallery(this));
    localGallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = GalleryActivity.this.cards.getDrawable(paramAnonymousInt);
        GalleryActivity.this.currentMeaning = GalleryActivity.this.meanings.getString(paramAnonymousInt);
        GalleryActivity.this.doShowCard(paramAnonymousAdapterView);
      }
    });
  }
}


/* Location:              R:\source\java_android\DruidCraft\classes-dex2jar.jar!\nl\uilennest\druid\tarot\druidcraft\GalleryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */