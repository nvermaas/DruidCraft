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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimeSpreadActivity
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

  private List<Integer> picks = new ArrayList();

  private Vibrator vibrator;


  private void hideSpread() {
    Drawable card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_past));
    ((ImageView) findViewById(R.id.positionPast)).setImageDrawable(card);
    this.pastHidden = true;

    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_present));
    ((ImageView) findViewById(R.id.positionPresent)).setImageDrawable(card);
    this.presentHidden = true;

    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_future));
    ((ImageView) findViewById(R.id.positionFuture)).setImageDrawable(card);
    this.futureHidden = true;
  }

  private boolean showCardOnPosition(int pick, int card_id, boolean hidden) {
    Drawable card = this.cards.getDrawable((this.picks.get(pick)));
    this.currentMeaning = this.meanings.getString(((Integer) this.picks.get(pick)).intValue());
    doShowMeaningLabel(this.currentMeaning);
    if (hidden) {
      ((ImageView) findViewById(card_id)).setImageDrawable(card);
      return false;
    }
    doShowCard(card);


    return hidden;
  }

  private void showCurrentCard(int kind, int position_id, int hiddencard, boolean hidden) {
    Drawable card;
    if (hidden) {
      card = this.hiddencards.getDrawable(hiddencard);
    } else {
      card = this.cards.getDrawable((this.picks.get(kind)).intValue());
    }
    ((ImageView) findViewById(position_id)).setImageDrawable(card);
    doShowMeaningLabel(this.currentMeaning);

  }

  private void showCurrentSpread() {
    showCurrentCard(past, R.id.positionPast, hiddencard_past, this.pastHidden);
    showCurrentCard(present, R.id.positionPresent, hiddencard_present, this.presentHidden);
    showCurrentCard(future, R.id.positionFuture, hiddencard_future, this.futureHidden);
  }

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

  public void doNewSpread() {
    this.picks = Common.pickCards(NR_PICKS, MAX_CARDS);
    hideSpread();
  }

  public void doPreviousLayout() {
    switchToLayout1();
    showCurrentSpread();
  }

  public void doShowCard(Drawable card) {
    switchToLayout2();
    ((ImageView) findViewById(R.id.card)).setImageDrawable(card);
  }

  public void doShowMeaningLabel(String message) {
    ((TextView)findViewById(R.id.textMeaning)).setText(message);
  }

  public void doShowMeaning(View view) {
    Common.showDialog(TimeSpreadActivity.this, this.currentMeaning);
  }

  public void doShowPast(View paramView) {
    this.pastHidden = showCardOnPosition(past, R.id.positionPast, this.pastHidden);
  }
  public void doShowPresent(View paramView) {
    this.presentHidden = showCardOnPosition(present, R.id.positionPresent, this.presentHidden);
  }
  public void doShowFuture(View paramView) {
    this.futureHidden = showCardOnPosition(future, R.id.positionFuture, this.futureHidden);
  }

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          finish();
          return true;
        case R.id.navigation_dashboard:
          // new spread
          doNewSpread();

          return true;

      }
      return false;

    }

  };
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

    setContentView(R.layout.timespread);
    this.cards = getResources().obtainTypedArray(R.array.cards);
    this.hiddencards = getResources().obtainTypedArray(R.array.hiddencards);
    this.meanings = getResources().obtainTypedArray(R.array.meanings);
    this.currentMeaning = this.meanings.getString(0);
    this.vibrator = ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));

    switchToLayout1();

    findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        doPreviousLayout();
      }
    });
    findViewById(R.id.card).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        //CircleSpreadActivity.this.vibrator.vibrate(50L);
        doShowMeaning(view);
        return false;
      }
    });
    findViewById(R.id.positionPast).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
       doShowPast(view);
      }
    });
    findViewById(R.id.positionPast).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!pastHidden) {
          doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionPresent).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        doShowPresent(view);
      }
    });
    findViewById(R.id.positionPresent).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!presentHidden) {
         doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionFuture).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        doShowFuture(view);
      }
    });
    findViewById(R.id.positionFuture).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!futureHidden) {
          doShowMeaning(view);
        }
        return false;
      }
    });

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_timespread);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    // start with a hidden spread
    hideSpread();
    if (this.picks.size() == 0) {
      this.picks = Common.pickCards(NR_PICKS, MAX_CARDS);
    }
  }
}
