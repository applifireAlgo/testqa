package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organization.contactmanagement.Gender;
import org.springframework.stereotype.Repository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "rushikesh.jeware@algorhythm.co.in", updatedBy = "rushikesh.jeware@algorhythm.co.in", versionNumber = "2", comments = "Repository for Gender Master table Entity", complexity = Complexity.LOW)
public class GenderRepositoryImpl extends SearchInterfaceImpl implements GenderRepository<Gender> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Gender> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.organization.contactmanagement.Gender> query = emanager.createQuery("select u from Gender u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Gender save(Gender entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Gender> save(List<Gender> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.organization.contactmanagement.Gender obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.organization.contactmanagement.Gender s = emanager.find(com.app.shared.organization.contactmanagement.Gender.class, id);
        emanager.remove(s);
        Log.out.println("ORGCM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Gender entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Gender> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.organization.contactmanagement.Gender obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public Gender findById(String genderId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Gender.findById");
        query.setParameter("genderId", genderId);
        com.app.shared.organization.contactmanagement.Gender listOfGender = (com.app.shared.organization.contactmanagement.Gender) query.getSingleResult();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "findById", "Total Records Fetched = " + listOfGender);
        return listOfGender;
    }
}
