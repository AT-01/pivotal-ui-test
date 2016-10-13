package org.fundacionjala.pivotaluitest.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.pivotaluitest.ui.utils.WaitElement;

/**
 * this Class is for login on PivotalTracker.
 */
public class SignInForm extends AbstractBasePage {

    @FindBy(id = "credentials_username")
    private WebElement emailTextField;

    @FindBy(id = "credentials_password")
    private WebElement passwordTextField;

    @FindBy(name = "action")
    private WebElement nextSigninButton;

    /**
     * This method set the username in the text field.
     *
     * @param email String whit the email.
     */
    public void setEmailTextField(final String email) {
        WaitElement.waitClear(emailTextField);
        WaitElement.waitSendKeys(emailTextField, email);
    }

    /**
     * This method set the password in the text field.
     *
     * @param password String  whit the password.
     */
    public void setPasswordTextField(final String password) {
        WaitElement.waitClear(passwordTextField);
        WaitElement.waitSendKeys(passwordTextField, password);
    }

    /**
     * Method to click on next button.
     */
    public void clickNextButton() {
        WaitElement.waitClick(nextSigninButton);
    }

    /**
     * This method sigin the application.
     *
     * @return Return to home page the PivotalTracker.
     */
    public Home clickSigninButton() {
        WaitElement.waitClick(nextSigninButton);
        return new Home();
    }

    /**
     * Method to perform a login as Primary user.
     *
     * @param userName Username used to perform a login to Mach2 application.
     * @param password Password used to perform a login to Mach2 application.
     * @return The login to Mach2 application.
     */
    public static Home loginAs(final String userName, final String password) {
        Welcome welcomePage = new Welcome();
        SignInForm signInForm = welcomePage.clickSigninLink();
        signInForm.setEmailTextField(userName);
        signInForm.clickNextButton();
        signInForm.setPasswordTextField(password);
        return signInForm.clickSigninButton();
    }
}
