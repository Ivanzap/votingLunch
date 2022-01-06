package ru.ivanzap.votinglunch.web.user;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import ru.ivanzap.votinglunch.HasIdAndEmail;
import ru.ivanzap.votinglunch.model.User;
import ru.ivanzap.votinglunch.repository.datajpa.DataJpaUserRepository;
import ru.ivanzap.votinglunch.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    private final DataJpaUserRepository repository;
    private final HttpServletRequest request;

    public UniqueMailValidator(DataJpaUserRepository repository, @Nullable HttpServletRequest request) {
        this.repository = repository;
        this.request = request;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasIdAndEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasIdAndEmail user = ((HasIdAndEmail) target);
        if (StringUtils.hasText(user.getEmail())) {
            User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
            if (dbUser != null) {
                Assert.notNull(request, "HttpServletRequest missed");
                if (request.getMethod().equals("PUT")) {  // update
                    int dbId = dbUser.id();
                    // it is ok, if update ourself
                    if (user.getId() != null && dbId == user.id()) return;

                    // workaround for update with user.id=null in request body
                    // ValidationUtil.assureIdConsistent (id setter) called after this validation
                    String requestURI = request.getRequestURI();
                    if (requestURI.endsWith("/" + dbId) || (dbId == SecurityUtil.get().getId() && requestURI.contains("/profile")))
                        return;
                }
                errors.rejectValue("email", "exception.user.duplicateEmail");
            }
        }
    }
}
