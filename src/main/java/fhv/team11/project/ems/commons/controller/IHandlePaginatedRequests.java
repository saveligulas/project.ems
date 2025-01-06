package fhv.team11.project.ems.commons.controller;

import org.springframework.web.servlet.ModelAndView;

public interface IHandlePaginatedRequests {
    default void addPageStateToModel(int pageNumber, int pageSize, ModelAndView modelAndView) {
        modelAndView.addObject("pageNumber", pageNumber);
        modelAndView.addObject("pageSize", pageSize);
    }
}
