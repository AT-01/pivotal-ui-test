package org.fundacionjala.pivotaluitest.ui.pages.story;

import org.fundacionjala.pivotaluitest.ui.pages.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by erickaviraca on 10/13/2016.
 */
public class StorySideBarMenu extends AbstractBasePage {

    @FindBy(css = "div[id='view175'] > div[class='tn-panel__loom']")
    private WebElement storyList;

    /**
     * @param storyNameDeleted the name of the story previously deleted.
     *
     * @return a true or false value, depending if the story is found.
     */
    public boolean storyExist(String storyNameDeleted) {
        List<WebElement> storyCreated = storyList.findElements(By.tagName("div"));
        return !storyCreated.isEmpty();
    }
}
