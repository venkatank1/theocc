package com.occ.venkata.scoring.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.occ.venkata.scoring.Scorable;

public class ScoringUtil {
	private ScoringUtil() {
	}
	
	public static ScoringUtil create() {
		return new ScoringUtil();
	}
	
	public long sum(final List<String> names, final Scorable scorer, final long offset) {
		AtomicLong index = new AtomicLong(offset + 1);
		return names.stream()
				.sequential()
				.map(scorer :: score)
				.mapToLong(score -> (score * index.getAndIncrement()))
				.sum();
	}
}
