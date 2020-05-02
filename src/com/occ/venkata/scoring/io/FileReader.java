package com.occ.venkata.scoring.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.occ.venkata.scoring.util.Delimiter;

public class FileReader implements InputSourceReader<String>{
	
	private String absoluteFilePath;

	private FileReader(final String absoluteFilePath) {
		this.absoluteFilePath = absoluteFilePath;
	}
	
	public final List<String> read() {
		List<String> sortedNames; 
		try (Stream<String> s = Files.lines(Paths.get(absoluteFilePath))) {
			sortedNames =  s.map(str -> str.split(Delimiter.COMMA.value()))
				    .flatMap(Arrays::stream)
				    .map(str -> str.substring(1, str.length() - 1))
				    .sorted()
				    .collect(Collectors.toList());
				    
		} catch (IOException e) {
			throw new RuntimeException("Exception while reading file:", e);
		}
		return sortedNames;
	}

	public static InputSourceReader<String> with(String absoluteFilePath) {
		return new FileReader(absoluteFilePath);
	}
}
