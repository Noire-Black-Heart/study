package facade;

import java.util.ArrayList;

import reader.TableHolder;
import reader.TableReaderFactory;

public class TableFacade implements Facade{
		public ArrayList<TableHolder> FacadeParse(String path) {
		
		TableReaderFactory tableFact = new TableReaderFactory();
		
		ArrayList<TableHolder> table = tableFact.CreateReader().parse(path);
		
		return table;
		
	}
}
