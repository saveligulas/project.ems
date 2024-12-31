package fhv.team11.project.ems.domain.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ValidationMessages {
    private static final String NULL_MESSAGE = "This field is required";
    private static final String EMPTY_STRING_MESSAGE = "Please enter some text";
    private static final String BLANK_STRING_MESSAGE = "Please enter some text, not just spaces";
    private static final String NOT_WRAPPER_MESSAGE = "Please enter a valid number";
    private static final String NOT_PRIMITIVE_MESSAGE = "Please enter a valid number";
    private static final String EMPTY_COLLECTION_MESSAGE = "Please select at least one item";
    private static final String EMPTY_MAP_MESSAGE = "Please make at least one selection";

    public static String validateNotNull(Object value) {
        return Validator.isNull(value) ? NULL_MESSAGE : "";
    }

    public static String validateNotEmpty(String value) {
        if (Validator.isNull(value)) {
            return NULL_MESSAGE;
        }
        return Validator.isEmpty(value) ? EMPTY_STRING_MESSAGE : "";
    }

    public static String validateNotBlank(String value) {
        if (Validator.isNull(value)) {
            return NULL_MESSAGE;
        }
        return Validator.isBlank(value) ? BLANK_STRING_MESSAGE : "";
    }

    public static String validateWrapper(Object value) {
        if (Validator.isNull(value)) {
            return NULL_MESSAGE;
        }
        return !Validator.isWrapper(value) ? NOT_WRAPPER_MESSAGE : "";
    }

    public static String validatePrimitive(Object value) {
        if (Validator.isNull(value)) {
            return NULL_MESSAGE;
        }
        return !Validator.isPrimitive(value) ? NOT_PRIMITIVE_MESSAGE : "";
    }

    public static String validateNotEmptyCollection(Collection<?> collection) {
        if (Validator.isNull(collection)) {
            return NULL_MESSAGE;
        }
        return Validator.isEmptyCollection(collection) ? EMPTY_COLLECTION_MESSAGE : "";
    }

    public static String validateNotEmptyMap(Map<?, ?> map) {
        if (Validator.isNull(map)) {
            return NULL_MESSAGE;
        }
        return Validator.isEmptyMap(map) ? EMPTY_MAP_MESSAGE : "";
    }

    public static List<String> validateAll(Object value) {
        List<String> messages = new ArrayList<>();

        if (value == null) {
            messages.add(NULL_MESSAGE);
            return messages;
        }

        if (value instanceof String) {
            String stringValue = (String) value;
            if (Validator.isEmpty(stringValue)) {
                messages.add(EMPTY_STRING_MESSAGE);
            }
            if (Validator.isBlank(stringValue)) {
                messages.add(BLANK_STRING_MESSAGE);
            }
        }

        if (value instanceof Collection) {
            if (Validator.isEmptyCollection((Collection<?>) value)) {
                messages.add(EMPTY_COLLECTION_MESSAGE);
            }
        }

        if (value instanceof Map) {
            if (Validator.isEmptyMap((Map<?, ?>) value)) {
                messages.add(EMPTY_MAP_MESSAGE);
            }
        }

        return messages;
    }
}
