package fhv.team11.project.ems.domain;

import java.util.*;

public class CollectionConverter {

    public static <T> List<T> toUnmodifiableList(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList<>(list));
    }

    public static <T> Set<T> toUnmodifiableSet(Set<T> set) {
        if (set == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(set));
    }

    public static <K, V> Map<K, V> toUnmodifiableMap(Map<K, V> map) {
        if (map == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(new HashMap<>(map));
    }


    public static <T> SortedSet<T> toUnmodifiableSortedSet(SortedSet<T> sortedSet) {
        if (sortedSet == null) {
            return Collections.emptySortedSet();
        }
        return Collections.unmodifiableSortedSet(new TreeSet<>(sortedSet));
    }

    public static <K, V> SortedMap<K, V> toUnmodifiableSortedMap(SortedMap<K, V> sortedMap) {
        if (sortedMap == null) {
            return Collections.emptySortedMap();
        }
        return Collections.unmodifiableSortedMap(new TreeMap<>(sortedMap));
    }

    public static <T> List<T> arrayToUnmodifiableList(T[] array) {
        if (array == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }
}
