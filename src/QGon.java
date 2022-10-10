public class QGon extends NGon
{
	public QGon(Point2D[] p) throws IllegalArgumentException
	{
		super(p);
		if (this.n != 4)
			throw new IllegalArgumentException("Object must be 4 points");
	}

	@Override
	public double square()
	{
		return super.square();
	}
}
