public class TGon extends NGon
{
	public TGon(Point2D[] p) throws IllegalArgumentException
	{
		super(p);
		if (n != 3)
			throw new IllegalArgumentException("Object must be 3 points");
	}

	@Override
	public double square()
	{
		return super.square();
	}
}
