package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.ordering.Customer;

/**
 * Contact data class
 * Used to save contact data
 */
public class ContactBean {
    private AuthToken token;
    private Customer customer;
    private ContactMethod method;
    private String data;

    public AuthToken getToken() {
        return token;
    }

    public ContactBean setToken(AuthToken token) {
        this.token = token;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ContactBean setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ContactMethod getMethod() {
        return method;
    }

    public ContactBean setMethod(ContactMethod method) {
        this.method = method;
        return this;
    }

    public String getData() {
        return data;
    }

    public ContactBean setData(String data) {
        this.data = data;
        return this;
    }
}
