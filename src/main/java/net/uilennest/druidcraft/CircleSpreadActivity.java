package net.uilennest.druidcraft;

import android.app.Activity;

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
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CircleSpreadActivity
  extends Activity {
  static final int MAX_CARDS = 78;
  static final int NR_PICKS = 7;
  static final int ancestors = 0;
  static final int hiddencard_ancestors = 4;
  static final int hiddencard_inspiration = 9;
  static final int hiddencard_journey = 8;
  static final int hiddencard_place = 7;
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


  private void hideSpread() {
    Drawable card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_ancestors));
    ((ImageView) findViewById(R.id.positionAncestors)).setImageDrawable(card);
    this.ancestorsHidden = true;
    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_time));
    ((ImageView) findViewById(R.id.positionTime)).setImageDrawable(card);
    this.timeHidden = true;
    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_tribe));
    ((ImageView) findViewById(R.id.positionTribe)).setImageDrawable(card);
    this.tribeHidden = true;
    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_place));
    ((ImageView) findViewById(R.id.positionPlace)).setImageDrawable(card);
    this.placeHidden = true;
    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_journey));
    ((ImageView) findViewById(R.id.positionJourney)).setImageDrawable(card);
    this.journeyHidden = true;
    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_inspiration));
    ((ImageView) findViewById(R.id.positionInspiration)).setImageDrawable(card);
    this.inspirationHidden = true;
    card = this.hiddencards.getDrawable(Integer.valueOf(hiddencard_self));
    ((ImageView) findViewById(R.id.positionSelf)).setImageDrawable(card);
    this.selfHidden = true;
  }

  private boolean showCardOnPosition(int pick, int card_id, boolean hidden) {
    Drawable card = this.cards.getDrawable((this.picks.get(pick)));
    this.currentMeaning = this.meanings.getString(((Integer) this.picks.get(pick)).intValue());
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
  }

  private void showCurrentSpread() {
    showCurrentCard(ancestors, R.id.positionAncestors, hiddencard_ancestors, this.ancestorsHidden);
    showCurrentCard(time, R.id.positionTime, hiddencard_time, this.timeHidden);
    showCurrentCard(tribe, R.id.positionTribe, hiddencard_tribe, this.tribeHidden);
    showCurrentCard(place, R.id.positionPlace, hiddencard_place, this.placeHidden);
    showCurrentCard(journey, R.id.positionJourney, hiddencard_journey, this.journeyHidden);
    showCurrentCard(inspiration, R.id.positionInspiration, hiddencard_inspiration, this.inspirationHidden);
    showCurrentCard(self, R.id.positionSelf, hiddencard_self, this.selfHidden);
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

  public void doInfo() {
    doShowCard(this.hiddencards.getDrawable(Integer.valueOf(info_circlespead)));
  }

  public void doNewSpread() {
    this.picks = Common.pickCards(NR_PICKS, MAX_CARDS);
    hideSpread();
  }

  public void doPreviousLayout() {
    switchToLayout1();
    showCurrentSpread();
  }

  public void doShowAncestors(View paramView) {
    this.ancestorsHidden = showCardOnPosition(0, R.id.positionAncestors, this.ancestorsHidden);
  }

  public void doShowCard(Drawable card) {
    switchToLayout2();
    ((ImageView) findViewById(R.id.card)).setImageDrawable(card);
  }

  public void doShowMeaning(View view) {
    Common.showDialog(CircleSpreadActivity.this, this.currentMeaning);
  }

  public void doShowInspiration(View paramView) {
    this.inspirationHidden = showCardOnPosition(inspiration, R.id.positionInspiration, this.inspirationHidden);
  }

  public void doShowJourney(View paramView) {
    this.journeyHidden = showCardOnPosition(journey, R.id.positionJourney, this.journeyHidden);
  }

  public void doShowPlace(View paramView) {
    this.placeHidden = showCardOnPosition(place, R.id.positionPlace, this.placeHidden);
  }

  public void doShowSelf(View paramView) {
    this.selfHidden = showCardOnPosition(self, R.id.positionSelf, this.selfHidden);
  }

  public void doShowTime(View paramView) {
    this.timeHidden = showCardOnPosition(time, R.id.positionTime, this.timeHidden);
  }

  public void doShowTribe(View paramView) {
    this.tribeHidden = showCardOnPosition(tribe, R.id.positionTribe, this.tribeHidden);
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
    //setContentView(R.layout.circlespread);
    setContentView(R.layout.circlespread);
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
    findViewById(R.id.positionAncestors).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
       doShowAncestors(view);
      }
    });
    findViewById(R.id.positionAncestors).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!ancestorsHidden) {
          //CircleSpreadActivity.this.vibrator.vibrate(50L);
          doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionTime).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        doShowTime(view);
      }
    });
    findViewById(R.id.positionTime).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!timeHidden) {
          //CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionTribe).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        doShowTribe(view);
      }
    });
    findViewById(R.id.positionTribe).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!tribeHidden) {
          //CircleSpreadActivity.this.vibrator.vibrate(50L);
          doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionPlace).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        doShowPlace(view);
      }
    });
    findViewById(R.id.positionPlace).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!placeHidden) {
          //CircleSpreadActivity.this.vibrator.vibrate(50L);
          doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionJourney).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        CircleSpreadActivity.this.doShowJourney(view);
      }
    });
    findViewById(R.id.positionJourney).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!CircleSpreadActivity.this.journeyHidden) {
          //CircleSpreadActivity.this.vibrator.vibrate(50L);
          CircleSpreadActivity.this.doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionInspiration).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        CircleSpreadActivity.this.doShowInspiration(view);
      }
    });
    findViewById(R.id.positionInspiration).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!inspirationHidden) {
          //CircleSpreadActivity.this.vibrator.vibrate(50L);
          doShowMeaning(view);
        }
        return false;
      }
    });
    findViewById(R.id.positionSelf).setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        doShowSelf(view);
      }
    });
    findViewById(R.id.positionSelf).setOnLongClickListener(new OnLongClickListener() {
      public boolean onLongClick(View view) {
        if (!selfHidden) {
          //CircleSpreadActivity.this.vibrator.vibrate(50L);
          doShowMeaning(view);
        }
        return false;
      }
    });

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    // start with a hidden spread
    hideSpread();
    if (this.picks.size() == 0) {
      this.picks = Common.pickCards(NR_PICKS, MAX_CARDS);
    }
  }
}
