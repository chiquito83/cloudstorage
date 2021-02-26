package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pom.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pom.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pom.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private String BASE_URL= "http://localhost:";


	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();

	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get(BASE_URL + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	//Write a Selenium test that verifies that the home page is not accessible without logging in.
	@Test
	public void homePageNotAccessibleWithoutLogin() {
		driver.get(BASE_URL + port + "/home");

		assertNotEquals("Home", driver.getTitle());
		assertEquals("Login", driver.getTitle());

	}

	// Write a Selenium test that signs up a new user, logs that user in,
	// verifies that they can access the home page,
	// then logs out and verifies that the home page is no longer accessible.
	@Test
	public void signupLoginAndLogout() throws InterruptedException {

		driver.get(BASE_URL + port + "/signup");

		String firstName = "Jeff";
		String lastName = "Brown";
		String username = "seleniumUser";
		String password = "seleniumPass";

		SignupPage signupPage = new SignupPage(driver);


		signupPage.registerUser(firstName, lastName, username, password);

		driver.get(BASE_URL + port + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginUser(username, password);

		assertEquals("Home", driver.getTitle());

		HomePage homePage = new HomePage(driver);

		Thread.sleep(2000);

		homePage.logout();

		Thread.sleep(2000);

		assertEquals("Login", driver.getTitle());



	}

	// Write a Selenium test that logs in an existing user,
	// creates a note and verifies that the note details are visible in the note list.
	@Test
	public void loginCreateNote() throws InterruptedException {


		driver.get(BASE_URL + port + "/signup");

		String firstName = "Tom";
		String lastName = "Cruise";
		String username = "seleniumUser2";
		String password = "seleniumPass2";

		SignupPage signupPage = new SignupPage(driver);


		signupPage.registerUser(firstName, lastName, username, password);

		driver.get(BASE_URL + port + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginUser(username, password);

		HomePage homePage = new HomePage(driver);

		String title = "Some Note";
		String description = "Description";

		homePage.addNote(title, description);


		Thread.sleep(2000);

		homePage.clickNotesTab();

		Thread.sleep(2000);

		assertEquals(title, homePage.getNoteTitle());

	}


	// Write a Selenium test that logs in an existing user with existing notes,
	// clicks the edit note button on an existing note, changes the note data, saves the changes,
	// and verifies that the changes appear in the note list.
	@Test
	public void loginAndEditNotes() throws InterruptedException {

		driver.get(BASE_URL + port + "/signup");

		String firstName = "Aaaaa";
		String lastName = "Bbbbb";
		String username = "cccc";
		String password = "ddddd";

		SignupPage signupPage = new SignupPage(driver);


		signupPage.registerUser(firstName, lastName, username, password);

		driver.get(BASE_URL + port + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginUser(username, password);

		HomePage homePage = new HomePage(driver); //todo

		String title = "Note Title";
		String description = "Description";


		Thread.sleep(3000);


		homePage.addNote(title, description);

		Thread.sleep(3000);


		homePage.clickNotesTab();

		Thread.sleep(3000);

		assertEquals(title, homePage.getNoteTitle());



		homePage.clickNotesTab();

		Thread.sleep(3000);

		homePage.editNote("new title");


		Thread.sleep(2000);


		homePage.clickNotesTab();

		Thread.sleep(2000);

		assertEquals("new title", homePage.getNoteTitle());

	}

	// Write a Selenium test that logs in an existing user with existing notes,
	// clicks the delete note button on an existing note,
	// and verifies that the note no longer appears in the note list.
	@Test
	public void loginAndDeleteNotes() throws InterruptedException {

		driver.get(BASE_URL + port + "/signup");

		String firstName = "Donald";
		String lastName = "Duck";
		String username = "seleniumUser3";
		String password = "seleniumPass3";

		SignupPage signupPage = new SignupPage(driver);


		signupPage.registerUser(firstName, lastName, username, password);

		driver.get(BASE_URL + port + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginUser(username, password);

		HomePage homePage = new HomePage(driver);

		String title = "Original Note";
		String description = "Original Description";

		homePage.addNote(title, description);

		Thread.sleep(2000);


		homePage.clickNotesTab();

		Thread.sleep(2000);

		assertEquals(title, homePage.getNoteTitle());


		homePage.deleteNote();

		Thread.sleep(2000);

		homePage.clickNotesTab();

		Thread.sleep(2000);

		NoSuchElementException e = assertThrows(
						NoSuchElementException.class,
						() -> homePage.getNoteTitle(),
						"Expected an exception as element should not be found"
		);



	}

	// Write a Selenium test that logs in an existing user,
	// creates a credential and verifies that
	// the credential details are visible in the credential list.
	@Test
	public void loginExistingCreateCredential() throws InterruptedException {


		driver.get(BASE_URL + port + "/signup");

		String firstName = "John";
		String lastName = "Smith";
		String username = "john";
		String password = "mypass";

		SignupPage signupPage = new SignupPage(driver);


		signupPage.registerUser(firstName, lastName, username, password);

		driver.get(BASE_URL + port + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginUser(username, password);

		HomePage homePage = new HomePage(driver);

		String url = "udacity.com";
		String credUsername = "USER";
		String credPass = "PASS";


		Thread.sleep(3000);


		homePage.addCredentials(url, credUsername, credPass);

		Thread.sleep(3000);


		homePage.clickCredentialsTab();

		Thread.sleep(3000);

		assertEquals(url, homePage.getCredentialsUrl());
	}

	// Write a Selenium test that logs in an existing user with existing credentials,
	// clicks the edit credential button on an existing credential, changes the credential data,
	// saves the changes, and verifies that the changes appear in the credential list.
	@Test
	public void loginAndEditCredential() throws InterruptedException {

		driver.get(BASE_URL + port + "/signup");

		String firstName = "Aaaaa";
		String lastName = "Bbbbb";
		String username = "cccc";
		String password = "ddddd";

		SignupPage signupPage = new SignupPage(driver);


		signupPage.registerUser(firstName, lastName, username, password);

		driver.get(BASE_URL + port + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginUser(username, password);

		HomePage homePage = new HomePage(driver); //todo

		String url = "zzzz.com";
		String credUsername = "ddddd";
		String credPass = "ssss123";


		Thread.sleep(3000);


		homePage.addCredentials(url, credUsername, credPass);

		Thread.sleep(3000);


		homePage.clickCredentialsTab();

		Thread.sleep(3000);

		assertEquals(url, homePage.getCredentialsUrl());



		homePage.clickCredentialsTab();

		Thread.sleep(3000);



		homePage.editCredentialsChangeUrl("newurl.com");

		Thread.sleep(2000);

		homePage.clickCredentialsTab();

		Thread.sleep(2000);

		assertEquals("newurl.com", homePage.getCredentialsUrl());



	}

	// Write a Selenium test that logs in an existing user with existing credentials,
	// clicks the delete credential button on an existing credential,
	// and verifies that the credential no longer appears in the credential list.
	@Test
	public void loginAndDeleteCredential() throws InterruptedException {
		driver.get(BASE_URL + port + "/signup");

		String firstName = "Walter";
		String lastName = "White";
		String username = "heisenberg";
		String password = "planck";

		SignupPage signupPage = new SignupPage(driver);


		signupPage.registerUser(firstName, lastName, username, password);

		driver.get(BASE_URL + port + "/login");

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginUser(username, password);

		HomePage homePage = new HomePage(driver);



		String url = "howtocook.com";
		String usr = "wwhite";
		String pass = "yoyo";



		homePage.addCredentials(url, usr, pass);

		Thread.sleep(2000);


		homePage.clickCredentialsTab();

		Thread.sleep(2000);



		assertEquals(url, homePage.getCredentialsUrl());


		homePage.deleteCredentials();

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
