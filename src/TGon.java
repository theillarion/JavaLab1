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

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.n; i++)
			str.append(this.p[i].toString()).append(" ");
		return String.format("Figure: TGon; Points: {%s}", str);
	}
}
