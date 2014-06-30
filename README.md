## Description
This extension will allow you to accept payments through Litle & Co. on Magento.

## Installation
*To install Magento, follow directions on the following page:
[Magento Installation Guide](http://www.magentocommerce.com/wiki/1_-_installation_and_configuration/magento_installation_guide)
* Go to your git directory, enter " git clone https://github.com/LitleCo/litle-integration-magento.git  -b dev2"
* Download the composer.phar, enter "url -sS https://getcomposer.org/installer | php"
* Run the composer.phar, enter "php composer.phar install"
* Set up WORKSPACE, enter "WORKSPACE=your_Litle_Integration_Path"
* Set up magento environment
* Run the build to generate tar file, enter "ant -f build/build.xml"
* If shows TreeWalker error, go to your ANT_HOME/lib, Add those two jar files: Xalan.jar, serializer.jar
* Merge the magento files into litle-integration-magento folder , select Merge All
## Setup
Login to Admin panel in Magento - Navigate to System > Configuration
### Enable Litle Payments

* Go to Advanced > Advanced
* Under "Disable Modules Output", set Litle_CreditCard to "Enable" and Litle_LEcheck to "Enable"

### Setup Litle Payments
* Go to Sales > Payment Methods

* Expand Litle - Credit Card. Set the field values as:
    
    Enabled: Yes

    Title: Heading you would like your customers to see. Typically set to Credit Card
    
    User: Litle User Name
    
    Password: Litle password
    
    Merchant ID*: Litle Merchant ID
    
    Report Group: Default Report Group
    
    New Order Status: Processing
    
    Payment Action: You may choose "Authorize Only", or "Authorize and Capture". If you choose "Authorize Only", you will have to manually process the Captures later.
    
    HTTP URL*: If performing preliminary testing, you may select Sandbox. If you are in process of setting up an account with Litle, then you may select Pre-Cert, Cert, or Production depending on which step you are at.
    
    HTTP Proxy: If you need to use a proxy, you need to enter it here.
    
    HTTP Timeout: Recommended timeout is 65
        
* Expand Litle - Echeck.  The Echeck configuration uses the same configuration as above, except for:
    Title: Typically set to ECheck

    *Payment Action: You may choose "Verification", or "Sale". Selecting verification will only verify the account upon customer checkout and a sale transaction can be done at a later time. Choosing sale will do both a verification and a sale transaction with the payment information upon checkout. 

    *Account Types: Select account types from which you would like to accept payments. You may select multiple types by holding Ctrl.
    
* Click on "Save Config".  *Upon clicking "Save Config", Magento will try to connect to Litle & Co servers to check connectivity. In-case the connect fails, you will be shown an error message.  * In addition hitting the 'Save Config' file will check to see that the merchantId is in the proper format. The merchantId should be of the form ('currency'=> merhcantId), this allows for multiple currecncy support. Note: USD must be present in the merchantId.If you see the error message and need help, please contact Litle SDK team at: sdksupport@litle.com.

NOTE: You do not need a valid username and/or password while connecting to Sandbox.  You may enter any fake values.
