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
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setEmailId("MXjGhPBLKWRODqo44O7vwWE23qqFFyjX3hW2Ori5XRlvV6Ngu0");
        corecontacts.setFirstName("qmkHWaoAzmaWxOH7DzzxKuo8tCWW9RgusSUUpqTU9Ri1j2FOwX");
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("qBWd6G2Xr7JzsPfX1sQQhbPLIjBP7aCMFvfrF0APNAN8Weatvy");
        timezone.setTimeZoneLabel("R9pLVSqTu2OVKTKfccniXiBZRo14VfuvIN6L2n8uYB84Tl4EH2");
        timezone.setCountry("NavCCxz3XdjBA55nGu5Cs4KbrHcvNdgjjnFVPVlEKkEo5pUpa0");
        timezone.setUtcdifference(1);
        timezone.setCities("h1XqiTMJgH8rlcNrMzaC8wnvnrYrkT8UtzMM6Dfc0EmNO63fnz");
        Gender gender = new Gender();
        gender.setGender("P7gcbaiAWTvGysi8tggZFtYR8daLm4rLbXg0nmh4f9L2HrhJcu");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha4("3F0d");
        language.setLanguage("0dGlXvWT6lyuKgYGHDKkVjiAcYsZzbbtsOlDMkJzri0mVt7Cbm");
        language.setLanguageDescription("ULQIlkLBnUZwDfCGBUSuVQqJWtwGnPbQkJ7FYwrsa4L7iHBvR3");
        language.setAlpha2("DJ");
        language.setAlpha4parentid(8);
        language.setLanguageType("7fQ05G5l8f9zdVZWjNci5Lc3m9FMCHeo");
        language.setAlpha3("a2A");
        language.setLanguageIcon("Fk19986tmywikCLwkU1vuP3pDrdLsza4ppSx4TY8DEvIk7CMiN");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("pWsWffPEpaZ62CnuCL65IjpuXDzmLu5qaucK9orqE2GsXRjKnl");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setEmailId("H1Z1EecHK87zJSNzwtM35vI1wrqjPXhX9a3dbtonEwjvKmtTCP");
        corecontacts.setFirstName("xigWeOQwHoq4cyBpGM2KJmFQ6tRhFPD4VRhO1zeORcPzXexCIa");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setAge(27);
        corecontacts.setNativeLastName("4dILNrjQyEFgqWJBaBu3sKWXFmWjFqDEqbxvDO0YEUzY7LeG3H");
        corecontacts.setNativeTitle("4XZn3GY7X1Akt5jpEbZoE2Rr2p3RPPRn8G9jsrKQPiIo8kzekp");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("wrmXxbKFctCSQCp2Rzp0");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("L0FoE5se0kQKNjXHod9ahTzUfHUJlL5wIucS07pVUPnVgA30Io");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465045640023l));
        corecontacts.setMiddleName("A6UcVITDT2yfitxrnAYqCHQ004pWKFVfdV3gvDW1tgkJ184wZK");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465045640023l));
        corecontacts.setNativeFirstName("6jhFiYHh7UpzaCdJH8OtJpiUkylNmnAOuyzMbljZhmeZQOJiml");
        corecontacts.setLastName("wzJTp6Qr8aVzFO9bLpdjKD5zLIeuDSNSashXIc4Xk9DJYhVsDy");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCapitalLatitude(7);
        country.setCountryFlag("ZU8H5FRVfGyObGJkcdrcL3vWsqKCMBpJ1Mq68ctU13GWVs1qnr");
        country.setCurrencySymbol("lV73VsCD5BTgqa9pOzPFej3fP2HNdGkh");
        country.setCountryCode2("fQi");
        country.setCurrencyCode("S2P");
        country.setCountryName("GVfatLf0UEE3hTVgha7aIc069mxZhNdHMhsNsjWRyFMRsyP4Eq");
        country.setCapital("m5fP7o87VFhcIUZ18mTFKaBRBhqq3Uso");
        country.setCurrencyName("CK2lb65GIBs3KOu1DDkDn3mYi0FWPSPyNzVXZtZRz2QwA6L6E1");
        country.setCapitalLongitude(9);
        country.setCountryCode1("kuk");
        country.setIsoNumeric(267);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(9);
        state.setStateDescription("Dx23HFThbsyaEkfsXP6GEeIdGQFBziDckVMXbekOVMlkGLcMg5");
        state.setStateName("aEC9cbPKh0zm4oyWgWZZNMjBw3HAZuEPBZc8HE8751R5UDtC2u");
        state.setStateCapital("eVbLIV5mK4qQRDdbOXTLkH08aHOswWA4gKziNtRQcXuFoWN3Yo");
        state.setStateFlag("phcpBI4K8Ud6lgMOD1aFbzjqkz7fbLjWIFweSPw1fIdd9vtNo9");
        state.setStateCodeChar3("ODOqQmqR8yPedd7EN41ExVWhwwqXRZnR");
        state.setStateCode(2);
        state.setStateCapitalLatitude(9);
        state.setStateCapitalLongitude(11);
        state.setStateDescription("7ZuBpFR5xFx08yyGBz7AfbOAW59KwytE7vdeuSKoWtSRXsnPuC");
        state.setStateName("ttT9UEmYYMjTy6O2lC7Z6mnvdZx7IEmB9OTFaNuJktOuu1cliF");
        state.setStateCapital("qVvqTTRUSO1Xc8ukXKk2ejljQcUyShf6cZf78J6CufpLcIrpwB");
        state.setStateFlag("ltAr8KMIGKGHHPzqQ6id4IbFDiFyjH7vQlIBAhKiuy8baMqBpm");
        state.setStateCodeChar3("pEt22hzTKMYGQqGGwnuOPjgBMd6ZwYKJ");
        state.setStateCode(1);
        state.setStateCapitalLatitude(9);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("FfetqVgoKC0MeAFdi3euNvhrIMvgMlNn");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("vtXWcnbzzbTQRRXxEnBbt3VjTPcRV7IW");
        city.setCityLatitude(9);
        city.setCityCodeChar2("dX9z7dqGzOJzIlTlyyJScVPNmpc0Obdd");
        city.setCityLatitude(8);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(4);
        city.setCityCode(2);
        city.setCityFlag("MbqjGvU2JfGiTUJySMLOcAW3MWPVIIjXhwRFGZRxgUgSumqt6i");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("HVhxXkLEaj21kbCDhmrc3vaqkP4qwt5q2Z3VAlX4CCqTlXRGjh");
        city.setCityDescription("MYJ2OJCX0AxCuKAyV48GmEmH6BWvkFSKQFjZcyPhEztF4AKJo6");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("qfp1tk8z1JwNRLMnx5yZQu2iGiHKjAdsPW5hBrdMeNWG2cBJkM");
        addresstype.setAddressTypeDesc("z5oeLlXHbXJFdLBEjkzPaLN2QhYU3Cq2dfTLcRc9Fv36UnHET7");
        addresstype.setAddressTypeIcon("jrANjCkk0T4WfdKPj1P2yvZ0Mt9fgMbFiKxfiAk09g3JArhC59");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("viIJJTU2OZ9OYksXpsuxsoooJ85mNuhllFc4t8g52ve7FcSBLH");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("Qd7D9zrH1C90zfmaB3UoTEavmG3Eb79wVV1PR6zTl0DhCzu1fK");
        address.setZipcode("YloUxB");
        address.setLongitude("ZnPii5X7u673hcsIq57QSgAUj4MoOoBssuWUl6UIXpZBiiKyhm");
        address.setAddressLabel("Rrw74IuuXgk");
        address.setAddress1("BkfX4307Kf5ZCLTTBMgVaFMbNPU26a7efOvQmPeoad12HD7tzf");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("7lad8kaOi1nokU4v7HHQHRZUsBdEKIPgQv55Qz8rkI1qtmRROu");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("leeU7wXY4q");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("M5ttXB41uiBbr309gjgEgipakv1hebsXMYp169TGBdqGBOcaI2");
        communicationtype.setCommTypeName("yPfix7pvZ1EGQY1znmjYxifFf0mpTF6o4DUipyfFcGHgvorVMY");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("PAdyAgvuQdnwmxK0HLfJ28xbB1F0RLeQmhRZYuW2us8nffDoGd");
        communicationgroup.setCommGroupDescription("Lj76W0FsNMKE4DUwi65VyoWehBmAhtuwlo4OvZ0JW4VnN8OKTh");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("pK3yqDKI5fb2EvDdQW8pm0XzUX3P7yrLxveKew01lAtW60WzG8");
        communicationtype.setCommTypeName("WVGbh19dHFW7qi0JjqDskF6mjKucvlSYjJTlPULiPCIdLTDyxi");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("WNETWMYdTx");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setGenTempOneTimePassword(1);
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("sMAegmDC2xejRZK18yAXCBSdFLrbKtVEC8iP0V4LcnmHljfDtn");
        useraccessdomain.setDomainIcon("HQxIriZ0wULoxMJsEgAur9eOS0HNx7Im5MZd433nGlPJpANSnJ");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("M88G0ZJJBjXWzlYwcNn47lcikAZdrDQncDX1TyZIOWnyFfLYpl");
        useraccessdomain.setDomainHelp("jGui4KpjgvoxlRsWsHCL4fY9L3Rm3m7E72oaij0bRdXEkak0H3");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("c1vyWlZOW49be29lNslnkcOkOISIz4qLLEGHGlK2ZkyhbEGu0U");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("UFcmHQkuv1x3CVsf1j8U3S7mZML5WNwOtXaDdeE6IFTZCu2fLh");
        useraccesslevel.setLevelIcon("WYvNVdFXQ2kHF6k1KLht1hrjvFwZMkl4jyijD4xWVuVamwLkur");
        useraccesslevel.setLevelName("39RsEFUMqVAq10gzagxBmvKd9HLmFeIrmHrKcDe7wDamjVG2At");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setGenTempOneTimePassword(1);
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setSessionTimeout(2387);
        user.setUserAccessCode(5403);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465045641149l));
        user.setIsDeleted(1);
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465045641150l));
        user.setMultiFactorAuthEnabled(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordAlgo("6G47rSmTpr7V8GvyNSQgHV659Sajo2DUW6GkAM1wR7TEmlub6W");
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setLevelid(7);
        question.setQuestionDetails("WIHuwgcKWN");
        question.setQuestionIcon("UxIhjF2KTKfe4ENb6P3gfLuZSVFB4YJOEmjsuLiPGRMpWiykqh");
        question.setQuestion("xYJFR0at6WWSHj4a6Arc5o7x9j6DRytHBg3Yftb7hiTiGklAg0");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUser(user);
        passrecovery.setAnswer("jgafyTy6xAMDW6ZcL8VYTesuEfnZihWl3tleviweqdTtDEEDAe");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465045641688l));
        userdata.setLast5Passwords("ZQkWDg7ZPufyQPGaAYSZYZVI8P15jJQ0tdvwexMoR4JdcHbjSJ");
        userdata.setOneTimePassword("Jf2bgt25gLCFO4OPVaFI4KkBW5L2ZG3Z");
        userdata.setOneTimePasswordExpiry(6);
        userdata.setPassword("fLP4SeShHOdgLuws0a3dZXkjoF0YlzuJ67H6e72NY93XUSsrHO");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465045641737l));
        userdata.setLast5Passwords("yRAqrXW1Qw22vFdO4F396GqkpPv9VmBo9ZxhsfusTfLKGHsnZ8");
        userdata.setOneTimePassword("4O5KfRxIvbTvWJ5APknxtgxpptai0tlR");
        userdata.setOneTimePasswordExpiry(3);
        userdata.setPassword("9K1HvSSbVoeZ1ZMSmDeRFvrLNh9On9XsIVmEcmggqUoWmv5d5o");
        userdata.setUser(user);
        user.setUserData(userdata);
        Login login = new Login();
        login.setServerAuthText("kHSkanekzf1dV1Uj");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        user.setUserId(null);
        login.setUser(user);
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(2);
        login.setLoginId("uaqIgKqKQnmb9m4BdjYFvIDBSatMsPTBbzkKIZFiBtGmfOmlRQ");
        login.setServerAuthImage("9YZw1LpIMOm1fjFJDjv2wNi9N1Smr3wA");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthText("NGmwsy1m9EOhw4Oe");
            login.setFailedLoginAttempts(2);
            login.setLoginId("JXZVoEX1HLHfF98juw4RGR2ii65HFw0g40M893Ynyhlkj7OrGP");
            login.setServerAuthImage("jEj42TRskqpOVnbZkWwW3ygW8sRXpDGl");
            login.setVersionId(1);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "PE1bqRmX3J3tOku3U9RrwmnwauXSNDQtwBnLDQDCOjSNzO4QBjNGDMxIw6iUuDddni2TV7oDtzRPUehetvVcw89pgYwIbKnBqY9zE2j7R5l7syeoUccBbkwTXvD1manTAXfav9IFqTwNdwVkWvzbQAMDAKOSBrRokIvWp30IRMPoDsxlBiHggSCbpW4nt6cEGuz00u7tZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "BWkxS7u1gpofnzEh4aDRDT12PmuC48fKX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "MO0bX5B2WmfoDqO4z"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
