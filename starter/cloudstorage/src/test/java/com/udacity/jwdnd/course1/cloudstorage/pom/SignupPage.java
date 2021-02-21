package com.udacity.jwdnd.course1.cloudstorage.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

  @FindBy(id="inputFirstName")
  private WebElement firstNameInput;

  @FindBy(id="inputLastName")
  private WebElement lastNameInput;

  @FindBy(id="inputUsername")
  private WebElement usernameInput;

  @FindBy(id="inputPassword")
  private WebElement passwordInput;

  @FindBy(id="submitButton")
  private WebElement submitButton;

  public SignupPage(WebDriver driver) {

    PageFactory.initElements(driver, this);
  }

  public void registerUser(String first, String last, String username, String password) {
    firstNameInput.sendKeys(first);
    lastNameInput.sendKeys(last);
    usernameInput.sendKeys(username);
    passwordInput.sendKeys(password);
    submitButton.click();
  }
}
