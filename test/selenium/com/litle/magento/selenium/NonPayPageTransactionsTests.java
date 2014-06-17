package com.litle.magento.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class NonPayPageTransactionsTests extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        iAmDoingCCOrEcheckTransaction();
        iAmDoingNonPaypageTransaction();
    }

	@Test
	public void doASuccessfulAuthAndThenCaptureTheAuth() throws Exception {
	    iAmDoingLitleAuth();
	    iAmLoggedInAsWithThePassword("gdake@litle.com", "password");
	    iHaveInMyCart("vault");
	    iCheckOutWith("Visa", "4100000000000001");
	    iLogOutAsUser();

	    iAmLoggedInAsAnAdministrator();
	    iView("Sales", "Orders");
	    iClickOnTheTopRowInOrders();
	    iPressInvoice();
	    iSelectNameFromSelect("Capture Online", "invoice[capture_case]");
	    iPressSubmitInvoice("The invoice has been created.", null);
	    iLogOutAsAdministrator();
	}

	@Test
	public void doAnUnsuccessfulCheckout() throws Exception {
	    iAmLoggedInAsWithThePassword("gdake@litle.com", "password");
	    iHaveInMyCart("vault");
	    iFailCheckOutWith("Visa", "4137307201736110", "The order was not approved.  Please try again later or contact us.  For your reference, the transaction id is");
	    iLogOutAsUser();
        
	}
	
}
