package com.occ.venkata.scoring.mapper;

import java.util.List;
import java.util.Map;

public interface Mappable {
	Map<String, Long> map(Map<Object, List<String>> namesByFirstCharacter);
}
