package com.litle.magento.selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EcheckTransactionsTests extends BaseTestCase {

    @Before
    public void background() throws Exception {
        iAmDoingCCOrEcheckTransaction();
	clearCache();
    }

    @Test
    public void doAVerifiyAndThenSale() throws Exception {
       iAmLoggedInAsWithThePassword("gdake@litle.com", "password");
        iHaveInMyCart("vault");
        iCheckOutWithEcheck("123456000", "123456000", "Checking");
        iLogOutAsUser();

        iAmLoggedInAsAnAdministrator();
        iView("Sales", "Orders");
        iClickOnTheTopRowInOrders();
        iPressInvoice();
        iPressSubmitInvoice(null, null);
        iLogOutAsAdministrator();
     
    }

    @Test
    public void testBackendECheckVerifyThenAttemptSale() throws Exception {
        iAmLoggedInAsAnAdministrator();
        iView("Sales", "Orders");
        iPressCreateNewOrder();
        iClickOnTheCustomerWithEmail("gdake@litle.com");
        iClickAddProducts();
        iAddTheTopRowInProductsToTheOrder();
         
        //adding shipping-address details
	WebElement e=driver.findElement(By.id("order-billing_address_firstname"));	
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
        
        waitForIdVisible("order-shipping_same_as_billing");
        driver.findElement(By.id("order-shipping_same_as_billing")).click();
        //And I choose "Echeck"
        waitFor(By.id("p_method_lecheck"));
        driver.findElement(By.id("p_method_lecheck")).click();
        //And I enter a routing number
        waitForIdVisible("lecheck_echeck_routing_number");
        e = driver.findElement(By.id("lecheck_echeck_routing_number"));
        e.clear();
        e.sendKeys("123456000");
        //And I enter a bank account number
        e = driver.findElement(By.id("lecheck_echeck_bank_acct_num"));
        e.clear();
        e.sendKeys("123456000");
        //And I select Checking
        iSelectFromSelect("Checking", "lecheck_echeck_account_type");
       
        //And I configure shipping method
        waitFor(By.id("order-shipping-method-summary"));
        driver.findElement(By.linkText("Get shipping methods and rates")).click();
        waitForIdVisible("s_method_flatrate_flatrate");
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
     
        iPressSubmitOrder();
        iView("Sales", "Orders");

        iClickOnTheTopRowInOrders();
        iPressInvoice();
        iPressSubmitInvoice("The invoice has been created.", "Captured amount of $6.99 online. Transaction ID:");
        iLogOutAsAdministrator();
    }

}
