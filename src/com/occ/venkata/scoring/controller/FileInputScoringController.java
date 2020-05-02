package com.occ.venkata.scoring.controller;

import com.occ.venkata.scoring.LexicalScorer;
import com.occ.venkata.scoring.Scorable;
import com.occ.venkata.scoring.io.FileReader;
import com.occ.venkata.scoring.io.InputSourceReader;
import com.occ.venkata.scoring.mapper.CharacterToOffsetMapper;

public class FileInputScoringController {
	
	private InputSourceReader<String> fileReader = null;
	private Scorable scorer = null;
	
	private FileInputScoringController(final String absoluteFilePath) {
		fileReader = FileReader.with(absoluteFilePath);
		scorer = LexicalScorer.create();
	}
	
	public static FileInputScoringController withFilePath(final String absoluteFilePath) {
		if(absoluteFilePath == null || absoluteFilePath.trim().length() == 0) {
			throw new RuntimeException("File Cannot be null or empty.");
		}
		return new FileInputScoringController(absoluteFilePath);
	}
	

	public final long score() {
		long sum = ScoringController.create()
						.withInputSourceReader(fileReader)
						.withScorer(scorer)
						.withMapper(CharacterToOffsetMapper.create())
						.score();
		return sum;
	}

}
