package fhv.team11.project.ems.commons.home.base;

import fhv.team11.project.ems.commons.home.base.attribute.GlobalAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAttributeAdvice {

    @ModelAttribute("universal")
    public GlobalAttributes addGlobalAttributes(Model model) {
        return new GlobalAttributes();
    }
}
