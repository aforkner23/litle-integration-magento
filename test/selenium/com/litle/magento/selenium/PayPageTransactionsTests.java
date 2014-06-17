package com.litle.magento.selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.Select;

public class PayPageTransactionsTests extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        iAmDoingCCOrEcheckTransaction();
        iAmDoingPaypageTransaction();
	iAmDoingLitleSale();
    }


	@Test
	public void backendPaypageAuthCheckoutThenAttemptCapture() throws Exception {
	iAmDoingLitleAuth();
        clearCache();
	iAmLoggedInAsAnAdministrator();
	iView("Sales", "Orders");
	iPressCreateNewOrder();	
        iClickOnTheCustomerWithEmail("gdake@litle.com");
        iClickAddProducts();
        iAddTheTopRowInProductsToTheOrder();

        waitFor(By.id("p_method_creditcard"));
        driver.findElement(By.id("p_method_creditcard")).click();
        waitFor(By.id("payment_form_creditcard"));
        WebElement e = driver.findElement(By.id("payment_form_creditcard"));
        String linkText = e.getText();
        assertEquals("Litle Virtual Terminal", linkText);
        System.out.println(linkText);
        String url = e.findElement(By.tagName("a")).getAttribute("href");
        System.out.println(url);
        assertEquals("https://reports.litle.com/ui/vt", url);

        driver.findElement(By.id("order-comment")).click();
	//adding shipping-address details
	e=driver.findElement(By.id("order-billing_address_firstname"));	
	e.clear();
	e.sendKeys("Dragon");
	
	e=driver.findElement(By.id("order-billing_address_lastname"));	
	e.clear();
	e.sendKeys("Dragon");
	
	e=driver.findElement(By.id("order-billing_address_street0"));	
	e.clear();
	e.sendKeys("Dragon");

	e=driver.findElement(By.id("order-billing_address_city"));	
	e.clear();
	e.sendKeys("Dragon");

	e=driver.findElement(By.id("order-billing_address_postcode"));	
	e.clear();
	e.sendKeys("123");

	e=driver.findElement(By.id("order-billing_address_telephone"));	
	e.clear();
	e.sendKeys("123456");

	Select select = new Select(driver.findElement(By.id("order-billing_address_region_id")));
	select.selectByVisibleText("Alaska");
        
	//And I configure shipping method
        waitFor(By.id("order-shipping-method-summary"));
        driver.findElement(By.linkText("Get shipping methods and rates")).click();
	waitForIdVisible("s_method_flatrate_flatrate");
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
	
	driver.findElement(By.id("order-comment")).click();

	iPressSubmitOrder();

        iView("Sales", "Orders");
        iClickOnTheTopRowInOrders();
        iPressInvoice();
        iPressSubmitInvoice("This order was placed using Litle Virtual Terminal. Please process the capture by logging into Litle Virtual Terminal (https://reports.litle.com).", null);
        iLogOutAsAdministrator();
	}
//TODO The sandbox PayPage isn't equipped to deal with failures
//    @Test
//    public void doAnUnsuccessfulCheckout() throws Exception {
//        iAmLoggedInAsWithThePassword("gdake@litle.com", "password");
//        iHaveInMyCart("vault");
//        iFailCheckOutWith("Visa", "4137307201736110", "The order was not approved.  Please try again later or contact us.  For your reference, the transaction id is");
//        iLogOutAsUser();
//    }
//

}
