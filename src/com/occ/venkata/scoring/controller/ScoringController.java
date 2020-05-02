package com.occ.venkata.scoring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.occ.venkata.scoring.Scorable;
import com.occ.venkata.scoring.ScoringInputSource;
import com.occ.venkata.scoring.io.FileReader;
import com.occ.venkata.scoring.io.InputSourceReader;
import com.occ.venkata.scoring.util.ScoringUtil;

public class ScoringController {
	
	private Scorable scorer = null;
	private InputSourceReader<String> inputSourceReader = null;
		
	private ScoringController() {
	}
	
	public static final ScoringController create() {
		return new ScoringController();
	}
	
	
	public final ScoringController withInputSourceReader(final InputSourceReader<String> inputSourceReader) {
		if(inputSourceReader == null) {
			throw new RuntimeException("Input Source Reader Cannot be Null.");
		}
		this.inputSourceReader = inputSourceReader;
		return this;
	}
	
	public ScoringController withScorer(final Scorable scorer) {
		if(scorer == null) {
			throw new RuntimeException("Input Scoring algorithm implementation Cannot be Null.");
		}
		this.scorer = scorer;
		return this;
	}
	
	public final long score() {
		Map<Object, List<String>> namesByFirstCharacter = inputSourceReader.read()
																	.stream()
																	.collect(Collectors.groupingBy(s -> s.substring(0, 1)));
		final Map<String, Long> offsetsByCharacter = constructOffsetByCharacterMap(namesByFirstCharacter);
		final long sum = namesByFirstCharacter.entrySet().stream()
				.mapToLong(entry -> ScoringUtil.create().sum(entry.getValue(), scorer, offsetsByCharacter.get(entry.getKey().toString())))
				.sum();
		return sum;
		
	}
	
	private Map<String, Long> constructOffsetByCharacterMap(final Map<Object, List<String>> namesByFirstCharacter) {
		final Map<String, Long> offsetsByCharacter = new HashMap<>();
		long offset = 0;
		for(int i = 'A'; i <= 'Z'; i++) {
			offsetsByCharacter.put(String.valueOf((char)i), offset);
			offset += namesByFirstCharacter.get(String.valueOf((char)i)).size();
			
		}
		return offsetsByCharacter;
	}
	
}
