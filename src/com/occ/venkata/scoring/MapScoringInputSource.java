package com.occ.venkata.scoring;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapScoringInputSource implements ScoringInputSource{
	private static Map<String,Integer> LEXICAL_ORDERING_MAP = new LinkedHashMap<>();
	
	static {
		LEXICAL_ORDERING_MAP = IntStream.rangeClosed('A', 'Z')
								        .boxed()
								        .collect(Collectors.toMap( x -> String.valueOf((char) x.intValue()), x -> x + (1 - 'A')));
	}
	
	private MapScoringInputSource() {
	}
	
	public static MapScoringInputSource create() {
		return new  MapScoringInputSource();
	}

	public int score(String input) {
		//@TODO : THROW EXCEPTION FOR NON EXISTING.
		return LEXICAL_ORDERING_MAP.get(input);
	}
}
