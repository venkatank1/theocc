package com.occ.venkata.scoring.io;

import java.util.List;

public interface InputSourceReader<T> {
	List<T> read();
}
