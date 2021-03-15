package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pom.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pom.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pom.SignupPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyStepdefs extends SpringTest {

  {
    WebDriverManager.chromedriver().setup();

  }

  @Before
  public void createDriver() {
    if (this.driver == null) {
      driver = new ChromeDriver();
    }
  }




  @After
  public void closeDriver() {
    if (this.driver != null) {
      driver.quit();
    }
  }




  @Given("new user signs up with login {string} and password {string}")
  public void newUserSignsUpWithLoginAndPassword(String username, String password) {


    driver.get(BASE_URL + port + "/signup");

    String firstName = "Jeff";
    String lastName = "Brown";


    SignupPage signupPage = new SignupPage(driver);


    signupPage.registerUser(firstName, lastName, username, password);


  }

  @When("user logs in with login {string} and password {string}")
  public void userLogsInWithLoginAndPassword(String login, String password) {

    driver.get(BASE_URL + port + "/login");

    LoginPage loginPage = new LoginPage(driver);

    loginPage.loginUser(login, password);


  }

  @Then("home page is accessible")
  public void homePageIsAccessible() throws InterruptedException {
    Thread.sleep(2000);
    assertEquals("Home", driver.getTitle());


  }

  @When("user logs out")
  public void userLogsOut() throws InterruptedException {
    HomePage homePage = new HomePage(driver);

    driver.get(BASE_URL + port + "/home");

    homePage.logout();

    Thread.sleep(2000);


  }

  @Then("home page is not accessible")
  public void homePageIsNotAccessible() throws InterruptedException {

    Thread.sleep(2000);

    assertEquals("Login", driver.getTitle());


  }



  @When("user navigates to home page")
  public void userNavigatesToHomePage() {

    driver.get(BASE_URL + port + "/home");
  }

  @When("user adds a note with title {string}")
  public void userAddsANoteWithTitle(String title) {
    HomePage homePage = new HomePage(driver);

    homePage.addNote(title, "description lol");
  }

  @Then("note {string} is visible")
  public void noteIsVisible(String title) throws InterruptedException {
    HomePage homePage = new HomePage(driver);
    Thread.sleep(2000);

    homePage.clickNotesTab();

    Thread.sleep(2000);

    assertEquals(title, homePage.getNoteTitle());
  }

  @When("user changes the content and the title to {string}")
  public void userChangesTheContentAndTheTitleTo(String newTitle) throws InterruptedException {
    HomePage homePage = new HomePage(driver);
    Thread.sleep(2000);
    homePage.clickNotesTab();
    Thread.sleep(3000);
    homePage.editNote(newTitle);
  }



  @When("user deletes the note")
  public void userDeletesTheNote() throws InterruptedException {
    HomePage homePage = new HomePage(driver);
    Thread.sleep(2000);
    homePage.clickNotesTab();
    Thread.sleep(3000);
    homePage.deleteNote();
  }

  @Then("note does not exist")
  public void noteDoesNotExist() throws InterruptedException {

    HomePage homePage = new HomePage(driver);

    Thread.sleep(2000);

    homePage.clickNotesTab();

    Thread.sleep(2000);

    NoSuchElementException e = assertThrows(
            NoSuchElementException.class,
            () -> homePage.getNoteTitle(),
            "Expected an exception as element should not be found"
    );
  }

  @When("user adds credentials for url {string}")
  public void userAddsCredentialsForUrl(String url) throws InterruptedException {

    HomePage homePage = new HomePage(driver);

    Thread.sleep(2000);

    homePage.clickCredentialsTab();

    Thread.sleep(2000);

    homePage.addCredentials(url, "sajhsd", "sps@£@");



  }

  @Then("credentials for {string} url are visible")
  public void credentialsForUrlAreVisible(String arg0) throws InterruptedException {

    HomePage homePage = new HomePage(driver);

    Thread.sleep(2000);

    homePage.clickCredentialsTab();

    Thread.sleep(2000);

    assertEquals(arg0, homePage.getCredentialsUrl());
  }

  @When("user changes the url to {string}")
  public void userChangesTheUrlTo(String url) throws InterruptedException {

    HomePage homePage = new HomePage(driver);

    Thread.sleep(2000);

    homePage.clickCredentialsTab();

    Thread.sleep(2000);

    homePage.editCredentialsChangeUrl(url);
  }

  @When("user deletes the credentials")
  public void userDeletesTheCredentials() throws InterruptedException {

    HomePage homePage = new HomePage(driver);

    Thread.sleep(2000);

    homePage.clickCredentialsTab();

    Thread.sleep(2000);

    homePage.deleteCredentials();
  }

  @Then("credentials do not exist")
  public void credentialsDoNotExist() throws InterruptedException {
    HomePage homePage = new HomePage(driver);

    Thread.sleep(2000);

    homePage.clickCredentialsTab();


    Thread.sleep(2000);

    NoSuchElementException e = assertThrows(
            NoSuchElementException.class,
            () -> homePage.getCredentialsUrl(),
            "Expected an exception as element should not be found"
    );
  }
}
