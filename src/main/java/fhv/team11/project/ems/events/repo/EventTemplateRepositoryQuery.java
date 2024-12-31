package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.database.IRepository;

import java.util.List;

public interface EventTemplateRepositoryQuery extends IRepository<EventTemplateEntity, Long> {
    List<EventTemplateEntity> listNumberOfBlueprints(int num);
    /**
     *
     * @param pageNumber the <code>pageNumber</code> to retrieve
     * @param pageSize the <code>pageSize</code> of event templates to return
     * @param userId the <code>userId</code> of the user whose events to retrieve
     * @return the <code>pageSize</code> of event templates with the <code>pageNumber</code> offset for the user with <code>userId</code>
     */
    List<EventTemplateEntity> getEventTemplatesForPageNumber(int pageNumber, int pageSize, Long userId);
    EventTemplateEntity getEventTemplateByName(String templateName);
}
