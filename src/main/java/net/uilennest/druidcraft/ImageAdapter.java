package net.uilennest.druidcraft;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter
  extends BaseAdapter
{
  private Context mContext;
  private Integer[] mThumbIds = { Integer.valueOf(2130837504), Integer.valueOf(2130837505), Integer.valueOf(2130837506), Integer.valueOf(2130837507), Integer.valueOf(2130837508), Integer.valueOf(2130837509), Integer.valueOf(2130837510), Integer.valueOf(2130837511), Integer.valueOf(2130837512), Integer.valueOf(2130837513), Integer.valueOf(2130837514), Integer.valueOf(2130837515), Integer.valueOf(2130837516), Integer.valueOf(2130837517), Integer.valueOf(2130837518), Integer.valueOf(2130837519), Integer.valueOf(2130837520), Integer.valueOf(2130837521), Integer.valueOf(2130837522), Integer.valueOf(2130837523), Integer.valueOf(2130837524), Integer.valueOf(2130837525), Integer.valueOf(2130837526), Integer.valueOf(2130837527), Integer.valueOf(2130837528), Integer.valueOf(2130837529), Integer.valueOf(2130837530), Integer.valueOf(2130837531), Integer.valueOf(2130837532), Integer.valueOf(2130837533), Integer.valueOf(2130837534), Integer.valueOf(2130837535), Integer.valueOf(2130837536), Integer.valueOf(2130837537), Integer.valueOf(2130837538), Integer.valueOf(2130837539), Integer.valueOf(2130837540), Integer.valueOf(2130837541), Integer.valueOf(2130837542), Integer.valueOf(2130837543), Integer.valueOf(2130837544), Integer.valueOf(2130837545), Integer.valueOf(2130837546), Integer.valueOf(2130837547), Integer.valueOf(2130837548), Integer.valueOf(2130837549), Integer.valueOf(2130837550), Integer.valueOf(2130837551), Integer.valueOf(2130837552), Integer.valueOf(2130837553), Integer.valueOf(2130837554), Integer.valueOf(2130837555), Integer.valueOf(2130837556), Integer.valueOf(2130837557), Integer.valueOf(2130837558), Integer.valueOf(2130837559), Integer.valueOf(2130837560), Integer.valueOf(2130837561), Integer.valueOf(2130837562), Integer.valueOf(2130837563), Integer.valueOf(2130837564), Integer.valueOf(2130837565), Integer.valueOf(2130837566), Integer.valueOf(2130837567), Integer.valueOf(2130837568), Integer.valueOf(2130837569), Integer.valueOf(2130837570), Integer.valueOf(2130837571), Integer.valueOf(2130837572), Integer.valueOf(2130837573), Integer.valueOf(2130837574), Integer.valueOf(2130837575), Integer.valueOf(2130837576), Integer.valueOf(2130837577), Integer.valueOf(2130837578), Integer.valueOf(2130837579), Integer.valueOf(2130837580), Integer.valueOf(2130837581) };
  
  public ImageAdapter(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public int getCount()
  {
    return this.mThumbIds.length;
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = new ImageView(this.mContext);
      paramView.setLayoutParams(new AbsListView.LayoutParams(100, 160));
      paramView.setScaleType(ImageView.ScaleType.FIT_XY);
      paramView.setPadding(8, 8, 8, 8);
    }
    for (;;)
    {
      paramView.setImageResource(this.mThumbIds[paramInt].intValue());
      return paramView;
      paramView = (ImageView)paramView;
    }
  }
}


/* Location:              R:\source\java_android\DruidCraft\classes-dex2jar.jar!\nl\uilennest\druid\tarot\druidcraft\ImageAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */