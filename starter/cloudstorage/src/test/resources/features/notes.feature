Feature: Notes

  Background: User exists
    Given new user signs up with login "pam" and password "pass"

  Scenario: User can add, edit and delete notes which are persisted

    Given user logs in with login "pam" and password "pass"
    When user adds a note with title "myFirstNote"
    Then note "myFirstNote" is visible

    Then user logs out

    Given user logs in with login "pam" and password "pass"
    When user changes the content and the title to "editedNote"
    Then note "editedNote" is visible

    Then user logs out

    Given user logs in with login "pam" and password "pass"
    When user deletes the note
    Then note does not exist






