package com.occ.venkata.scoring;

import java.util.Arrays;

public class LexicalScorer implements Scorable {

	private ScoringInputSource source = MapScoringInputSource.create();

	private LexicalScorer() {
	}

	public static LexicalScorer create() {
		return new LexicalScorer();
	}
	
	public LexicalScorer withInputSource(final ScoringInputSource inputSource) {
		setScoringInputSource(inputSource);
		return this;
	}

	public void setScoringInputSource(final ScoringInputSource inputSource) {
		if (inputSource != null) {
			this.source = inputSource;
		}
	}

	public long score(final String input) {
		return Arrays.stream(input.split("")).mapToLong(source::score).sum();

	}

	public long scoreWithPosition(final String input, final long position) {
		return score(input) * position;
	}
}
