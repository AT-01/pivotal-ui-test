package org.fundacionjala.pivotal.ui.pages.workspace;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.api.RequestManager;
import org.fundacionjala.pivotal.ui.browser.DriverManager;
import org.fundacionjala.pivotal.ui.pages.Dashboard;
import org.fundacionjala.pivotal.ui.pages.SignInForm;
import org.fundacionjala.pivotal.ui.pages.common.CommonNavigator;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterSuite;
import static org.fundacionjala.pivotal.utils.Constants.DELETE_MESSAGE;
import static org.fundacionjala.pivotal.utils.Constants.SAVED_MESSAGE;

/**
 * Created by Daniel Jauregui on 4/7/2017.
 */
public class WorkSpacesTests {

    /**
     *
     */
    @BeforeSuite
    public void initWebDriver() {

    }

    /**
     *
     */
    @BeforeClass
    public void beforeClass() {
        SignInForm.loginAs("selenium.daniel.1@yopmail.com", "daniel.123");
        CommonNavigator.goToDashboard();
    }

    /**
     * testCreateNewWorkSpace.
     */
    @Test
    public void testCreateNewWorkSpace() {

        final String nameOfWorkSpace = "Test_Auto_WorkSpace";
        Dashboard d = new Dashboard();
        d.clickInWorkSpacesTab();
        CreateWorkspace cw = d.clickCreateWorkspaceLink();
        cw.setWorkspaceName(nameOfWorkSpace);
        Workspace w = cw.clickCreateWorkspaceLink();
        Assert.assertEquals(nameOfWorkSpace, w.getWorkspaceNameText());
    }

    /**
     * testEditWorkSpace.
     */
    @Test
    public void testEditWorkSpace() {
        RequestManager.post("/my/workspaces", "{\"name\":\"Test_Auto_WorkSpace\"}");
        CommonNavigator.goToDashboard();
        final String nameOfWorkSpace = "Test_Auto_WorkSpace";
        final String nameOfWorkSpaceUpdated = "TestAutoWorkSpaceEdited";
        Dashboard d = new Dashboard();
        d.clickInWorkSpacesTab();
        SettingWorkspace sw = d.clickConfigIconWorkSpace(nameOfWorkSpace);
        sw.setNameWorkspaceTestField(nameOfWorkSpaceUpdated);
        sw.clickSaveChangesWorkspaceLink();
        Assert.assertEquals(SAVED_MESSAGE, sw.getMessageChangesWorkspace());
    }

    /**
     * testDeleteWorkSpace.
     */
    @Test
    public void testDeleteWorkSpace() {
        RequestManager.post("/my/workspaces", "{\"name\":\"TestAutoWorkSpaceEdited\"}");
        CommonNavigator.goToDashboard();
        final String nameOfWorkSpaceUpdated = "TestAutoWorkSpaceEdited";
        Dashboard d = new Dashboard();
        d.clickInWorkSpacesTab();
        SettingWorkspace sw = d.clickConfigIconWorkSpace(nameOfWorkSpaceUpdated);
        DeleteWorkspace dw = sw.clickDeleteWorkspaceLink();
        dw.clickConfirmDeleteLink();
        Assert.assertEquals(nameOfWorkSpaceUpdated + DELETE_MESSAGE, d.getMessageDeleteWorkspace());
    }

    /**
     * removeWorkspaces.
     */
    @AfterTest
    public void removeWorkspaces() {
        Response response = RequestManager.get("/my/workspaces");
        JsonPath jsonPath = response.getBody().jsonPath();
        String id = jsonPath.getString("id").replace("[", "").replace("]", "");
        RequestManager.delete("/my/workspaces/" + id);
    }

    /**
     * removeDriver.
     */
    @AfterSuite
    public void removeDriver() {
        DriverManager.getInstance().getDriver().close();
    }

}
