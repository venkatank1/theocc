package com.occ.venkata.scoring.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.occ.venkata.scoring.Scorable;
import com.occ.venkata.scoring.io.InputSourceReader;
import com.occ.venkata.scoring.mapper.Mappable;
import com.occ.venkata.scoring.util.ScoringUtil;

public class ScoringController {
	
	private Scorable scorer = null;
	private InputSourceReader<String> inputSourceReader = null;
	private Mappable mapper;
		
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
	
	public ScoringController withMapper(final Mappable mapper) {
		if(mapper == null) {
			throw new RuntimeException("Mapper cannot be null.");
		}
		this.mapper = mapper;
		return this;
	}
	
	public final long score() {
		Map<Object, List<String>> namesByFirstCharacter = inputSourceReader.read()
																		.stream()
																		.collect(Collectors.groupingBy(s -> s.substring(0, 1)));
		
		final Map<String, Long> offsetsByCharacter = mapper.map(namesByFirstCharacter);
		
		final long sum = namesByFirstCharacter.entrySet().stream()
														.mapToLong(entry -> ScoringUtil.create().sum(entry.getValue(), scorer, offsetsByCharacter.get(entry.getKey().toString())))
														.sum();
		return sum;
		
	}
}
