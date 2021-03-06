package com.avaje.tests.query.orderby;

import com.avaje.ebean.BaseTestCase;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;
import com.avaje.tests.model.basic.Order;
import com.avaje.tests.model.basic.OrderDetail;
import com.avaje.tests.model.basic.ResetBasicData;
import org.avaje.ebeantest.LoggedSqlCollector;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestOrderByClear extends BaseTestCase {

  @Test
  public void test() {

    ResetBasicData.reset();

    Query<Order> query = Ebean.find(Order.class)
        .orderBy().asc("orderDate");


    OrderBy<Order> orderBy = query.orderBy();
    assertTrue(orderBy.containsProperty("orderDate"));

    orderBy.clear();
    Assert.assertFalse(orderBy.containsProperty("orderDate"));

    orderBy.asc("shipDate");
    assertTrue(orderBy.containsProperty("shipDate"));

    query.findList();
    String sql = query.getGeneratedSql();

    assertTrue(sql.contains("order by t0.ship_date"));

  }

}
