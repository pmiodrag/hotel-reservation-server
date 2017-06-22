/**
 * 
 */
package com.twinsoft.domain;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Miodrag Pavkovic
 */
public interface DomainOperations<T> {
	default List<T> filter(List<T> list, Predicate<T> predicate) {
		return list.stream().filter(predicate).collect(Collectors.toList());
	}
	
	default List<T> doubleFilter(List<T> list, Predicate<T> predicate, Predicate<T> secondPredicate) {
		return list.stream().filter(predicate).filter(secondPredicate).collect(Collectors.toList());
	}
	
}
