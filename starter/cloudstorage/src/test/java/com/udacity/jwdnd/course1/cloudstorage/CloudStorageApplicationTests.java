package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
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
		driver.get(BASE_URL + this.port + "/home");

		Assertions.assertNotEquals("Home", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());

	}

	// Write a Selenium test that signs up a new user, logs that user in,
	// verifies that they can access the home page,
	// then logs out and verifies that the home page is no longer accessible.
	@Test
	public void signupLoginAndLogout() {

		throw new UnsupportedOperationException();


	}

	// Write a Selenium test that logs in an existing user,
	// creates a note and verifies that the note details are visible in the note list.
	@Test
	public void loginCreateNote() {
		throw new UnsupportedOperationException();
	}


	// Write a Selenium test that logs in an existing user with existing notes,
	// clicks the edit note button on an existing note, changes the note data, saves the changes,
	// and verifies that the changes appear in the note list.
	@Test
	public void loginAndEditNotes() {
		throw new UnsupportedOperationException();
	}

	// Write a Selenium test that logs in an existing user with existing notes,
	// clicks the delete note button on an existing note,
	// and verifies that the note no longer appears in the note list.
	@Test
	public void loginAndDeleteNotes() {
		throw new UnsupportedOperationException();
	}

	// Write a Selenium test that logs in an existing user,
	// creates a credential and verifies that
	// the credential details are visible in the credential list.
	@Test
	public void loginExistingCreateCredential() {
		throw new UnsupportedOperationException();
	}

	// Write a Selenium test that logs in an existing user with existing credentials,
	// clicks the edit credential button on an existing credential, changes the credential data,
	// saves the changes, and verifies that the changes appear in the credential list.
	@Test
	public void loginAndEditCredential() {
		throw new UnsupportedOperationException();
	}

	// Write a Selenium test that logs in an existing user with existing credentials,
	// clicks the delete credential button on an existing credential,
	// and verifies that the credential no longer appears in the credential list.
	@Test
	public void loginAndDeleteCredential() {
		throw new UnsupportedOperationException();
	}






}
