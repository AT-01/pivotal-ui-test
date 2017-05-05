package org.fundacionjala.pivotal.ui.pages;

import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * SideBarBoard class.
 */
public class SideBarBoard extends AbstractBasePage {


    @FindBy(css = ".button.add_story > span")
    private WebElement addStroyBtn;

    /**
     * This method will be used to click on addStroy Button.
     * @return IceBox.
     */
    public IceBox clickAddStory() {
        CommonActions.clickElement(addStroyBtn);
        return new IceBox();
    }
}
