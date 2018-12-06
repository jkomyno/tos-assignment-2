package it.unipd.tos.business;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RestaurantBillImplTest {
    @Test
    public void test() {
        RestaurantBillImpl bill = new RestaurantBillImpl();
        assertEquals(bill.getOrderPrice(null), 0, 0.001);
    }
}
