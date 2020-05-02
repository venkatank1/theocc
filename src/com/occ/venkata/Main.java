package com.occ.venkata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.occ.venkata.scoring.LexicalScorer;
import com.occ.venkata.scoring.Scorable;
import com.occ.venkata.scoring.controller.FileInputScoringController;
import com.occ.venkata.scoring.io.FileReader;
import com.occ.venkata.scoring.util.ScoringUtil;

/**
 * Input : File path.
 * Sort Names.
 * Sum the alphabetical * position in the list. - COMPLETED
 * Scoring algorithm should be pluggable. - COMPLETED
 * Input source should be pluggable. - COMPLETED
 * Scoring Input should be pluggable. - PARK
 * 
 * Large Data
 * Sort
 * Read
 * 
 * 
 * 
 * @author venkatank
 *
 */

public class Main {
	
	public static final String COMMA = ",";
	
	private static Scorable scoringAlgorithm = LexicalScorer.create();
	private static ScoringUtil scorer = ScoringUtil.create();
			
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		final long sum = FileInputScoringController.withFilePath(args[0])
													.score();
		System.out.println("Time Taken for Scoring : "+(System.currentTimeMillis() - start)+"ms");
		System.out.println("Sum : "+ sum);
									
		
	}
}
