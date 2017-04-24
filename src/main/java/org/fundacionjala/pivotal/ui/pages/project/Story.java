package org.fundacionjala.pivotal.ui.pages.project;

import org.fundacionjala.pivotal.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.ui.pages.common.CommonActions.clickElement;

/**
 * Created by Bruno Barrios on 4/12/2017.
 */
public class Story extends AbstractBasePage {
    @FindBy(css = ".add_story span span")
    private WebElement addStroyBtn;

    @FindBy(name = "story[name]")
    private WebElement storyname;

    @FindBy(css = ".autosaves.button.std.save")
    private WebElement saveStory;

    /**
     * This method will click add story button.
     */
    public void clickAddStoryButton() {
        clickElement(addStroyBtn);
    }

    /**
     * This method will set story title.
     *
     * @param storyTitle to be set.
     */
    public void setStoryTitle(String storyTitle) {
        CommonActions.clickElement(storyname);
        CommonActions.sendKeys(storyname, storyTitle);
    }

    /**
     * This method will click on save button.
     */
    public void clickSaveStory() {
        CommonActions.clickElement(saveStory);
    }
}
