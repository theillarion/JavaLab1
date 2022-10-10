public class Segment extends OpenFigure
{
	protected Point2D start;
	protected Point2D finish;

	public Segment(Point2D s, Point2D f)
	{
		this.start = new Point2D(s.getX());
		this.finish = new Point2D(f.getX());
	}

	public Point2D getStart()
	{
		return this.start;
	}

	public void setStart(Point2D a)
	{
		this.start = a;
	}

	public Point2D getFinish()
	{
		return this.finish;
	}

	public void setFinish(Point2D a)
	{
		this.finish = a;
	}

	@Override
	public Segment shift(Point2D a)
	{
		this.start.add(a);
		this.finish.add(a);
		return this;
	}

	@Override
	public Segment rot(double phi)
	{
		this.start.rot(phi);
		this.finish.rot(phi);
		return this;
	}

	@Override
	public Segment symAxis(int i)
	{
		this.start.symAxis(i);
		this.finish.symAxis(i);
		return this;
	}
	@Override
	public double length()
	{
		return Math.sqrt(Math.pow(this.finish.x[0] - this.start.x[0], 2)
			+ Math.pow(this.finish.x[1] - this.start.x[1], 2));
	}

	static private boolean CrossPointWithSegment(Segment line, Point2D point)
	{
		Segment first_line = new Segment(line.getStart(), point);
		Segment second_line = new Segment(line.getFinish(), point);
		return first_line.length() + second_line.length() == line.length();
	}

	static private double[] getCoefEquation(Segment s)
	{
		// 0 - k, b - 1
		double[] coef = new double[2];
		coef[0] = ((s.getFinish().getX(1) - s.getStart().getX(1)) / (s.getFinish().getX(0) - s.getStart().getX(0)));
		coef[1] = s.getFinish().getX(1) - coef[0] * s.getFinish().getX(0);
		return coef;
	}
	static public double calc2Determinant(double[][] matrix)
	{
		return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
	}

	@Override
	public boolean cross(IShape i) throws IllegalArgumentException
	{
		if (i instanceof Segment segment)
		{
			double k1 = getCoefEquation(this)[0];
			double b1 = getCoefEquation(this)[1];
			double k2 = getCoefEquation(segment)[0];
			double b2 = getCoefEquation(segment)[1];
			var denominator = calc2Determinant(new double[][] {{-k1, 1}, {-k2, 1}});

			if (denominator == 0)
				return CrossPointWithSegment(this, segment.getStart())
					|| CrossPointWithSegment(this, segment.getFinish());

			double x0 = calc2Determinant(new double[][] {{1, -b1}, {1, -b2}}) / denominator;
			double y0 = calc2Determinant(new double[][] {{-b1, -k1}, {-b2, -k2}}) / denominator;
			Point2D cross_point = new Point2D(new double[] {x0, y0});
			return CrossPointWithSegment(this, cross_point) || CrossPointWithSegment(segment, cross_point);
		}
		else if (i instanceof Polyline polyline)
		{
			for (int j = 0; j + 1 < polyline.getN(); j++)
				if (this.cross(new Segment(polyline.getP(j), polyline.getP(j + 1))))
					return true;
			return false;
		}
		else if (i instanceof NGon ngon)
		{
			for (int j = 0; j + 1 < ngon.getN(); j++)
				if (this.cross(new Segment(ngon.getP(j), ngon.getP(j + 1))))
					return true;
			return (this.cross(new Segment(ngon.getP(ngon.getN() - 1), ngon.getP(0))));
		}
		else if (i instanceof Circle circle)
		{
			var ACenterLength = new Segment(this.getStart(), circle.getP()).length();
			var BCenterLength = new Segment(this.getStart(), circle.getP()).length();
			var CCenterLength = new Segment
			(
				new Point2D(
					new double[]
					{
						this.getStart().getX(0) + ((this.getFinish().getX(0) - this.getStart().getX(0)) / 2.0),
						this.getStart().getX(1) + ((this.getFinish().getX(1) - this.getStart().getX(1)) / 2.0)
					}
				),
				circle.getP()
			).length();

			// случай 2 и 6
			if ((ACenterLength >= circle.getR() & BCenterLength <= circle.getR())
				|| (ACenterLength <= circle.getR() && BCenterLength >= circle.getR()))
				return true;
			// случай 3 и 5
			else return ACenterLength > circle.getR() & BCenterLength > circle.getR()
				& CCenterLength <= circle.getR();
		}
		throw new IllegalArgumentException("Shape is not found");
	}

	public String toString()
	{
		return String.format("Figure: Segment; Points: {%s %s}", this.start, this.finish);
	}
}
