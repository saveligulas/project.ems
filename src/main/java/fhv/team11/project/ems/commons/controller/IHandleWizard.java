package fhv.team11.project.ems.commons.controller;

import fhv.team11.project.ems.events.transfer.EventWizard;
import jakarta.servlet.http.HttpSession;

public interface IHandleWizard<T extends IWizard> {
    T initWizard(HttpSession session);

    T initOrGetWizard(HttpSession session);

    T getWizard(HttpSession session);

    default void updateWizard(HttpSession session, T t) {
        session.setAttribute(t.getModelAttributeName(), t);
    }
}
