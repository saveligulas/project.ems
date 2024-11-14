package fhv.team11.project.ems.commons.controller.error.handler;

public interface HandleRedirectException {
    default String getRedirect(String redirectEndpoint) {
        return "redirect:/" + redirectEndpoint;
    }
}
