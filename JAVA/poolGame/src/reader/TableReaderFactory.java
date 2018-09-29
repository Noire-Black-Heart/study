package reader;

public class TableReaderFactory implements AbstractReaderFactory {

	@Override
	public Reader CreateReader() {
		// TODO Auto-generated method stub
		return new TableReader();
	}

}
