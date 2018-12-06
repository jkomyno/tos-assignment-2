////////////////////////////////////////////////////////////////////
// ALBERTO SCHIABEL 1144672
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;

public class RestaurantBillImpl implements RestaurantBill {

    @Override
    /**
     * Dato un elenco di ordinazioni (Pizze e Primi piatti) calcolare il totale (somma del prezzo
     * dei prodotti ordinati)
     *
     * Se vengono ordinate più di 10 Pizze la meno costosa è in regalo (l’importo totale non
     * considera la pizza meno costosa)
     *
     * Se l’importo totale delle ordinazioni (Pizze e Primi) supera i 100 euro viene fatto un 5%
     * di sconto
     *
     * Non è possibile avere un’ordinazione con più di 20 elementi (se accade prevedere un
     * messaggio d’errore
     *
     * @param itemsOrdered
     * @return
     * @throws RestaurantBillException
     */
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException {
        double totalPrice = getTotalPrice(itemsOrdered);

        if (totalPrice > 100) {
            totalPrice = getDiscount(totalPrice, 5);
        }

        return totalPrice;
    }

    /**
     * Sums the prices of every item in itemsOrdered
     * @param itemsOrdered
     * @return
     */
    private static double getTotalPrice(List<MenuItem> itemsOrdered) {
        return itemsOrdered.stream()
                .mapToDouble(item -> item.getPrice())
                .sum();
    }

    /**
     * Applies discount to the original price and returns the resulting value.
     * It assumes that both originalPrice and discount are != 0
     * @param originalPrice
     * @param discount
     * @return
     */
    private static double getDiscount(double originalPrice, double discount) {
        double amountToDiscount = originalPrice * discount / 100;
        return originalPrice - amountToDiscount;
    }
}
