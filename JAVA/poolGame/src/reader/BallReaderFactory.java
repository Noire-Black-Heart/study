package reader;

public class BallReaderFactory implements AbstractReaderFactory {

	@Override
	public Reader CreateReader() {
		// TODO Auto-generated method stub
		return new BallReader();
	}

}
