public class NGon implements IShape, IPolyPoint
{
	protected int n;
	protected Point2D[] p;

	public NGon(Point2D[] p) throws IllegalArgumentException
	{
		if (p.length < 3)
			throw new IllegalArgumentException("");
		setP(p);
	}

	public int getN()
	{
		return this.n;
	}

	public Point2D[] getP()
	{
		return this.p;
	}

	@Override
	public Point2D getP(int i)
	{
		if (i < 0 || i >= this.n)
			throw new ArrayIndexOutOfBoundsException();
		return this.p[i];
	}

	public void setP(Point2D[] p)
	{
		this.n = p.length;
		this.p = new Point2D[this.n];
		System.arraycopy(p, 0, this.p, 0, this.n);
	}

	@Override
	public void setP(Point2D p, int i)
	{
		if (i < 0 || i >= this.n)
			throw new ArrayIndexOutOfBoundsException();
		this.p[i] = p;
	}

	@Override
	public NGon shift(Point2D a)
	{
		for (var elem : this.p)
			elem.add(a);
		return this;
	}

	@Override
	public NGon rot(double phi)
	{
		for (var elem : this.p)
			elem.rot(phi);
		return this;
	}

	@Override
	public NGon symAxis(int i)
	{
		for (var elem : this.p)
			elem.symAxis(i);
		return this;
	}

	static private double squareTriangle(Point2D a, Point2D b, Point2D c)
	{
		double x1 = Point2D.lengthTwoPoint(a, b);
		double x2 = Point2D.lengthTwoPoint(b, c);
		double x3 = Point2D.lengthTwoPoint(a, c);
		double _p = (x1 + x2 + x3) / 2;
		return Math.sqrt(_p * (_p - x1) * (_p - x2) * (_p - x3));
	}

	@Override
	public double square()
	{
		double result = 0;
		for (int i = 2; i < this.n; i++)
			result += squareTriangle(this.p[0], this.p[i - 1], this.p[i]);
		return result;
	}

	@Override
	public double length()
	{
		double result = 0;
		for (int i = 0; i + 1 < this.n; i++)
			result += Point2D.lengthTwoPoint(this.p[i], this.p[i + 1]);
		result += Point2D.lengthTwoPoint(this.p[n - 1], this.p[0]);
		return result;
	}

	@Override
	public boolean cross(IShape i) throws IllegalArgumentException
	{
		if (i instanceof NGon ngon)
		{
			for (int j = 0; j + 1 < this.getN(); j++)
				if ((new Segment(this.getP(j), this.getP(j + 1))).cross(ngon))
					return true;
			return (new Segment(this.getP(this.getN() - 1), this.getP(0))).cross(ngon);
		}
		else if (i instanceof Circle circle)
		{
			for (int j = 0; j + 1 < getN(); j++)
				if ((new Segment(this.getP(j), this.getP(j + 1))).cross(circle))
					return true;
			return (new Segment(this.getP(this.getN() - 1), this.getP(0))).cross(circle);
		}
		return i.cross(this);
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.n; i++)
			str.append(this.p[i].toString()).append(" ");
		return String.format("Figure: NGon; Points: {%s}", str);
	}
}
