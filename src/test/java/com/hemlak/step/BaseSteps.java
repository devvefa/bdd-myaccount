package com.hemlak.step;

import com.hemlak.base.BaseTest;
import com.hemlak.base.DriverFactory;
import com.hemlak.helper.ElementHelper;
import com.hemlak.helper.StoreHelper;
import com.hemlak.model.ElementInfo;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class BaseSteps extends BaseTest {

    public static int DEFAULT_MAX_ITERATION_COUNT = 1;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;
    private static String actualUrl = "";

    protected static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
            .getLogger(BaseSteps.class);
    private final Actions actions = new Actions(DriverFactory.getDriver());
    private static Random random = new Random();

    public WebDriverWait driverWait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));

    public BaseSteps() {

        PropertyConfigurator
                .configure(BaseSteps.class.getClassLoader().getResource("log4j.properties"));
    }

    /**
     * Elementi selektore gore bulur ve geri dondurur
     *
     * @param key bulunacak elementin selektor keyi
     * @return bulunana element geri dondurulur
     */
    public WebElement findElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        System.out.println("loog"+elementInfo.getValue());
       // By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        By infoParam = By.xpath(elementInfo.getValue());

        logger.info("Element - " + elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getDriver(), 60);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    /**
     * Element xpath selektorune gore aranir bulunan element
     * nesnesi geri dondurulur
     *
     * @param xpath xpath selektoru
     * @return WebElement nesnesi
     */
    protected WebElement findElementByXpath(String xpath) {
        By infoParam = By.xpath(xpath);
        WebElement webElement;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getDriver(), 60);
            webElement = webDriverWait
                    .until(ExpectedConditions.presenceOfElementLocated(infoParam));
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
        } catch (Exception e) {
            webElement = null;
            logger.info(xpath + "\n xpathli element bulunamadı");

        }

        return webElement;
    }

    /**
     * Elementi 60 saniye boyunca xpathe gore arar
     *
     * @param xpath element xpath selektoru
     * @return element bulunduktan sonra deger
     * nesnesi dondurulur
     */
    protected WebElement findElementByXpathVisibilityOfElementLocated(String xpath) {
        By infoParam = By.xpath(xpath);
        WebElement webElement;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getDriver(), 60);
            webElement = webDriverWait
                    .until(ExpectedConditions.visibilityOfElementLocated(infoParam));
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
        } catch (Exception e) {
            webElement = null;
            logger.info(xpath + "\n xpathli element bulunamadı");

        }

        return webElement;
    }

    /**
     * Elementi 60 saniye boyunca xpathe gore arar
     *
     * @param xpath element xpath selektoru
     * @return element bulunduktan sonra deger
     * nesnesi dondurulur
     */
    protected WebElement findElementByXpathElementToBeClickable(String xpath) {
        By infoParam = By.xpath(xpath);
        WebElement webElement;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getDriver(), 60);
            webElement = webDriverWait
                    .until(ExpectedConditions.elementToBeClickable(infoParam));
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
        } catch (Exception e) {
            webElement = null;
            logger.info(xpath + "\n xpathli element bulunamadı");

        }

        return webElement;
    }

    /**
     * Xpath selektorune gore elementleri bulur bulunan elementleri
     * liste halinde geri dondurur
     *
     * @param xpath elemenlerin xpath selektoru
     * @return elementler bulunduktan sonra dondurulur
     */
    protected List<WebElement> findElementsByXpath(String xpath) {
        By infoParam = By.xpath(xpath);
        WebElement webElement;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getDriver(), 60);
            webElement = webDriverWait
                    .until(ExpectedConditions.presenceOfElementLocated(infoParam));
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
        } catch (Exception e) {
            logger.info(xpath + "\n xpathli element bulunamadı");

        }

        return DriverFactory.getDriver().findElements(infoParam);
    }

    /**
     * Elementleri key selektorune gore bulur ve liste halinde geri dondurur
     *
     * @param key elementlerin selektor keyi
     * @return elementlerin listesi
     */
    protected List<WebElement> findElements(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getDriver(), 60);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return DriverFactory.getDriver().findElements(infoParam);
    }

    /**
     * Element ekranda gozukune kadar 60 saniye bekler
     * element gozuktukten sonra tiklar
     *
     * @param element tiklanacak element nesnesi
     */
    private void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 60);
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor jsExec = (JavascriptExecutor) DriverFactory.getDriver();
        jsExec.executeScript("arguments[0].style.border='3px solid red'", element);
        Gauge.captureScreenshot();
        element.click();
    }

    /**
     * Element sayfada gozukuyor mu kontrol eder
     *
     * @param by Selector tipi girilir
     * @return element sayfada gozukurya true
     * gozukmuyorsa false doner
     */
    private boolean isDisplayedBy(By by) {
        return DriverFactory.getDriver().findElement(by).isDisplayed();
    }

    @Step({"Write random value to element <key>",
            "<key> elementine random değer yaz"})
    public void writeRandomValueToElement(String key) {
        findElement(key).sendKeys(randomString(15));
    }

    /**
     * Verilen sayi kadar random string olusturur
     *
     * @param stringLength olusturulacak random string uzunlugu
     * @return olusturulan random string geri dondurulur
     */
    public String randomString(int stringLength) {
        Random random = new Random();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUWVXYZabcdefghijklmnopqrstuwvxyz0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            stringBuilder.append(chars[random.nextInt(chars.length)]);
        }

        return stringBuilder.toString();
    }

    /**
     * Elementi keyine gore bulur ve geri dondurur
     *
     * @param key aranacak elementin selektor keyi
     * @return element bulunduktan sonra Element nesnesi
     * geri dondurulur
     */
    public WebElement findElementWithKey(String key) {
        return findElement(key);
    }

    /**
     * @param driver
     * @param element
     */
    public void javaScriptClicker(WebDriver driver, WebElement element) {

        JavascriptExecutor jse = ((JavascriptExecutor) DriverFactory.getDriver());
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }

    /**
     * Web elementine "arguments[0].click()" javascript komutu ile tiklanir
     *
     * @param element Tiklanicak Web element
     */
    public void javascriptclicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDriver();
        executor.executeScript("arguments[0].style.border='3px solid red'", element);
        Gauge.captureScreenshot();
        executor.executeScript("arguments[0].click();", element);

    }

    @Step({"Wait <value> seconds",
            "<int> saniye bekle"})
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
            Gauge.captureScreenshot();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"Wait <value> milliseconds",
            "<long> milisaniye bekle"})
    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info(milliseconds + " milisaniye bekleniyor.");
            Thread.sleep(milliseconds);
            Gauge.captureScreenshot();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step({"Click to element <key>",
            "Elementine tıkla <key>"})
    public void clickElement(String key) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getDriver(), 100);
        WebElement element;
        try {
            element = findElement(key);
            waitByMilliSeconds(500);
            clickElement(element);
            logger.info(key + " elementine tıklandı.");
        } catch (StaleElementReferenceException e) {
            element = findElement(key);
            waitByMilliSeconds(500);
            clickElement(element);
            logger.info("Stale Element Reference Exception");
            logger.info(key + " elementine tıklandı.");
        } catch (ElementClickInterceptedException e) {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(infoParam));
            element = findElement(key);
            javascriptclicker(element);
            Gauge.captureScreenshot();
            logger.info(key + " elementine tıklandı.");
        }

    }


    @Step("Yükleme ikonu kaybolana kadar bekle limit saniye <limit>")
    public void waitForElementDisapear(int limit) {
        int i = 0;
        while (true) {
            waitByMilliSeconds(500);
            try {
                DriverFactory.getDriver().findElement(By.xpath("//*[@class='loading-message loading-message-boxed']"));
                i++;
                if (i == limit) {
                    Assert.fail("Yükleme ikonunun " + limit + " saniye içersinde kaybolması gerekiyordu fakat kaybolmadı");
                }
            } catch (Exception e) {
                return;
            }
            waitBySeconds(1);
        }

    }

    /**
     * Web elementine selenium actions ile tiklanir
     *
     * @param key json dosyasında bulunan sellectorun keyi
     */
    @Step({"Click to element <key> with focus",
            "<key> elementine focus ile tıkla"})
    public void clickElementWithFocus(String key) {
        actions.moveToElement(findElement(key));
        Gauge.captureScreenshot();
        actions.click();
        actions.build().perform();
        logger.info(key + " elementine focus ile tıklandı.");
    }

    /**
     * Elementin yuklenene kadar beklenmesi
     * Ayni zamanda elementin varliginin kontrolu
     * Element bulunamassa fail verilir
     *
     * @param key
     * @return
     */
    @Step({"Check if element <key> exists",
            "Wait for element to load with key <key>",
            "Element var mı kontrol et <key>",
            "Elementin yüklenmesini bekle <key>"})
    public WebElement getElementWithKeyIfExists(String key) {
        WebElement webElement;
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            try {
                webElement = findElementWithKey(key);
                logger.info(key + " elementi bulundu.");
                return webElement;
            } catch (WebDriverException e) {
                logger.info("Element not found" + e.getMessage());
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element: '" + key + "' doesn't exist.");
        return null;
    }

    /**
     * Elementin yuklenene kadar beklenmesi
     * Ayni zamanda elementin varliginin kontrolu
     * Element bulunamassa fail verilir
     * Fail mesajı opsiyoneldir specs içersinden belirtilir
     *
     * @param key
     * @param message fail alınırsa verilecek hata mesajı
     */
    @Step({"Check if element <key> exists else print message <message>",
            "Element <key> var mı kontrol et yoksa hata mesajı ver <message>"})
    public void getElementWithKeyIfExistsWithMessage(String key, String message) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (DriverFactory.getDriver().findElements(by).size() > 0) {
                logger.info(key + " elementi bulundu.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail(message);
    }

    /**
     * Belirtilen element sayfada bulunuyor ise fail verilir
     * fail opsiyoneldir specs de belirtilebilir
     *
     * @param key
     * @param message
     */
    @Step({"Check if element <key> exists if there is print message <message>",
            "Element <key> var mı kontrol et varsa hata mesajı ver <message>"})
    public void getElementWithKeyIfNotExistsWithMessage(String key, String message) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (DriverFactory.getDriver().findElements(by).size() > 0) {
                logger.info(key + " elementi bulundu.");
                Assert.fail(message);
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }

    }

    /**
     * Belirtilen Texti elemente yazar element ve text specs dosyasında belirtilir
     *
     * @param text
     * @param key
     */
    @Step({"Write value <text> to element <key>",
            "<text> textini <key> elemente yaz"})
    public void ssendKeys(String text, String key) {
        if (!key.equals("")) {
            WebElement element = findElement(key);
            JavascriptExecutor jsExec = (JavascriptExecutor) DriverFactory.getDriver();
           // jsExec.executeScript("arguments[0].style.border='3px solid red'", element);
            Gauge.captureScreenshot();
            try {
                element.clear();
            } catch (Exception e) {

            }


            element.sendKeys(text);
            Gauge.captureScreenshot();
            logger.info(key + " elementine " + text + " texti yazıldı.");

        }
    }

    /**
     * normal textboxa text yazma metodunun aksine bu metod belirtilen texti yazmadan önce back space tusu ile textboxı temizler
     *
     * @param text
     * @param key
     */
    @Step({"Clear With backSpace Write value <text> to element <key>",
            "Back Space İle Temizle ve <text> textini <key> elemente yaz"})
    public void ssendKeysBackSpace(String text, String key) {
        if (!key.equals("")) {
            WebElement element = findElement(key);
            element.click();
            element.sendKeys(Keys.CONTROL, "a");
            element.sendKeys(Keys.DELETE);
            JavascriptExecutor jsExec = (JavascriptExecutor) DriverFactory.getDriver();
            jsExec.executeScript("arguments[0].style.border='3px solid red'", element);
            logger.info(key + " elementine " + text + " texti yazıldı.");
            element.sendKeys(text);
            Gauge.captureScreenshot();
            logger.info(key + " elementine " + text + " texti yazıldı.");
        }
    }

    /**
     * textbox uzerinde bir kere backspace tusuna basar
     *
     * @param key
     */
    @Step({"Send BACKSPACE key to element <key>",
            "Elemente BACKSPACE keyi yolla <key>"})
    public void sendKeyToElementBACKSPACE(String key) {
        findElement(key).sendKeys(Keys.BACK_SPACE);
        logger.info(key + " elementine BACKSPACE keyi yollandı.");
        Gauge.captureScreenshot();
    }

    /**
     * textbox uzerinde bir kere backspace tusuna basar selenium actions ile
     *
     * @param key
     */
    @Step({"Clear text of element <key> with BACKSPACE",
            "<key> elementinin text alanını BACKSPACE ile temizle"})
    public void clearInputAreaWithBackspace(String key) {
        WebElement element = findElement(key);
        JavascriptExecutor jsExec = (JavascriptExecutor) DriverFactory.getDriver();
        jsExec.executeScript("arguments[0].style.border='3px solid red'", element);
        element.clear();
        element.sendKeys("a");
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.sendKeys(Keys.BACK_SPACE).build().perform();
        Gauge.captureScreenshot();
    }

    /**
     * elementin text degeri belirtilen texte birebir eşit mi kontrol edilir degilse fail verilir
     *
     * @param key
     * @param expectedText
     */
    @Step({"Check if element <key> contains text <expectedText>",
            "<key> elementi <text> değerini içeriyor mu kontrol et"})
    public void checkElementContainsText(String key, String expectedText) {
        Assert.assertEquals(expectedText, findElement(key).getText());
        Gauge.captureScreenshot();

    }

    /**
     * Textboxa 15 harfli Rastgele bir text yazilir
     *
     * @param key
     * @param startingText
     */
    @Step({"Write random value to element <key> starting with <text>",
            "<key> elementine <text> degeri ile baslayan random deger yaz"})
    public void writeRandomValueToElement(String key, String startingText) {
        String randomText = startingText + randomString(15);
        findElement(key).sendKeys(randomText);
        Gauge.captureScreenshot();
    }

    /**
     * Sayfa Yenilenir
     */
    @Step({"Refresh page",
            "Sayfayı yenile"})
    public void refreshPage() {
        DriverFactory.getDriver().navigate().refresh();
    }



    /**
     * Tarayıcı uyarısı popupında tamam tusuna tiklanir
     */
    @Step({"Accept Chrome alert popup",
            "Chrome uyarı popup'ını kabul et"})
    public void acceptChromeAlertPopup() {
        DriverFactory.getDriver().switchTo().alert().accept();
    }




    @Step("<url> sayfasına git")
    public void implementation1(String url) {
        DriverFactory.getDriver().get(url);

    }

    private void createMenu(String navItem) {
    }

    @Step("jjjj")
    public void implementation2() {
        System.out.println("fghjkl");

    }

    @Step("click on created xpath that start with <first> contain <body> end with<last>")
    public void implementation3(String first, String body, String last) {
        findElementByXpath(first + body + last).click();
        logger.info(body + "'a tıklandı.");

    }

    @Step("wait url changed to<expectedUrl>")
    public void waitUntilUrlChanged(String expectedUrl) {

        System.out.println("gh" + actualUrl);
        waitToGoExpectedUrl(expectedUrl);
        waitForPageLoadComplete();
        Assert.assertNotEquals(expectedUrl, actualUrl);

        logger.info(expectedUrl + "sayfasına gidildi ");


    }

    @Step("wait for page load complete")
    public void waitForPageLoadComplete() {

        driverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

    }


    private void waitToGoExpectedUrl(String url) {
        driverWait.until(ExpectedConditions.urlToBe(url));
    }

    protected String getProjectPath() {
        return System.getProperty("user.dir");
    }

    @Step("uplaod photo <photo>")
    public void uploadPhotos(String photos) {
        WebElement element = findElement("fileInput");
        LocalFileDetector detector = new LocalFileDetector();
        File file = new File(getProjectPath() + "/src/test/resources/photos");
        ((RemoteWebElement) element).setFileDetector(detector);
        element.sendKeys(file.getAbsolutePath() + photos);
        waitByMilliSeconds(2000);
    }

    private WebElement scrollTo(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        if (element != null) {
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3);", element);
        }
        return element;
    }

    @Step("scroll to click on created xpath that start with <first> contain <body> end with<last>")
    public void implementati0on3(String first, String body, String last) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement element = findElementByXpath(first + body + last);
        if (element != null) {
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3);", element);
        }
        element.click();
    }


    @Step("hhhh <first>")
    public void implemejjntati0onkk(String first) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement element = findElementByXpath(first);
        Point p = element.getLocation();
        int X = p.getX();
        int Y = p.getY();
        js.executeScript("window.scrollBy(" + X + ", " + Y + ");");
        js.executeScript("arguments[0].click();", element);


    }


    @Step("create xpath <xpath> and write <text>")
    public void implementation4(String xpath, String text) {

        WebElement element = findElementByXpath(xpath);
        element.sendKeys(text);
    }

    @Step("write <text> in <label> input field")
    public void implementjjjation5(String text, String label) {
        WebElement element = findElementByXpath("//div[p[contains(text(),'" + label + "')]]//input");

        element.sendKeys(text);
    }


    @Step("click on select input that with <label>")
    public void implementajtions5(String label) {
         String content ="//p[contains(text(),'" + label + "')]/following-sibling::mat-form-field//span[contains(text(),'Seçiniz')]";

        WebElement element = findElementByXpath(content);
        element.click();

    }


    @Step("click on option that <value>")
    public void implementationhjs5(String value) {
        WebElement element = findElementByXpath("//div/mat-option/span[contains(text(),'" + value + "')]");
        element.click();

    }

    @Step("click via xpath selector <xpath>")
    public void implementatidonshjs5(String xpath) {
        WebElement element = findElementByXpath(xpath);
        element.click();

    }

}