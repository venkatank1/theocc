package com.occ.venkata.scoring.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterToOffsetMapper implements Mappable{
	
	private CharacterToOffsetMapper() {
	}
	
	public static CharacterToOffsetMapper create() {
		return new CharacterToOffsetMapper();
	}
	
	public Map<String, Long> map(final Map<Object, List<String>> namesByFirstCharacter) {
		final Map<String, Long> offsetsByCharacter = new HashMap<>();
		long offset = 0;
		for(int i = 'A'; i <= 'Z'; i++) {
			offsetsByCharacter.put(String.valueOf((char)i), offset);
			offset += namesByFirstCharacter.get(String.valueOf((char)i)).size();
				
		}
		return offsetsByCharacter;
	}
}
