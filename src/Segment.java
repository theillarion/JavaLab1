public class Segment extends OpenFigure
{
	protected Point2D start;
	protected Point2D finish;

	public Segment(Point2D s, Point2D f)
	{
		start = new Point2D(s.getX());
		finish = new Point2D(f.getX());
	}

	public Point2D getStart()
	{
		return start;
	}

	public void setStart(Point2D a)
	{
		start = a;
	}

	public Point2D getFinish()
	{
		return finish;
	}

	public void setFinish(Point2D a)
	{
		finish = a;
	}

	@Override
	public Segment shift(Point2D a)
	{
		start.add(a);
		finish.add(a);
		return this;
	}

	@Override
	public Segment rot(double phi)
	{
		start.rot(phi);
		finish.rot(phi);
		return this;
	}

	@Override
	public Segment symAxis(int i)
	{
		start.symAxis(i);
		finish.symAxis(i);
		return this;
	}
	@Override
	public double length()
	{
		return Math.sqrt(Math.pow(finish.x[0] - start.x[0], 2)
			+ Math.pow(finish.x[1] - start.x[1], 2));
	}

	static public boolean cross_point_segment(Segment line, Point2D point)
	{
		Segment first_line = new Segment(line.getStart(), point);
		Segment second_line = new Segment(line.getFinish(), point);
		return first_line.length() + second_line.length() == line.length();
	}

	static public double[] get_equation_line(Segment s)
	{
		double[] coef = new double[2];
		double k = ((s.getFinish().getX(1) - s.getStart().getX(1))/(s.getFinish().getX(0) - s.getStart().getX(0)));
		double b = s.getFinish().getX(1) - k*s.getFinish().getX(0);
		coef[0] = k;
		coef[1] = b;
		return coef;
	}
	static public double calc_2_det(double[][] matrix)
	{
		return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
	}

	@Override
	public boolean cross(IShape i) throws IllegalArgumentException
	{
		if (i instanceof Segment segment)
		{
			double k1 = get_equation_line(this)[0];
			double b1 = get_equation_line(this)[1];
			double k2 = get_equation_line(segment)[0];
			double b2 = get_equation_line(segment)[1];
			var znamenatel = calc_2_det(new double[][] {{-k1, 1}, {-k2, 1}});

			if (znamenatel == 0)
				return cross_point_segment(this, segment.getStart())
					|| cross_point_segment(this, segment.getFinish());

			double x0 = calc_2_det(new double[][] {{1, -b1}, {1, -b2}}) / znamenatel;
			double y0 = calc_2_det(new double[][] {{-b1, -k1}, {-b2, -k2}}) / znamenatel;
			Point2D cross_point = new Point2D(new double[] {x0, y0});
			return cross_point_segment(this, cross_point) || cross_point_segment(segment, cross_point);
		}
		else if (i instanceof Polyline polyline)
		{
			for (int j = 0; j + 1 < polyline.getN(); j++)
				if (cross(new Segment(polyline.getP(j), polyline.getP(j + 1))))
					return true;
			return false;
		}
		else if (i instanceof NGon ngon)
		{
			for (int j = 0; j + 1 < ngon.getN(); j++)
				if (cross(new Segment(ngon.getP(j), ngon.getP(j + 1))))
					return true;
			return (cross(new Segment(ngon.getP(ngon.getN() - 1), ngon.getP(0))));
		}
		else if (i instanceof Circle)
		{
			//
		}
		throw new IllegalArgumentException("Shape is not found");
	}

	public String toString()
	{
		return String.format("Figure: Segment; Points: {%s %s}", start.toString(), finish.toString());
	}
}
