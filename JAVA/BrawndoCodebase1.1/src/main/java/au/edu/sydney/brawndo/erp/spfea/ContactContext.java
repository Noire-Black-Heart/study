package au.edu.sydney.brawndo.erp.spfea;

import java.util.HashMap;
import java.util.Map;

/**
 *Contact strategy class, implement the ContactStrategy policy interface
 */
public class ContactContext implements ContactStrategy {
    private ContactStrategy strategy;
    private final Map<Enum, ContactStrategy> map = new HashMap<>();

    public ContactContext(){
        initMap();
    }
    @Override
    public boolean doSend(ContactBean bean) {
        //Get registered specific contact instance examples
        strategy = map.get(bean.getMethod());
        if(strategy == null){
            return  false;
        }
        //Call the actual doSend
        return strategy.doSend(bean);
    }

    /**
     * Register contact information, easy for subsequent expansion
     */
    private void initMap(){
        map.put(ContactMethod.SMS,new SMSHandler());
        map.put(ContactMethod.CARRIER_PIGEON,new CarrierPigeonHandler());
        map.put(ContactMethod.EMAIL,new EmailHandler());
        map.put(ContactMethod.MAIL,new MailHandler());
        map.put(ContactMethod.MERCHANDISER,new MerchandiserHandler());
        map.put(ContactMethod.PHONECALL,new PhoneHandler());
    }
}
