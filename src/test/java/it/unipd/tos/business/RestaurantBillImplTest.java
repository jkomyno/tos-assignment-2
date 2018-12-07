package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class RestaurantBillImplTest {

    /**
     * itemsOrdered is the base data instance where RestaurantBillImpl.getOrderPrice
     * tests will be built upon
     */
    private List<MenuItem> getItemsOrdered() {
        return Stream.of(
                new MenuItem(ItemType.PIZZE, "Margherita", 4),
                new MenuItem(ItemType.PIZZE, "Patatosa", 5.50),
                new MenuItem(ItemType.PIZZE, "Pomodorini", 7),
                new MenuItem(ItemType.PIZZE, "Margherita", 4),
                new MenuItem(ItemType.PRIMI, "Carbonara", 9),
                new MenuItem(ItemType.PIZZE, "Bianca", 3), // minPrice
                new MenuItem(ItemType.PRIMI, "Risotto ai funghi", 11.50),
                new MenuItem(ItemType.PIZZE, "Patatosa", 5.50),
                new MenuItem(ItemType.PIZZE, "Pomodorini", 7),
                new MenuItem(ItemType.PIZZE, "Bianca", 3), // minPrice
                new MenuItem(ItemType.PRIMI, "Carbonara", 9),
                new MenuItem(ItemType.PIZZE, "Pomodorini", 7)
        ).collect(Collectors.toList());
    }

    /**
     * Dato un elenco di ordinazioni (Pizze e Primi piatti) calcolare il totale (somma del prezzo dei
     * prodotti ordinati)
     */
    @Test
    public void testGetOrderPriceBaseCase() {
        RestaurantBillImpl bill = new RestaurantBillImpl();
        List<MenuItem> itemsOrdered = getItemsOrdered();
        assertEquals( 75.5, bill.getOrderPrice(itemsOrdered), 0.001);
    }

    /**
     * Se l’importo totale delle ordinazioni (Pizze e Primi) supera i 100 euro viene fatto un 5% di
     * sconto
     */
    @Test
    public void testGetOrderPrice5PercentDiscountIfMoreThan100Euros() {
        RestaurantBillImpl bill = new RestaurantBillImpl();
        List<MenuItem> itemsOrdered = getItemsOrdered();
        itemsOrdered.add(new MenuItem(ItemType.PIZZE, "Oro e argento", 100.00));
        double rawPrice = 175.5;
        double discount = rawPrice * 5 / 100;
        assertEquals(bill.getOrderPrice(itemsOrdered), rawPrice - discount, 0.001);
    }

    /**
     * Non è possibile avere un’ordinazione con più di 20 elementi (se accade prevedere un messaggio
     * d’errore
     */
    @Test(expected = RestaurantBillException.class)
    public void testGetOrderPriceNoMoreThan20Items() {
        RestaurantBillImpl bill = new RestaurantBillImpl();
        List<MenuItem> list = getItemsOrdered();
        list.addAll(list);

        assert(list.size() >= 20);
        bill.getOrderPrice(list);
    }

    /**
     * Se vengono ordinate più di 10 Pizze la meno costosa è in regalo (l’importo totale non considera
     * la pizza meno costosa)
     */
    @Test
    public void testGetOrderPriceCheapestPizzaIsFreeIfMoreThan10Pizzas() {
        RestaurantBillImpl bill = new RestaurantBillImpl();
        List<MenuItem> list = getItemsOrdered();
        list.add(new MenuItem(ItemType.PIZZE, "Pomodorini", 7));
        list.add(new MenuItem(ItemType.PIZZE, "Patatosa", 5.50));
        double totalRawPrice = 75.5 + 7 + 5.50;
        double minPrice = 3;
        assertEquals(totalRawPrice - minPrice, bill.getOrderPrice(list), 0.0001);
    }
}
