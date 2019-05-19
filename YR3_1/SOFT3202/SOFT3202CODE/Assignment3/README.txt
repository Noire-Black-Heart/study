1. Solved the large RAM usage issue. 
	in: SPFEAFacade.java, 163~165 line was edited; ProductListSingleton.java class was created. 

2. Solved the order type issue. 
	in: createOrder() from SPFEAFacade class was edited; 
	Discount.java, DiscountBulk, DiscountFlat
	PersonalSub, PersonalNoSub, BusinessSub, BusinessNoSub
	these classes were created. 

3. Solved the customer contact handling issue. 
	in: sendInvoice() from ContactHandler class was edited; 
	ContactContext, ContactStrategy, EmailStrategy, MailStrategy, MerchandiserStrategy, PhoneCallStrategy, SMSStrategy
	these classes were created. 

4. Solved the customer loading slowly issue. 
	in: CustomerImpl.java class. 

5. Solved the product comparison issue
	in: productImpl.java was edited
	PersonalNoSub, BusinessNoSub classes were created

Failed to solve the database operation issue and multithreading issue. 