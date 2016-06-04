package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuCommands("gziTnEKQVF3zUfdM9ShV4nqDmS5WNaKl3SMqLSvEaK6u9SLVnz");
        appmenus.setAutoSave(true);
        appmenus.setMenuIcon("60gVmBV23GtOx2qjCzmsqYPQCEN2TDbtJ5lrbaaRaY7vmuGxig");
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuAccessRights(8);
        appmenus.setRefObjectId("61axec0pE0B7y15xdAroMkWXbEdz7h2dLMFYXhMO4uliZBNKUE");
        appmenus.setMenuAction("t8aB4pHnbyRQnccHuh59qz6LskTNlU5jIEjW3srzrp9RTTLARZ");
        appmenus.setMenuLabel("lDUjHDYh62URD1lFkAhpOOh5ZwuTapEkUp4dSVwXkBwYTVH83U");
        appmenus.setMenuTreeId("Kvt7Q7dAQbaAWLRMynSNluKhBYNU4VTNZCjiOH1v72RCGxkoMj");
        appmenus.setAppId("6msQFHShqaVrkWgoUazX78benzyuKQ71r7BWOa1XCUlAp05oEI");
        appmenus.setUiType("4Yo");
        appmenus.setAppType(1);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuCommands("j3eoOymZgxu7ov845rvSStzxyAMN2eH8TnJQTGqsH8bYbzD73f");
            appmenus.setMenuIcon("Iy44rEARLEObLXRvsEdbjQnvKzq223L2KgkWCzeihk0fYS4jG9");
            appmenus.setMenuAccessRights(1);
            appmenus.setRefObjectId("cQTbL1lAjHBNLR5XPwW5pes2UoM8ztUpCDVZy2xIEBXtMeLNBp");
            appmenus.setMenuAction("ETwb9CYfWlhmHGb6mqpx0BzxdDmyFGx2bxKZv4wvX8TMmglC8Y");
            appmenus.setMenuLabel("7YGR7lTzG3cC1uLCBMAq1qLMaBSTClxu7mmFxuJoWB88pZTpkG");
            appmenus.setMenuTreeId("awqm4Af3vCoxibcLjpTT4jjGDQTChuMYsh7fYK3W2wFBo2dwNz");
            appmenus.setAppId("oGdz2SCuUmAYke2DNo7cripcHJffRa21NAsuqQirQSNc58REGU");
            appmenus.setVersionId(1);
            appmenus.setUiType("XqU");
            appmenus.setAppType(1);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "C2YdCu7oHDrMJbnHAXzYFAoYKDn1fTX3c28iWTRx3IQQH1pFGr6WxGtZu4K0pbfSOcWCYNEAd0NtyPKtGY12tr0OmnMQnMy6z2nEB9kUQW0TEtjW4JwVjPYMHyNzZr9FKA0HHjpzCVVyKw1lRf5jrjhqbK1BMsDKnfE9tO5uASQlChojbk7dLgheEedwVi7XVBH93slqQQNSdovk4q8rvByoAoZAWKiTmuzfLlbM7aH04drSa5GWqUhRbirtEKGJO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "v0t1ktkvXI3IRLcxiNusLOScIRps0saerbnrK8S9KiyqBxqBAE9qtnaTP5uDZn3OYeVgbpJ05gpURpPjNGtTVzRkoSy28NVjuhbgCQe0dIBzaZvvMM6uqdK1x1j459FsfLB8GoC1PryON6x9JUeOIUeYmJdi4VKcOUb7h2mcipwGgeTcmJ7NrCoTf016aA7key1cqzsFrOBaZlYxYUVSUyin9uHnsrDI5FdWZwuQ3xWhpQayV5u4h3zKf5M8ZKLpj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "Il9U1XxvHndLWza6oHJvbNqLEAtpTZJPPbaRlW2kXnrJm4n775adj5meWChyMW22arWoaWdZyU0KUqncAG9jgUlC6CwdnEMUYszX5lKqVlMtjgHYoZfWLZd54HDT6UIxPsWjVWgyvCBE4zdIKmciwfjysgCUJC2rycHt0CLNyjgL9rc4EyoUfA9HMzVgY39dd3VlxW5RCA1ZPMb4ghO55Ce1LWPb3Iss8bwFBOx5SVxtrTid6QKl2aKEVVqr7qA4d"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "9tHzcUJQIP72tB5YTPKWq0S4oGumKa1k4lxfJWwXlV4q3XTzeLlzqNKvPmbOd2ywY"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "O5APWh1q62irSHL9oXbHvzwK532OZys5JOTYaWW1DZkG6jkCIEuTUTTSd9jmdL0T7RYMNrXHzdjG9INt0fghgCjamWZQk9Io3ACrJieT9qgILeLya2sMpJycxRXlQu4o074WXCVHII1sqoPXrKnP42EtyJA6WL6NAKc9szOEtGC1prfySsmahxYVJ0NM47fgYjpYwO7GId3qIl4YIkS1YTYvXRVk6spo1g6O1KtoQGaqnsrNBFvZ7lmjahmiO81hy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "Qx18"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "mhwErZGMEq62rbfBROKJyKrycNAotpE03r5vyOLbujlSuacWLbwwgyfVInH8kZhxyt9IUpal76biv2d047foNzWdmU3NCLLsPdyN04mAxVi9NsLlxqfivml6fFCFfuQFsZ0F8pKQWNB6xlzjCPYkk6GkERN9mVTpaWnZOB0z4kV2p1tL5kkzlJrLtPSWCz5wYWHqt9Bp23kXr1nYyj9EkUDJusDkBZRx2DwR0XX4pe78ujmuGrpocEbSKdG6h7kR0"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "uykVi7nVD10bCKCkqsaWNIBm57VBZR04lHWvizEHNcxRpYvAGGXKff4UNaI18D2RRHkBw6Rve0sW5WRWMBCnrtrQDVCipngOaXfPlouvsdPYFPrsizDDJXZTFWCXPkiRGX6LAgNYBZMCXzdDwtnUddXj9ND8sjAMk87iRyhut3OsBz8UU5XwpXpwHE9SP1Muho51D74QPQ0BAmXNTcpPpUIx9NvfdQa7U2lcDEio26CMThr4E1uBDqoGN7wUULwbg"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
