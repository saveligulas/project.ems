package fhv.team11.project.ems.commons.controller;

import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public interface IHandleRowPresentation {
    default <T> List<List<T>> listToRows(List<T> list, int itemsInRow, ModelAndView modelAndView) {
        List<List<T>> rows = new ArrayList<>();
        for (int i = 0; i < list.size(); i += itemsInRow) {
            rows.add(list.subList(i, Math.min(i + itemsInRow, list.size())));
        }
        addRowCountAndSizeToModel(rows.size(), rows.get(0).size(), modelAndView);
        return rows;
    }

    default void addRowCountAndSizeToModel(int rowCount, int rowSize, ModelAndView modelAndView) {
        modelAndView.addObject("rowCount", rowCount);
        modelAndView.addObject("rowSize", rowSize);
    }
}
