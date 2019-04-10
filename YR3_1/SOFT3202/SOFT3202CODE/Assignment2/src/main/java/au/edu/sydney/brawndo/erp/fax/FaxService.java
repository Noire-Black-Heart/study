package au.edu.sydney.brawndo.erp.fax;

import au.edu.sydney.brawndo.erp.auth.AuthToken;

public interface FaxService {

    /**
     * In Brawndo-land phone numbers and fax numbers are the sme thing.
     *
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param token The authentication token to verify this operation with. May not be null.
     * @param phone The customer phone number to fax to. May not be null or empty. Must contain only numbers and +() characters.
     * @param data The data to be printed. May not be null or empty.
     * @throws SecurityException If the authentication token fails verification.
     * @throws IllegalArgumentException if any of the preconditions are violated.
     */
    void faxInvoice(AuthToken token, String phone, String data) throws SecurityException, IllegalArgumentException;
}
