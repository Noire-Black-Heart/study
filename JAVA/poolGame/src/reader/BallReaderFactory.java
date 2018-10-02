package reader;

public class BallReaderFactory implements AbstractReaderFactory {

	@Override
	public Reader CreateReader() {
		return new BallReader();
	}

}
