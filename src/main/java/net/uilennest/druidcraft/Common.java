package net.uilennest.druidcraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Common
{
  public static List<Integer> picks = new ArrayList();
  
  public static Integer pickCard(Integer paramInteger)
  {
    return Integer.valueOf(new Random().nextInt(paramInteger.intValue()));
  }
  
  public static List<Integer> pickCards(Integer paramInteger1, Integer paramInteger2)
  {
    ArrayList localArrayList = new ArrayList();
    Random localRandom = new Random();
    int i = 0;
    if (i >= paramInteger1.intValue()) {
      return localArrayList;
    }
    for (Integer localInteger = Integer.valueOf(localRandom.nextInt(paramInteger2.intValue()));; localInteger = Integer.valueOf(localRandom.nextInt(paramInteger2.intValue()))) {
      if (!localArrayList.contains(localInteger))
      {
        localArrayList.add(localInteger);
        i += 1;
        break;
      }
    }
  }
}
