package com.ysl3000.config.adapter;

import com.ysl3000.config.data.IConfigPosition;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class ConfigPositionAdapterTest extends AbstractAdapterTest{

  @Test
  public void shouldConvertFromLocationToPosition() {

    IConfigPosition position = new ConfigPositionAdapter(this.location);

    checkEquality(this.iConfigPosition,position);


  }


  private void checkEquality(IConfigPosition location, IConfigPosition location2){
    Assert.assertEquals(location.getName(),location2.getName());
    Assert.assertEquals(location.getX(),location2.getX(),0);
    Assert.assertEquals(location.getY(),location2.getY(),0);
    Assert.assertEquals(location.getZ(),location2.getZ(),0);

  }

}
