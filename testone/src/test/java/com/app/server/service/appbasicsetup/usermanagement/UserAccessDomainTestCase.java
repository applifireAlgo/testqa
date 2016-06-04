package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("2I4QqS1HiOc5e8GHJlH3MHE4LsLz2Lo6U5GQfkjLYJQ63imHzx");
        useraccessdomain.setDomainIcon("RVxjOQgyGlhwLE0Rthm7c1cAdBv0AherZDKrCvbrxts1Y7C7zc");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("hAHuqtw0KUa3SMSJP9LNN2ciPTd2OkDfZJ4y1viGAMyAEqpO3q");
        useraccessdomain.setDomainHelp("qDwFlmyXrhxyUOJ6nKA2kAY0NvTlxGzI10b7sJ10SHVHgdlg1d");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainDescription("w2vOPl68vtN19lssCY6MoJyRXcFU21jIcTvwMkf3CNy0HBgymL");
            useraccessdomain.setDomainIcon("WsRaobjqwkhUfxc5FQcx4M5NsX1qqBcwTkwaXEnXHhiLO1uC98");
            useraccessdomain.setUserAccessDomain(66140);
            useraccessdomain.setDomainName("hM8Um2DRGR4plGYg9UAvgACjeFMnFldbXW0RdsPA6ayNvem1yq");
            useraccessdomain.setDomainHelp("LomPg3v141TiivotxENzEAnSfQ003MBEW6DV2XM1LMObllhs9T");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 155362));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "CKoDUmuYfk87jlh20OiMZCZbbfkrUSgfhpvTD7LQvTv8QziYqLUktXx2QHlLuZnxenUPxWDpZwspT5fz1V5tw7n6GZdUN699SkJFaL5vaz2i3U10TGOgkrvWyqYMieOGh44aoZFbSrPGm2we14AWlN0edksrpullElWKjDveTAQDRY1rHKyC5A199RuZCXyu8umlX6MQ4Z0MJmfg9ivniMf8WezMrG3ecZ86q9P6lwtN4cWUuXdUEtqOfHNDIoiKh"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "Bi9CULbXGHg0CfReUma5jENLRg9FTL9YPeG7U01awqytsu0mW4Efwqo9dqXsWosuUPbQS4bh43hN3rDO8jfknZaucYF7mkR0CGptDuMky8bm5u7tcZvtGsz2Nr5SigAZ0vWTUIF0XlsO53pfn2yEGN6DKZNbDZWc6ockv33a1iYLS2GfvbYsqawMStj03FKqSqSgL4URmZQ5fiTKoeUDxkJQBFnp5WD3yDc67VQzCqD1j2694hRUWQhyz3N2sdsOS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "KVzSfZW03ow1i1qNh1z0jy5T7eHWWKNiXKi06J7RoUBjY5LYifdXLpFTI7tVW0zvnpgYnQ69cdnq4SWwBXNeFKvRplMfwA6toIpfULY7yvMrFflV6bOxLsrxf4Z9CMo4TXIlgxk40vj4e1q1mBRKjZVZvJPKJnbxQouIe920AHkKDxgw3RcXoB3KMRrKC5yEprDOMEglVJ6wIet5xxgF2ZdfEAuqruFMEWXbdX1PRFr84W8AvjyLbAyK7UkDaNw2y"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
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
