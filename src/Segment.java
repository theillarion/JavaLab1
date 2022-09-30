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


	@Override
	public boolean cross(IShape i)
	{
		return true;
	}

	public String toString()
	{
		// NOT WORK
		return "Start: " + start.toString() + "; Finish: " + finish.toString();
	}
}
