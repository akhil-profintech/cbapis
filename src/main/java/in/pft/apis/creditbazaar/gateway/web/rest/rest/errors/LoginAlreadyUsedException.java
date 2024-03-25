package in.pft.apis.creditbazaar.gateway.web.rest.rest.errors;

import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.ErrorConstants;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class LoginAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists");
    }
}
