public class QGon extends NGon
{
	public QGon(Point2D[] p) throws IllegalArgumentException
	{
		super(p);
		if (n != 4)
			throw new IllegalArgumentException("");
	}

	@Override
	public double square()
	{
		return super.square();
	}
}
