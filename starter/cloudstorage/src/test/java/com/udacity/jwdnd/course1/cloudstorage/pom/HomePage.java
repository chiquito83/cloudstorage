package com.udacity.jwdnd.course1.cloudstorage.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

  private JavascriptExecutor js;
  private WebDriverWait wait;

  @FindBy(id="logoutButton")
  private WebElement logoutButton;

  @FindBy(id="nav-notes-tab")
  private WebElement notesTab;

  @FindBy(id="nav-credentials-tab")
  private WebElement credentialsTab;

  @FindBy(id="nav-files-tab")
  private WebElement fileTab;

  //notes

  @FindBy(id="addNoteButton")
  private WebElement addNoteButton;

  @FindBy(id="note-title")
  private WebElement noteTitleInput;

  @FindBy(id="note-description")
  private WebElement noteDescriptionInput;

  @FindBy(id="noteSubmit")
  private WebElement submitNoteButton;

  @FindBy(id="noteTitle")
  private WebElement noteTitleElement;

  @FindBy(linkText = "Delete")
  private WebElement deleteNoteButton;

  @FindBy(className = "btn-success")
  private WebElement editNoteButton;

  // credentials

  @FindBy(id="addCredentialButton")
  private WebElement addCredentialsButton;

  @FindBy(id="credential-url")
  private WebElement credentialsInputUrl;

  @FindBy(id="credential-username")
  private WebElement credentialsInputUsername;

  @FindBy(id="credential-password")
  private WebElement credentialsInputPassword;

  @FindBy(id="credUrl")
  private WebElement credentialsUrlElement;

  @FindBy(id="credentialSubmit")
  private WebElement submitCredentialsButton;

  @FindBy(linkText = "Delete")
  private WebElement deleteCredentialsButton;

  @FindBy(className = "btn-success")
  private WebElement editCredentialsButton;



  public HomePage(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
    wait = new WebDriverWait(webDriver, 4, 2000);
    js = (JavascriptExecutor) webDriver;
  }

  public void addCredentials(String url, String username, String password) {
    js.executeScript("arguments[0].click();", addCredentialsButton);
    js.executeScript("arguments[0].value='" + url + "';", credentialsInputUrl);
    js.executeScript("arguments[0].value='" + username + "';", credentialsInputUsername);
    js.executeScript("arguments[0].value='" + password + "';", credentialsInputPassword);
    js.executeScript("arguments[0].click();", submitCredentialsButton);

  }

  public void editCredentialsChangeUrl(String url) {
    js.executeScript("arguments[0].click();", editCredentialsButton);
    js.executeScript("arguments[0].value='" + url + "';", credentialsInputUrl);
    js.executeScript("arguments[0].click();", submitCredentialsButton);
  }

  public void editNote(String title) {
    js.executeScript("arguments[0].click();", editNoteButton);
    js.executeScript("arguments[0].value='" + title + "';", noteTitleInput );
    js.executeScript("arguments[0].click();", submitNoteButton);

  }


  public void addNote(String title, String description) {


    js.executeScript("arguments[0].click();", addNoteButton);
    js.executeScript("arguments[0].value='" + title + "';", noteTitleInput);
    js.executeScript("arguments[0].value='" + description + "';", noteDescriptionInput);
    js.executeScript("arguments[0].click();", submitNoteButton);

  }

  public void deleteNote() {
    deleteNoteButton.click();


  }

  public void deleteCredentials() {
    deleteCredentialsButton.click();
  }

  public void clickNotesTab() {
    notesTab.click();
  }

  public void clickCredentialsTab() {
    credentialsTab.click();
  }

  public String getNoteTitle() {
    return noteTitleElement.getText();
  }

  public String getCredentialsUrl() {
    return credentialsUrlElement.getText();
  }

  public void logout() {
    logoutButton.click();
  }




}
