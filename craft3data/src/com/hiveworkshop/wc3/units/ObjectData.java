package com.hiveworkshop.wc3.units;

import java.util.Set;

public interface ObjectData {
	GameObject get(String id);
	void setValue(String id, String field, String value);
	Set<String> keySet();
}
