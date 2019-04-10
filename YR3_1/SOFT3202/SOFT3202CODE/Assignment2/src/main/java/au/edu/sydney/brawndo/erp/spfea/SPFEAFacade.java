package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthModule;
import au.edu.sydney.brawndo.erp.email.EmailService;
import au.edu.sydney.brawndo.erp.fax.FaxService;
import au.edu.sydney.brawndo.erp.ordering.OrderingFacade;
import au.edu.sydney.brawndo.erp.print.PrintService;
import au.edu.sydney.brawndo.erp.todo.Task;
import au.edu.sydney.brawndo.erp.todo.ToDoList;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface SPFEAFacade {
    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * All method calls requiring this service will be passed to the given provider until this method is invoked again.<br>
     * If this method is invoked with a null parameter (a legal operation) all such calls will fail.<br>
     *
     * @param provider The service provider to be injected
     */
    void setToDoProvider(ToDoList provider);

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * All method calls requiring this service will be passed to the given provider until this method is invoked again.<br>
     * If this method is invoked with a null parameter (a legal operation) all such calls will fail.<br>
     * This method will set SPFEA to a logged-out state regardless of any prior Auth state<br>
     *
     * @param provider The service provider to be injected
     */
    void setAuthProvider(AuthModule provider);

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * All method calls requiring this service will be passed to the given provider until this method is invoked again.<br>
     * If this method is invoked with a null parameter (a legal operation) all such calls will fail.<br>
     *
     * @param provider The service provider to be injected
     */
    void setEmailProvider(EmailService provider);

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * All method calls requiring this service will be passed to the given provider until this method is invoked again.<br>
     * If this method is invoked with a null parameter (a legal operation) all such calls will fail.<br>
     *
     * @param provider The service provider to be injected
     */
    void setFaxProvider(FaxService provider);

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * All method calls requiring this service will be passed to the given provider until this method is invoked again.<br>
     * If this method is invoked with a null parameter (a legal operation) all such calls will fail.<br>
     *
     * @param provider The service provider to be injected
     */
    void setOrderingProvider(OrderingFacade provider);

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * All method calls requiring this service will be passed to the given provider until this method is invoked again.<br>
     * If this method is invoked with a null parameter (a legal operation) all such calls will fail.<br>
     *
     * @param provider The service provider to be injected
     */
    void setPrintProvider(PrintService provider);

    /**
     * <b>Preconditions:</b><br>
     * The SPFEAFacade must have been injected with a non-null instance of a ToDo module via SPFEAFacade.setToDoProvider<br>
     * <b>Postconditions:</b><br>
     * The given task details will be stored in the application and retrievable by task lookup methods with matching criteria<br>
     *
     * @param dateTime may not be null, must be a value greater than or equal to LocalDateTime.now()
     * @param description may not be null or empty
     * @param location must be one of: "HOME OFFICE", "CUSTOMER SITE", or "MOBILE"
     * @throws IllegalArgumentException any of the parameter preconditions are breached
     * @throws IllegalStateException if called with a null ToDo provider set
     * @return The (not completed) Task with matching fields created by this method call
     */
    Task addNewTask(LocalDateTime dateTime, String description, String location) throws IllegalArgumentException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * The SPFEAFacade must have been injected with a non-null instance of a ToDo module via SPFEAFacade.setToDoProvider<br>
     * The id must have a corresponding incomplete task stored in the service provider<br>
     * <b>Postconditions:</b><br>
     * The task with the matching ID will be marked as completed<br>
     *
     * @param id The target task id
     * @throws IllegalArgumentException if no matching task exists
     * @throws IllegalStateException if called with a null ToDo provider set, or if the matching task has already been completed
     */
    void completeTask(int id) throws IllegalArgumentException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * The SPFEAFacade must have been injected with a non-null instance of a ToDo module via SPFEAFacade.setToDoProvider<br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @return A complete list of all tasks which have been added to and not removed from the current instance of the service provider. May not be null, may be empty
     * Changes to this list itself will not be reflected in the SPFEAFacade, but changes to the Tasks it contains will be
     * @throws IllegalStateException if called with a null ToDo provider set
     */
    List<Task> getAllTasks() throws IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * No customer with an exactly matching fName and lName may exist in this instance<br>
     * One of either phone or email must not be null<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * A customer record with the matching fields will be added to this instance<br>
     * By default this customer prefers any contact channel provided, but not print.<br>
     *
     * @param fName may not be null or empty
     * @param lName may not be null or empty
     * @param phone may be null, may not be empty, may only contain numeric characters and ‘+()’ if not null
     * @param email may be null, may not be empty, must contain at least one @ character if not null
     * @return The id of the new customer record added to this instance
     * @throws IllegalArgumentException if any of the parameter preconditions are violated
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     */
    int addCustomer(String fName, String lName, String phone, String email) throws IllegalArgumentException, SecurityException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param fName may not be null or empty
     * @param lName may not be null or empty
     * @return If a customer with an exactly matching fName and lName exists in this instance, the id of that customer – otherwise null
     * @throws IllegalArgumentException if any of the parameter preconditions are violated
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     */
    Integer getCustomerID(String fName, String lName) throws IllegalArgumentException, SecurityException,IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     * @return A list of all current customers in the string format "lName, fName" (may be empty, may not be null)
     */
    List<String> getAllCustomers() throws SecurityException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param id The target customer id.
     * @return The phone field associated with this customer id – may be null, may not be empty, will contain only numeric characters and ‘+()’ if not null
     * @throws IllegalArgumentException If no matching customer exists
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     */
    String getCustomerPhone(int id) throws IllegalArgumentException, SecurityException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * If phone is null, matching customer must have a non-null email field set<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * The matching customer’s phone field will be updated to match the phone parameter<br>
     *
     * @param id The target customer id
     * @param phone may be null, may not be empty, may only contain numeric characters and ‘+()’ if not null
     * @throws IllegalArgumentException If any of the parameter preconditions are breached
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     */
    void setCustomerPhone(int id, String phone) throws IllegalArgumentException, SecurityException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param id The target customer id
     * @return The email associated with this customer id – may be null, may not be empty, will contain at least one @ character if not null
     * @throws IllegalArgumentException If no matching customer exists
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     */
    String getCustomerEmail(int id) throws IllegalArgumentException, SecurityException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * If email is null, matching customer must have a non-null phone field set<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * The matching customer’s email field will be updated to match the email parameter<br>
     *
     * @param id The target customer id
     * @param email may be null, may not be empty, must contain at least one @ symbol if not null
     * @throws IllegalArgumentException If any of the parameter preconditions are breached
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     */
    void setCustomerEmail(int id, String email) throws IllegalArgumentException, SecurityException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * No customer with a matching id will exist in this instance<br>
     *
     * @param id The target customer id
     * @throws IllegalArgumentException If no matching customer exists
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalStateException if called with a null Auth provider set
     */
    void removeCustomer(int id) throws IllegalArgumentException, SecurityException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * At least one preference must be set.
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * An invoice will be created for all true preference parameter channels for all subsequent orders<br>
     *
     * @param customerID The target customer id
     * @param email Send an email invoice when this customer places an order
     * @param print Send a printed invoice when this customer places an order
     * @param fax Send an faxed invoice when this customer places an order
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalArgumentException if any of the parameter preconditions are breached
     * @throws IllegalStateException if called with a null Auth provider set
     */
    void setCustomerPreferences(int customerID, boolean email, boolean print, boolean fax) throws SecurityException, IllegalArgumentException, IllegalStateException;


    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * The SPFEAFacade must have a corresponding service provider for any channel preference the given customer has set (via setCustomerPreferences)
     * <b>Postconditions:</b><br>
     * An order will be recorded for this customer. Invoices will be generated for all set customer preferences through
     * the appropriate services.
     *
     * @param customerID The target customer id
     * @param qty The quantity of Brawndo "It's got Electrolytes!" to order. Must be greater than 0.
     * @return The rounded (0.5 = 1) dollar cost of this order. Must be greater than 0.
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalArgumentException if any of the parameter preconditions are breached
     * @throws IllegalStateException if any required provider (Auth, invoice channels) is null
     */
    int placeOrder(int customerID, int qty) throws SecurityException, IllegalArgumentException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * A customer with the matching id exists in this instance<br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * <br>
     * @param customerID The target customer id
     * @return The rounded (0.5 = 1) dollar cost of this customer's lifetime orders. Will return a minimum of 1 if any
     * orders exist regardless of rounding, 0 if no orders exist.
     * @throws SecurityException if SPFEA is not logged in or the logged in user cannot be authenticated
     * @throws IllegalArgumentException if any of the parameter preconditions are breached
     * @throws IllegalStateException if any required provider (Auth, invoice channels) is null
     */
    int getTotalLifetimeCosts(int customerID) throws SecurityException, IllegalArgumentException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * The SPFEAFacade must not be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * SPFEA will be in a logged in state until its Auth provider is changed or logout() is called.<br>
     * @param userName The username to verify. May not be null or empty.
     * @param password The password to verify. May not be null or empty.
     * @return True if the credentials were valid, otherwise false.
     * @throws IllegalArgumentException if any of the parameter preconditions are breached
     * @throws IllegalStateException if called with a null Auth provider set, or if already in a logged-in state
     */
    boolean login(String userName, String password) throws IllegalArgumentException, IllegalStateException;

    /**
     * <b>Preconditions:</b><br>
     * The SPFEAFacade must have been injected with a non-null instance of an AuthModule via SPFEAFacade.setAuthProvider<br>
     * SPFEA must be in a logged-in state prior to this call<br>
     * <b>Postconditions:</b><br>
     * SPFEA will no longer be in a logged-in state<br>
     * @throws IllegalStateException if called with a null Auth provider set, or if SPFEA was not already in a logged-in state
     */
    void logout() throws IllegalStateException;
}
