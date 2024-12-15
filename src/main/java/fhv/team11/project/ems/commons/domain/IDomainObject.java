package fhv.team11.project.ems.commons.domain;

public interface IDomainObject {
    void checkFieldErrors() throws DomainInstantiationException;
    boolean isInstantiated();
}
