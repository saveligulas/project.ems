package fhv.team11.project.ems.domain.commons;

public interface IValidator<T> {
    void isValid(T value);
}
