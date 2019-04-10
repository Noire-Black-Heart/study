package au.edu.sydney.brawndo.erp.print;

import au.edu.sydney.brawndo.erp.auth.AuthToken;

public interface PrintService {

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param token The authentication token to verify this operation with. May not be null.
     * @param data The data to be printed. May not be null or empty.
     * @throws SecurityException If the authentication token fails verification.
     * @throws IllegalArgumentException If any of the preconditions are violated.
     */
    void printInvoice(AuthToken token, String data) throws SecurityException, IllegalArgumentException;
}
