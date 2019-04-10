package au.edu.sydney.brawndo.erp.ordering;

import au.edu.sydney.brawndo.erp.auth.AuthToken;

import java.math.BigDecimal;

public interface OrderingFacade {

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * The order will be recorded in the ordering module and impact lifetime cost for this customer<br>
     *
     * @param token The authentication token to verify this operation with. May not be null.
     * @param custfName The customer's first name (and partial primary key). May not be null or empty.
     * @param custlName The customer's last name (and partial primary key). May not be null or empty.
     * @param qty The quantity of Brawndo© The Thirst Mutilator to order. Must be greater than 0.
     * @return The cost of this order (dollars as the whole number component, cents as the decimal). Must be greater than 0.
     * @throws IllegalArgumentException If any of the preconditions are violated.
     * @throws SecurityException If the authentication token fails verification.
     */
    BigDecimal placeOrder(AuthToken token, String custfName, String custlName, int qty) throws IllegalArgumentException, SecurityException;

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param token The authentication token to verify this operation with. May not be null.
     * @param custfName The customer's first name (and partial primary key). May not be null or empty.
     * @param custlName The customer's last name (and partial primary key). May not be null or empty.
     * @return If no orders have been recorded returns 0 - otherwise the total cost of all previous orders for this
     * customer - will be greater than 0. Never returns null.
     * @throws IllegalArgumentException If any of the preconditions are violated.
     * @throws SecurityException If the authentication token fails verification.
     */
    BigDecimal getLifetimeCost(AuthToken token, String custfName, String custlName) throws IllegalArgumentException, SecurityException;
}
