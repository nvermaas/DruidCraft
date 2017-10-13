package net.uilennest.druidcraft;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


public class GalleryImageAdapter extends BaseAdapter
{
  private Context mContext;
  public TypedArray mCards;


  public GalleryImageAdapter(Context context, TypedArray cards)
  {
    mContext = context;
    mCards = cards;
  }

  public int getCount() {

    //return mImageIds.length;
    return mCards.length();
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }


  // Override this method according to your need
  public View getView(int index, View view, ViewGroup viewGroup)
  {
    // TODO Auto-generated method stub
    ImageView i = new ImageView(mContext);

    i.setImageDrawable(mCards.getDrawable(Integer.valueOf(index)));
    i.setLayoutParams(new Gallery.LayoutParams(400, 600));
    i.setScaleType(ImageView.ScaleType.FIT_XY);

    return i;
  }


}