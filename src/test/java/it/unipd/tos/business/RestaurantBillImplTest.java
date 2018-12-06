package it.unipd.tos.business;

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
}
