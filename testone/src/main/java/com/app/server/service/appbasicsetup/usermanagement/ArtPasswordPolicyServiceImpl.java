package com.app.server.service.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtPasswordPolicy;

import com.app.server.repository.appbasicsetup.usermanagement.ArtPasswordPolicyRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.athena.server.pluggable.utils.bean.FindByBean;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "", versionNumber = "1", comments = "Service for AppMenus Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/ArtPasswordPolicy")
public class ArtPasswordPolicyServiceImpl extends ArtPasswordPolicyService {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private ArtPasswordPolicyRepository<ArtPasswordPolicy> artPasswordPolicyrepository;

	@RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public HttpEntity<ResponseBean> findAll() throws  Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		java.util.List<ArtPasswordPolicy> lstappmenus = artPasswordPolicyrepository.findAll();
		responseBean.add("success", true);
		responseBean.add("message", "Successfully retrived ");
		responseBean.add("data", lstappmenus);
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> save(@RequestBody ArtPasswordPolicy entity) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
			artPasswordPolicyrepository.save(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully Created");
			responseBean.add("data", entity);
			httpStatus = org.springframework.http.HttpStatus.CREATED;
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> save(@RequestBody List<ArtPasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
			artPasswordPolicyrepository.save(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully Created");
			httpStatus = org.springframework.http.HttpStatus.CREATED;
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
	@Override
	public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
			artPasswordPolicyrepository.delete(entity);
			httpStatus = org.springframework.http.HttpStatus.OK;
			responseBean.add("success", true);
			responseBean.add("message", "Successfully deleted ");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
	@Override
	public HttpEntity<ResponseBean> update(@RequestBody ArtPasswordPolicy entity) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
			artPasswordPolicyrepository.update(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully updated ");
			responseBean.add("data", entity._getPrimarykey().toString());
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
	@Override
	public HttpEntity<ResponseBean> update(@RequestBody List<ArtPasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws  Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
			artPasswordPolicyrepository.update(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully updated entities");
			httpStatus = org.springframework.http.HttpStatus.OK;
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws  Exception {
		com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
			ArtPasswordPolicy lstappmenus = artPasswordPolicyrepository.findById((java.lang.String) findByBean.getFindKey());
			responseBean.add("success", true);
			responseBean.add("message", "Successfully retrived ");
			responseBean.add("data", lstappmenus);
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

}
