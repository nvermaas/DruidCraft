package net.uilennest.druidcraft;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardGalleryActivity
  extends Activity {
  static final int NR_PICKS = 3;
  static final int MAX_CARDS = 78;
  static final int future = 2;
  static final int hiddencard_future = 3;
  static final int hiddencard_past = 1;
  static final int hiddencard_present = 2;
  static final int past = 0;
  static final int present = 1;
  private boolean pastHidden = true;
  private boolean presentHidden = true;
  private boolean futureHidden = true;
  private TypedArray cards;
  private String currentMeaning = "";
  private TypedArray hiddencards;
  private TypedArray meanings;
  private ImageView selectedImage;
  private Integer[] theCards;

  private List<Integer> picks = new ArrayList();

  private Vibrator vibrator;

  // main screen
  private void switchToLayout1() {
    LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
    LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
    layout1.setVisibility(View.VISIBLE);
    layout2.setVisibility(View.GONE);
  }

  // card gui
  private void switchToLayout2() {
    LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
    LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
    layout1.setVisibility(View.GONE);
    layout2.setVisibility(View.VISIBLE);
  }


  public void doShowMeaningLabel(String message) {
    ((TextView)findViewById(R.id.textMeaning)).setText(message);
  }

  private void doShowMeaning() {
    int pos = this.currentMeaning.indexOf("\n");
    String title = this.currentMeaning.substring(0,pos).trim();
    String description = this.currentMeaning.substring(pos).trim();
    Common.doShowMeaning(CardGalleryActivity.this,this.currentMeaning);
  }

  public void showGallery()
  {
    Gallery gallery = (Gallery)findViewById(R.id.myGallery);
    selectedImage=(ImageView)findViewById(R.id.selectedImage);
    //selectedImage.setImageDrawable(this.cards.getDrawable(Integer.valueOf(0)));

    int pos = 5;
    this.currentMeaning =this.meanings.getString(Integer.valueOf(pos));

    gallery.setSpacing(1);
    final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(this, this.cards);
    gallery.setAdapter(galleryImageAdapter);

     gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // show the selected Image
        selectedImage.setImageDrawable(galleryImageAdapter.mCards.getDrawable(Integer.valueOf(position)));

      }
    });
  }

  // catch the back pressed to switch to main layout
  public void onBackPressed() {
    if (((LinearLayout) findViewById(R.id.layout2)).getVisibility() == View.VISIBLE) {
      switchToLayout1();
      return;
    }
    super.onBackPressed();
  }

  protected void onCreate(Bundle myBundle) {
    super.onCreate(myBundle);

    setContentView(R.layout.gallery);
    this.cards = getResources().obtainTypedArray(R.array.cards);
    this.meanings = getResources().obtainTypedArray(R.array.meanings);
    this.currentMeaning = this.meanings.getString(0);
    this.vibrator = ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));

    switchToLayout1();
    showGallery();

    findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        switchToLayout2();
      }
    });
    findViewById(R.id.card).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        //CircleSpreadActivity.this.vibrator.vibrate(50L);
        doShowMeaning();
        return false;
      }
    });
    findViewById(R.id.selectedImage).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        //CircleSpreadActivity.this.vibrator.vibrate(50L);

        doShowMeaning();
        return false;
      }
    });
  }
}
