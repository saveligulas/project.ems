package fhv.team11.project.ems.domain.commons.interfaces;

public interface IValidator<T> {
    void isValid(T value);
}
