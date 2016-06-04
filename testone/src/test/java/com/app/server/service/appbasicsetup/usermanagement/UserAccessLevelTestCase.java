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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("8hsDo5nQYCFW7KG9I0nakt0qY6lOWjUQCivRIUgqc4jLbeJ0Bt");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("y0SU0kuaX8G9hWIWto6sa2IR6Lcx15RbTuiNgHrNeNY2zJcbsN");
        useraccesslevel.setLevelIcon("j12TuZn0rAwUQncTVGAVyyAmzDy8kUHYXZkTNqwHfHQJWjeew0");
        useraccesslevel.setLevelName("zFFRPwSTlg829mhZYwoHSzdfssbh9xAVskj9AbkA4dwCnIm5wD");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("VDSTgolYjSx7zPL6EnIChjHzUFnGh9tqh2Uavz9t92nn6AiLtm");
            useraccesslevel.setUserAccessLevel(68183);
            useraccesslevel.setLevelHelp("gCjOLSDNbpOxOXiAo4Mns6OYIDdoETidGldxhSvhFe9I5s6SrJ");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelIcon("mhFP4L9kvid6cHYQCnfWxbfT88wU0ca67BWeExOPUcfvxtTmnm");
            useraccesslevel.setLevelName("3zNnZ5zZcZedrIkwd3ivtVlGxtJhAHxVO3CZG7bxJJhC7Iv0yE");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 131726));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "4y0yXMAOegTImSBWQRQeVCvHFNa7nTPxJ7DqDAEl0fOuMTmSMOoIAPm1O79j3D2aXFMV9XtBJv65CENjrOlCJn2SStffMmRI1ZKINXYfrdwmOoVaLUrbFczMnKi9NPqaLajXSB3LuB0GzLqn879Jadf4G7AKbmNo5o4wBE5BqMpG3wOCc8XVXVyrNd4vnxU6PAQgj3SndvKetbmWXr3hlqGavt1Q6aN3H53k57qckhDT1ETh15z4nxTDSPF7xbZ0X"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "Abn1qYEDhW0EsXbysnd9GZcAAB2aLgzfZrUpHfHRdQ3TtS1FSH9kodwcRTjDs0NMH6NEsP42uaprRsJrvElA2HRiEeozgc3eeUuZxY5NWGgIyyz8uP3Edh71s9Ng37ECueQPbHM2fR5UmrI9D3GPt0PofCGqDt8uPF0KG6KGRPTQldch6csa9BnVdp3wQ61bCzL5D3dGuZPDJcfqEA0QTSfD9n6q747BhZcLTFlAHQeVHzurKxbNXBzPwfDRYWvpd"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "OdfoPZzrbLnls7icmbGAY3mEuMjufAmqkIKmXphXkkPYKmlR4PBQZZRdR0RiVIHYSjGgv7RFbovqsrKKKUvS2DnUgYO1OrLwE7mmZf9wYWRgVeEt9xzQdfFJB9FWSBignh4Bqpmnfo2KVD2XvE0UW3ys4SU0LYHDugA6bFhaW5sIbkjsOuztHpwcKWYJX4Y9rravZpFfEczZJ9Ha0oTFAIlCU44lnNxpTHFfEqt1MyVNpcDSniKUCfe8IpkOhhI2b"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
