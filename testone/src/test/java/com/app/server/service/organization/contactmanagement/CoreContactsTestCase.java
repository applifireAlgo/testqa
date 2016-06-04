package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("zu8dwzIZrREURW0rWG9PyptZhFpbdasCj9EcaWlkk6j9FqXAUl");
        timezone.setTimeZoneLabel("gHucsJWsdqZ1Jq6NkKcFn9SosCIwMRq89aY01e7tlAn70cj1Bl");
        timezone.setCountry("L81R6qQPfFtpDrhV5wuIOtGOKdQuM2fh1ettfYTzYqLNz5bcrA");
        timezone.setUtcdifference(9);
        timezone.setCities("WwR0vY1PEx7yBbwrRoLBiON4xt2zAWgGbtyLjWWFsALqzhoMQK");
        Gender gender = new Gender();
        gender.setGender("lTb7QeiYq6vsWQqgoJABHCVAMIpEvzLD6wTeOoAClDXcaB6x0S");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha4("SrAf");
        language.setLanguage("wuKa3fGfmKBMxI5WMsuQm7P7tY0lJJwTvVk0pcJyIybRBiU8VK");
        language.setLanguageDescription("o0X3vgZ31FXEz32dCcBlu2h6vO3dseF2CF19mFcSTf3AxxudQs");
        language.setAlpha2("Ht");
        language.setAlpha4parentid(2);
        language.setLanguageType("NjWZdsEYrSSjjTQ7MClEN2xTZjVej9bu");
        language.setAlpha3("mDH");
        language.setLanguageIcon("mMvbpG6Gk1cTyFfS7xAOOCPQFuoaxVrJALvX9atfpfaAu5MuQg");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("E91veYt1MfzLvNKhd35IHbvzfBotiG5B65NjhojCSwmSFNLba7");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setEmailId("lom2MXY1g5alOK29EKQBdawwUmg3FlvEyoI0Fkd93IE2BqgyA8");
        corecontacts.setFirstName("U6PecDFpESZEQarNTolYMeHYuBHeGLytkIa45HpunNBJENRXDx");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setAge(104);
        corecontacts.setNativeLastName("6mviF1xS2g0zPwqAp3r6PdCEdx6luZMMJrsZj1vvTcvqzI78CF");
        corecontacts.setNativeTitle("60KOGizRPnp0YTfCERZbLvff6QgYoHsJ6QqaAGsbNblmPQ2SDX");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("io8XU1D6W3LDLbMYr4XI");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("ktHqG73LDo9BBwMqTxlVhwjwLUNkYVMydjsJrI5cFvIVrWuVO3");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465045620821l));
        corecontacts.setMiddleName("p71fP2PWuYSbwrpHp5wBNYXirnjuN3nQFunf5JyqHrellBrM8x");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465045620821l));
        corecontacts.setNativeFirstName("1lyYdCKrok8bxQkDzadq2LGmCLgaAFqm44XTa1meG9iJCecDcD");
        corecontacts.setLastName("6gbBrLQOBma5rm5bHlPTVwbLe68cJiWiQziMqOaqZqgljvJbFI");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCapitalLatitude(4);
        country.setCountryFlag("8Vd96Snq8H5FMTYLdIcHRJEmGV22zk0pIPES1vHIsEAn5ipcNW");
        country.setCurrencySymbol("wD8ZK6ED93NbAAVQlQM8GYUGb8pesmt9");
        country.setCountryCode2("5Yd");
        country.setCurrencyCode("BF1");
        country.setCountryName("uCrIZkN99OaUYRTANXruBYvWB1bBPVakcDLZPZTfw30oyNaj8a");
        country.setCapital("sPgRyAZe9aQXiaWvGwW3rXoq7fZjuvcE");
        country.setCurrencyName("ut2goX5gteRCjAAbFvPxAwerrReO4hQjzzClmhaHlZfngnxotH");
        country.setCapitalLongitude(9);
        country.setCountryCode1("cos");
        country.setIsoNumeric(484);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(3);
        state.setStateDescription("BMCJe5OqFunNy7kFvsQXbJOECBRfqNQ6fI8Atl3jCDBvKh0yot");
        state.setStateName("RIRSUf7Wbvn4L7CW3tOHlNJNNIhdCjHbEbFPE0TijP2BR0r89N");
        state.setStateCapital("o6W3ha36h34EjYWpCuIyOdsKnGx7kVdjgvM7HTQ3vCKADBKaxc");
        state.setStateFlag("HRlrp8byEYWdykmFg1wkFfZwyOo600CALAaSe7SX1ipIaqMMrj");
        state.setStateCodeChar3("tawKZJAftTc1RzwCo7zGLKlUMMNJMhJo");
        state.setStateCode(1);
        state.setStateCapitalLatitude(10);
        state.setStateCapitalLongitude(7);
        state.setStateDescription("bONn8wQ2XA0u0OWnQuwBrM2t2xyiLHo7HgbwAN7x0O3irV3kD1");
        state.setStateName("IK0KG6eokfWhz7RwMDQHkHJLqWyPF8Y5q4hZL1gNXrK8DaIbP5");
        state.setStateCapital("6k1bqUhyTDjxYylPvIm7a0rAG0jEMNyCCN6nyOEu0IYgyesDg1");
        state.setStateFlag("g3BBHYKYvQgITloHXkrgYYPRMixba7i78Tgm9OU1N4CCrSa60B");
        state.setStateCodeChar3("ENCIJI5OMd9FtVPGfzB2u4QarL0XAvZ0");
        state.setStateCode(1);
        state.setStateCapitalLatitude(5);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("DD63pS7DiusLbhtdajqm7Ine8hdbYxtO");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("ucLwniz9Qkvc5cdvT52MiHACA9qo82Z5");
        city.setCityLatitude(5);
        city.setCityCodeChar2("VrLxNclp4PNK713nuQQdjNP6KNqQdQhn");
        city.setCityLatitude(6);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(3);
        city.setCityCode(1);
        city.setCityFlag("iCUihUNrPFRg2KviX6m1PZnA8hRdiLVBABgXbMYliJ7upH6SYf");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("ZRugeW8wBDfIVPKEd3u4EuiwIohTvWsb3iryZZfDbxOOKtgzH4");
        city.setCityDescription("TQH0r76ASBbT1t2DLt29fQZm8pcbmcsVsWSRpKYuICamhmohVW");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("FzLVoFGTHBCUT7SJDba8ZcsO6txg3JVEVwSL0vTkt7GLyYsj2L");
        addresstype.setAddressTypeDesc("7b8HgzY4EEXZZiOCl9lTlUOW2nqOP5pgQZ6Xd1e90LR1DgjDYf");
        addresstype.setAddressTypeIcon("Al4JNLfqiXRzbxahXSWknM1edhX38sPYmWnll9NP8BgeJHpz0H");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("PVkth8sT4SVmXlU4ieBk9IsiivdUUZJckxoY8HOAH0aoUgoCwG");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("ceyK7NgfMp9iGwAai9sfhDuIDQXNF2X604P8RvTOdRqHfB7i86");
        address.setZipcode("lKHh9P");
        address.setLongitude("E30Z7JBsXoDoBlrfQiX9XJT1GIpmAN1TWOobGT0PcUW8mcscue");
        address.setAddressLabel("iNMwqDfeowc");
        address.setAddress1("Y03qjn66jxHgLmIagdXCV9PEhO6LGHx0kxVXkMbL4Sh0qR8R8L");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("G6rcbbnH2DV3G1GyC3ZMG4gMh9LZLDKc8nxPoECdAB3ND0tqCs");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("KI2bVL42ly");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("kan6XJPHmiIn6mFTDAlcKz9AaUbC63PXDuGiKf6s0iiivbCzCA");
        communicationtype.setCommTypeName("PG67nD04a6ozeyRmzVZGKwHqHl0pBdGRDTl7bT8wOIvATpvGv8");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("Gqpqtl04MFCbvSsqfoWbkY8Qk4c8pWbdm62uRZpKact6oTDkaC");
        communicationgroup.setCommGroupDescription("M2ef3q3rRhYP9gRA9i40bcVl0aoCnNHYIqRbdlU9XTfo1v0sf3");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("00xMhF4RPuopuKF6lk5ifSEsVrS6H3e0ENNAnMitaHXmXt98z7");
        communicationtype.setCommTypeName("DWJIqP5dxw9d45OwTi1vohl72IVUDwMdimCsJ7SxscsYuoEYTD");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("JOUG8WmDGw");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setEmailId("uS0NrIh8eWKjnRK6gPijREPxBshTxXHnERUOdZz82vDmyQ3G3o");
            corecontacts.setFirstName("uEugQCTauMQMjOvmPnKQKdRKkuiBYYdJQRwXUYx1WkVdoVN9bY");
            corecontacts.setVersionId(1);
            corecontacts.setAge(10);
            corecontacts.setNativeLastName("WZxYOSrn2XWpLVsVnG8iaH9mgnzv34P0vAD3losTQuM4zRiSl6");
            corecontacts.setNativeTitle("HpbDzCcEjoe4p3vMeMR7PL0bICKTosafs5oFSbILtRisZNIvL2");
            corecontacts.setPhoneNumber("5WwLkafilwcmZsw2AU5Z");
            corecontacts.setNativeMiddleName("Guge0FOLRcta924Ztu6fAORcCwPJGK2KGydQF8a01vLf68TXzl");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465045622110l));
            corecontacts.setMiddleName("wE2hSOjvqVa6tM210mlSJYwVGPt1juSHQiEcRezAP8tMMVYTYu");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465045622144l));
            corecontacts.setNativeFirstName("H2X3omZMPQmVpgPPeBp0CJskWIp0NqUkkIMcfVDtaCA0wQQUVy");
            corecontacts.setLastName("xIAEGUfNAwFsnZOzKvBPuh53wwdfVy0Yt6IjmBXdIuEJoGaz10");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
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

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "JWt61OULpThf2AaO8s4IVkrud17F3pQGF8LYw5of6obHZl14m3ZZM7lDmWvb59vIj1uSlTuN0ozVuyUmV2s6BbxxYbBRfMmWuD7haMSpg0muwQJiTVaKa0qMXqsV42PUOYmEm1ioA4TJ7ApjqW2MXa0jDyjJ1C2R1K3LCNhPu1HkwSr7fdVVROi9c7oCPmE9YisGnBXLMThr4NNyCm5lxyW44sGxshxFBZt8CH6tRzkxH9qPD1bYyaoCOFCcKEvrI"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "tdKjxpG0RbQ6BOjWyamFfLY5h5aw8ETSbIKLExEawwtoY7AbmXqMJl69lMwlYqI5qr3BTahpJmjLio4QLRlMnDxyjEGsLMpPyjItqf3A4mI661tLi6oWNeXK08QCks0bwbqZpKqORHPeIaWDoHqsVWI3rwxpVtJdyu2O3Q4LWWk1ENafHH70QB7eG3QrWKpN8mT8nPdZV3S6NSvIkJJLoOoDJGRzwooACAOD46oz63SicgzOKcHz9hgrCRcbZrTQu"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "ZETCMXY3RCXiebT98TywyUw3zyPfMWPyIulsDg9mk6oUkb5XuFocpiIn3elh1FNWkB66rYzH7LCr9fuu7Jne0isjklMfu3DkeajNSzlpKGuAPDXgwWTppQI9jlnkX7LaGlWhUfA9uJAo9b7Toek7Z3MmHsThFbBWPJch8Sih6dBsXnHXdPOduL6rNxGFW5usMjC44MlgRtI2YDBbYb7rGUciFZXRrIOwx1WlEz4mQzRlJIdcNRlg3slOIF5kCWBEQ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "r7PCTnQNz5XydLOg4SxJfgx3RIXvt2S0gEpLeRoFwINltecdDi5jlnxk97zVIVzjh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "QStAaPXnJTnnluYoRUHnySuMbJzeabCtV1Edy3EIS6aYpdqHxhFl7MXSugXTyFuoN9gTG2h4DeewYN5N1LpLuzGAvNKfHgnSW3F57GtTmy1oU7Obf59oiL7PfPNvR2mkLSkhEXDpog7WzNYc5Al9xOOtphQ9WkxNoIGS69tIovGcnj3oajo8l4sjVdGbpzq1TgVGCO66P2LLBbS99TKxfLNFtub6ldc5Hyqh7E3lLouhwPf4NFPsnraO1AmZVkIXB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "lCuFFU7VUZtkXkU8Mg2C099sgvGfSYjOxXNMcwYYHotR7h7DzCmaA8I6oetyVvzC18pDz6UkuBvnhQtv1ts1PpdDdaPRCY5mHZ2x7JI1Pv6sLkTg1iGPmfHkEN0wo0rX3bSAq2wS7eLJl3yZopdkbWFSEFVHd6K7Ib65tSFLqm4o7Ysn8kjpRZTW2OBSSPJBri7ZCwE099LhW08AYUKPL16B7kfRS1F01wfZwsVb6tjrIiBT8vwIRLmzETUPtzcXD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "oG7EDyigwiQ8PZpncRuyzjOa5rBmigQYolf8G0j7Yf9tZE2bCvDj2dTq7hFhLfVXggHzaEzeaUAxpNrlOybeP5XJZD4a9KBw8syOi059opvpK99c7wN7P83kdcrKcqQZk4qn63ZLkp46pqDDbVHSBRORQMhnfUeD9Vf250glpXZ4HttuTfUl8Duq3cyEj1rdR2skuF6Sx39i8FQjrYUDx0skO9SNdN1fkABWmQ6DMnbZVs1HNtrpODjVSWR0kssA6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 193));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "yiQsUzfs2NFfSK5DIeLCUDEZbcjWYOOflgPc63vVYDuf4o3F7bhSia4gYzwaSB9uQR6rmVAVuF36LQ9uJ6tq4DrvzeLCZP4JlHR6ZARggnr9LgyMft2rWom5eKvw6MA4nTvow3o5maEf3LjPxkydR5ZRKxeKz02N897mnnQ4pybSiOTXtWHarM1Z7AJKIzaO4PXaOm7ntafuYboqYSO4OBP54XETTVY767G1IIwyjecdJPl1kiTZAIYyFnDbGeyvr"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "CyUQOtz0wPCYV6K5KPkHF"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
