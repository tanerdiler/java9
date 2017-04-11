package com.collectionfactories;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class Sample
{
	public static void main(String[] args)
	{
		List<Integer> listOfNumbers = List.of(1, 2, 3, 4, 5);

		Set<Integer> setOfNumbers = Set.of(1, 2, 3, 4, 5);

		Map<String, String> mapOfString =
				Map.of("key1", "value1", "key2", "value2");

		Map<String, String> moreMapOfString =
				Map.ofEntries(
						Map.entry("key1", "value1"),
						Map.entry("key2", "value2")
				);

		StackWalker.getInstance().walk(s ->
				s.map( frame-> frame.getClassName()+"/"+frame.getMethodName())
						.filter(name -> name.startsWith("de.exxcellent"))
						.limit(10)
						.collect(Collectors.toList()) );
	}

}
