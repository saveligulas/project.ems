package fhv.team11.project.ems.commons.controller.base;

import fhv.team11.project.ems.commons.controller.base.attribute.user.UserAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAttributeAdvice {

    @ModelAttribute("user")
    public UserAttributes addUserAdvice(Model model) {
        return new UserAttributes();
    }
}
