package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.SearchInterface;
import com.spartan.server.interfaces.LoginSessionDataRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "rushikesh.jeware@algorhythm.co.in", updatedBy = "rushikesh.jeware@algorhythm.co.in", versionNumber = "2", comments = "Repository for SessionData Transaction table", complexity = Complexity.MEDIUM)
public interface SessionDataRepository<T> extends SearchInterface, LoginSessionDataRepository {
}
