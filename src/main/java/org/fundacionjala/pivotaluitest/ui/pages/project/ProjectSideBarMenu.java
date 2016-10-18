package org.fundacionjala.pivotaluitest.ui.pages.project;

import org.fundacionjala.pivotaluitest.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotaluitest.ui.pages.common.CommonActions;
import org.fundacionjala.pivotaluitest.ui.pages.story.StoryForm;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by erickaviraca on 10/17/2016.
 */
public class ProjectSideBarMenu extends AbstractBasePage {
    @FindBy(css = ".button.add_story")
    private WebElement addStory;

    /**
     * The method makes click on the save button.
     *
     * @return a StoryForm.
     */
    public final StoryForm clickAddStory() {
        CommonActions.clickElement(addStory);
        return new StoryForm();
    }

}
