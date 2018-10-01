package reader;

import java.util.*;

public interface Reader {
	public <T>ArrayList<T> parse(String path);
	//public void parse(String path, TableHolder table);
}
