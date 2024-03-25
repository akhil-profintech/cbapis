package in.pft.apis.creditbazaar.gateway.web.rest.rest.errors;

import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.ErrorConstants;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class EmailAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email is already in use!", "userManagement", "emailexists");
    }
}
