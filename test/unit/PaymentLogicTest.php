<?php
/*
 * Copyright (c) 2011 Litle & Co.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
require_once(getenv('MAGENTO_HOME')."/app/Mage.php");
require_once(getenv('MAGENTO_HOME')."/app/code/core/Mage/Core/Block/Abstract.php");
require_once(getenv('MAGENTO_HOME')."/app/code/core/Mage/Core/Block/Template.php");
require_once(getenv('MAGENTO_HOME')."/app/code/core/Mage/Adminhtml/Block/Template.php");
require_once(getenv('MAGENTO_HOME')."/app/code/core/Mage/Adminhtml/Block/Widget/Container.php");
require_once(getenv('MAGENTO_HOME')."/app/code/core/Mage/Adminhtml/Block/Sales/Transactions/Detail.php");
require_once("../../app/code/local/Litle/CreditCard/Model/PaymentLogic.php");

class PaymentLogicTest extends PHPUnit_Framework_TestCase
{
	public function testFormatAvailableBalance1()
	{		
 		$balance = Litle_CreditCard_Model_PaymentLogic::formatAvailableBalance('2000');
 		$this->assertEquals("$20.00",$balance);
	}
	
	public function testFormatAvailableBalance2()
	{
		$balance = Litle_CreditCard_Model_PaymentLogic::formatAvailableBalance('0');
		$this->assertEquals("$0.00",$balance);
	}
	
	public function testFormatAvailableBalance3()
	{
		$balance = Litle_CreditCard_Model_PaymentLogic::formatAvailableBalance('');
		$this->assertEquals("$0.00",$balance);
	}
	
	public function testFormatAvailableBalance4()
	{
		$balance = Litle_CreditCard_Model_PaymentLogic::formatAvailableBalance(NULL);
		$this->assertEquals("$0.00",$balance);
	}
	
	public function testFormatAvailableBalance5()
	{
		$balance = Litle_CreditCard_Model_PaymentLogic::formatAvailableBalance('10');
		$this->assertEquals("$0.10",$balance);
	}
	
	
}
