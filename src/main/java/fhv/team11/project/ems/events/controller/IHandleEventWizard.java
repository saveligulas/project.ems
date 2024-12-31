package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.commons.controller.IHandleWizard;
import fhv.team11.project.ems.commons.controller.error.RedirectionException;
import fhv.team11.project.ems.commons.validation.model.IModelAttributeName;
import fhv.team11.project.ems.events.transfer.EventWizard;
import jakarta.servlet.http.HttpSession;


public interface IHandleEventWizard extends IHandleWizard<EventWizard> {
    @Override
    default EventWizard initWizard(HttpSession session) {
        EventWizard wizard = new EventWizard();
        session.setAttribute(wizard.getModelAttributeName(), wizard);
        return wizard;
    }

    @Override
    default EventWizard initOrGetWizard(HttpSession session) {
        try {
            return getWizard(session);
        } catch (Exception e) {
            return initWizard(session);
        }
    }

    @Override
    default EventWizard getWizard(HttpSession session) {
        Object wizardObject = session.getAttribute(IModelAttributeName.getModelAttributeName(EventWizard.class));

        if (wizardObject == null) {
            throw new RedirectionException("Session is invalid");
        } else {
            try {
                return (EventWizard) wizardObject;
            } catch (ClassCastException e) {
                System.out.println(("wizard session attribute was set with invalid class"));
                throw new RedirectionException("Session is invalid");
            }
        }
    }
}
