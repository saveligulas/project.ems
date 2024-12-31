package fhv.team11.project.ems.commons.mapper;

public interface IDomainPresentationMapper<P, D> {
    P getView(D domain);
}
