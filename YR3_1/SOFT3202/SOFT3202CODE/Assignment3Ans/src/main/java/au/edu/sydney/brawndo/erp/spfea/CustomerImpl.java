package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.database.TestDatabase;
import au.edu.sydney.brawndo.erp.ordering.Customer;

public class CustomerImpl implements Customer {

    private final int id;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String suburb;
    private String state;
    private String postCode;
    private String merchandiser;
    private String businessName;
    private String pigeonCoopID;

    public CustomerImpl(AuthToken token, int id) {

        this.id = id;
        this.fName = CustomerConnFactory.getConn("fName").getCustomerField(token,id);
        this.lName = CustomerConnFactory.getConn("lName").getCustomerField(token,id);
        this.phoneNumber = CustomerConnFactory.getConn("phoneNumber").getCustomerField(token,id);
        this.emailAddress = CustomerConnFactory.getConn("emailAddress").getCustomerField(token,id);
        this.address = CustomerConnFactory.getConn("address").getCustomerField(token,id);
        this.suburb = CustomerConnFactory.getConn("suburb").getCustomerField(token,id);
        this.state = CustomerConnFactory.getConn("state").getCustomerField(token,id);
        this.postCode = CustomerConnFactory.getConn("postCode").getCustomerField(token,id);
        this.merchandiser = CustomerConnFactory.getConn("merchandiser").getCustomerField(token,id);
        this.businessName = CustomerConnFactory.getConn("businessName").getCustomerField(token,id);
        this.pigeonCoopID = CustomerConnFactory.getConn("pigeonCoopID").getCustomerField(token,id);
    }



    public int getId() {
        return id;
    }

    @Override
    public String getfName() {
        return fName;
    }

    @Override
    public String getlName() {
        return lName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getSuburb() {
        return suburb;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getPostCode() {
        return postCode;
    }

    @Override
    public String getMerchandiser() {
        return merchandiser;
    }

    @Override
    public String getBusinessName() {
        return businessName;
    }

    @Override
    public String getPigeonCoopID() {
        return pigeonCoopID;
    }
}

