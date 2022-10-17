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

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.n; i++)
			str.append(this.p[i].toString()).append(" ");
		return String.format("Figure: QGon; Points: {%s}", str);
	}
}
