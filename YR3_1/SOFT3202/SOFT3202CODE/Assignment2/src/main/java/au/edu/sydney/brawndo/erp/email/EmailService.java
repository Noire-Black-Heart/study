package au.edu.sydney.brawndo.erp.email;

import au.edu.sydney.brawndo.erp.auth.AuthToken;

public interface EmailService {

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param token The authentication token to verify this operation with. May not be null.
     * @param email The customer email address to send to. May not be null or empty. Must contain at least one @ character.
     * @param data The data to be printed. May not be null or empty.
     * @throws SecurityException If the authentication token fails verification.
     * @throws IllegalArgumentException if any of the preconditions are violated.
     */
    void printInvoice(AuthToken token, String email, String data) throws SecurityException, IllegalArgumentException;
}
