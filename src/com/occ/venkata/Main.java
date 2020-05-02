package com.occ.venkata;

import com.occ.venkata.scoring.controller.FileInputScoringController;

/**
 * @author venkatank
 *
 */

public class Main {
	public static void main(String args[]) {
		final long start = System.currentTimeMillis();
		
		if(args == null || args.length == 0) {
			throw new RuntimeException("Input source path should be provided.");
		}

		final long sum = FileInputScoringController.withFilePath(args[0]).score();

		System.out.println("Time Taken for Scoring : "+(System.currentTimeMillis() - start)+"ms");
		System.out.println("Sum : "+ sum);
	}
}
