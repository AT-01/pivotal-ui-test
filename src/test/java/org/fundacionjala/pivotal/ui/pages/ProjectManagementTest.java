package org.fundacionjala.pivotal.ui.pages;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import org.fundacionjala.pivotal.ui.pages.common.CommonMethods;
import org.fundacionjala.pivotal.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting;
import org.fundacionjala.pivotal.ui.pages.project.ProjectManagement;

import static org.fundacionjala.pivotal.ui.pages.common.CommonNavigator.goToDashboard;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.ACCOUNT;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_NAME;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_PRIVACY;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Bruno Barrios on 4/12/2017.
 */
public class ProjectManagementTest {

    public static final String COCOBONGO = "COCOBONGO";
    public static final String PUBLIC = "Public";

    /**
     * This method will test the creation of a project.
     */
    @Test
    public void createProject() {
        Dashboard dashboard = new Dashboard();
        ProjectForm projectForm = dashboard.clickCreateProjectButton();

        Map<ProjectFormSetting, String> newPro = new HashMap<>();
        final String testeandOsd = "TESTEANDOsd";
        newPro.put(PROJECT_NAME, testeandOsd);
        newPro.put(ACCOUNT, COCOBONGO);
        newPro.put(PROJECT_PRIVACY, PUBLIC);

        projectForm.setConfiguration(newPro);
        ProjectManagement projectManagement = projectForm.clickCreateProjectButton();

        assertEquals(testeandOsd, projectManagement.getProjectName());
    }

    /**
     * This will clean everything created in this test.
     */
    @AfterClass
    public void teardown() {
        CommonMethods.deleteAllProjects();
        goToDashboard();
    }
}
