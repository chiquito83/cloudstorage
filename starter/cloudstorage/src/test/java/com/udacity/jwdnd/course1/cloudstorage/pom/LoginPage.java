package com.udacity.jwdnd.course1.cloudstorage.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

  @FindBy(id="inputUsername")
  private WebElement usernameInputField;

  @FindBy(id="inputPassword")
  private WebElement passwordInputField;

  @FindBy(id="submitButton")
  private WebElement submitButton;

  public LoginPage(WebDriver driver) {

    PageFactory.initElements(driver, this);
  }

  public void loginUser(String username, String password) {
    usernameInputField.sendKeys(username);
    passwordInputField.sendKeys(password);
    submitButton.click();
  }
}
