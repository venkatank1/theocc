package com.occ.venkata.scoring;

public interface Scorable {
	void setScoringInputSource(ScoringInputSource inputSource);
	long score(String input);
}
