package bll.validators;

/**
 *
 * @param <T> is a generic type
 */
public interface Validator<T> {

    public void validate(T t);
}
