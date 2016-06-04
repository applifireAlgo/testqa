package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(1);
        country.setCountryFlag("8b4vbaYJHXc2ZIggACMx3tl3vllqDjr5Ex0NkKr3Ou9j1Qyvsb");
        country.setCurrencySymbol("BNWvzLeSW3GT1bZupscJiQL29yiCaOmC");
        country.setCountryCode2("nd5");
        country.setCurrencyCode("KDO");
        country.setCountryName("WPQPSUp31Hxx874rVTb9ycQCElvzJ3SY1ouTimMQUMRm7kLBOC");
        country.setCapital("BbM2AR61NIkYdAXDPK8uC1fZIYw0kdco");
        country.setCurrencyName("76rCj6SdFz4wbxkNLQXCh7HvFIpeSZF3Wz4KMLvjkgp7siGgqy");
        country.setCapitalLongitude(4);
        country.setCountryCode1("eiB");
        country.setIsoNumeric(82);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(4);
        state.setStateDescription("9KfIMlxdmaFMCcLAvTEo0LqcoIaedmwVYhnDbjowYD6HkhGyKY");
        state.setStateName("yfwpEalcqD9asq3sLEAkPUXUFkN2loZ2PwC1PFNLikzlWVZD1k");
        state.setStateCapital("hFS1uFBHaUsd9D4VgwmyR4g1pdXIm1WrgCuKrC8qgYzmPguWQz");
        state.setStateFlag("vMi4WCPdmWzEz9hc7MABGCNLef7d5ZkOgNPZeK1pl0wzKJ8iq4");
        state.setStateCodeChar3("kIQnND35bEnJ2uYy68o3B92SotZxgdOG");
        state.setStateCode(1);
        state.setStateCapitalLatitude(10);
        state.setStateCapitalLongitude(1);
        state.setStateDescription("y8nTMRBjgBV8esKUA5MyHvHf7z7F8YfDVHvJGuRZd6BKbR8JTL");
        state.setStateName("NtFjadsaBmQbO4oREtZBDAktzMsIVLmxoK70dtZWqCX997ZpYR");
        state.setStateCapital("Flx5tDyQ9W65F3CEngNFZWNDkXxvYfiCYCt4hsHZtoF7jqYPhT");
        state.setStateFlag("GV8GTEts2AQa90GxPAj66PY4RLDbn5NjnNviQFrZfZrjvvKkHA");
        state.setStateCodeChar3("Q5Xyf7Gd0sHy6DTuJw5i0GtNnwS3Hf6S");
        state.setStateCode(2);
        state.setStateCapitalLatitude(11);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("m9u2uuODvtzmkx8lrtIV2cYl5ZbbZA2P");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("yGcPUcMqpDF1ZR9nE9JYOfivKKnlMG7Q");
        city.setCityLatitude(8);
        city.setCityCodeChar2("iijUHu1O1Kbw7LfvoXwnbwPuUbhcwSCl");
        city.setCityLatitude(7);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(11);
        city.setCityCode(3);
        city.setCityFlag("gI9cVGtzlm2v5R7n1kyIzCcKAsbT6NWjWKYovJmrNGQ7asAvcM");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("1zv9agFBcwp66ar6kImoQQjEYyIOp6rAjJFcfawZ0FRC6VF3FZ");
        city.setCityDescription("Ap0Jiy6hVpFuwu0STAWZex5PUfucyXwcBhPAKcOQwBDdlaaPpz");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("F5uOsU3z9aHq35FsH4KRCZgdaCoAHTot65w66fDyNHTqEz7Or0");
        addresstype.setAddressTypeDesc("o0Poq3P7EPUcg0mg6PVifp64NmUbHrHmJ1psQmdcBrySAhjf2V");
        addresstype.setAddressTypeIcon("hXaWImaODySrwFUQqY0N9c5uPllA2fWukptKsZgaf2eQTjkwuE");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("qBuLcuPdVrfyGulsohhdeC5G3JiobJc2C5galmv55Xn7UdlIjy");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("Lk0wpLhKFpv4rLw9GMGWuBn1aohe50smt7aMirVGFDcLNnwsWW");
        address.setZipcode("5ZgR1P");
        address.setLongitude("bIznGWBgg2SrCgqZReiM6XGKpzCh2BlHfVpZp4XQr4eNhJZGUR");
        address.setAddressLabel("8REEqoZ74lk");
        address.setAddress1("9tCYcB6V0BQGdKR6l1MxJaQszq3bMSBD6ininOvWxhz8tCJcTO");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setAddress3("v3yKLfo601qdmyOz3S33I8xUknoPNSWq4CGLtA3pUw0yMXOXzW");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setVersionId(1);
            address.setLatitude("JZQtbjn9b2Z6goe0ralVakHUizwLnPpHLpSdnLwi7dh8OcgKKB");
            address.setAddress2("4iUzwkXBRj8X64mV1OMVIto79YYmBtmLlV3WkqguXtsVVpr4vS");
            address.setZipcode("8w1b0T");
            address.setLongitude("f7B4Ou6Eiyj9SUHjEXoK7Qc3cwNugiH2Bsf4bnrXqqICRMzEeG");
            address.setAddressLabel("9FNeJ9m9MbX");
            address.setAddress1("qBN1FiUnIlQR5mFT8vUFwG2NqKpn9vjGuY5t93HJmfoPlO0RCZ");
            address.setAddress3("3A1qs0yEnhJcGlNAPuYmbtwIIlC5x5BwYj9Nm77N4e7Wf87hcW");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "sux2iILPlRGb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "RDkI8f23eXoXzN5ixfGhExjkS6xTfwVHj8INUDpjzBk6G3EXt6ApAD89X"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "RybOgSt62zshjfHguRL7kpouOQeHmUI2zLL3Tx0eoYdIeVQMFyKmr3YT1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "rDRS14w9bjmAXaGAE9EYVyeljNjOqPq6h4uEgbH4o1jiQy15Oo2v6POcT"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "Xz5NqmA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "rpxmxy1QsEvHHBtaRmrUGPjGJYcP281dxQTRL4rSgMGvx1qRcxmstm3gO46UbqbUP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "t4FmyQDzzpiK7TNw9rI1lUgAF8kQVTb0GqYAHodYph6BmtN5X51kCmwRfB6M4CLkW"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
