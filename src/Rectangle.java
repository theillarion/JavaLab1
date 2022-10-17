public class Rectangle extends QGon
{
	public Rectangle(Point2D[] p) throws IllegalArgumentException
	{
		super(p);
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
		return String.format("Figure: Rectangle; Points: {%s}", str);
	}
}
