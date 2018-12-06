////////////////////////////////////////////////////////////////////
// ALBERTO SCHIABEL 1144672
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class RestaurantBillException extends RuntimeException {
    public RestaurantBillException() {
        super("Too many items ordered!");
    }
}
