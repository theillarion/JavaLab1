public class Segment
{
	protected Point2D start;
	protected Point2D finish;

	public Segment(Point2D s, Point2D f)
	{
		start = new Point2D(s.getX());
		finish = new Point2D(f.getX());
	}

	public double length()
	{
		return Math.sqrt(Math.pow(finish.x[0] - start.x[0], 2)
			+ Math.pow(finish.x[1] - start.x[1], 2));
	}

	public Segment shift(Point2D a)
	{
		start.add(a);
		finish.add(a);
		return this;
	}

	public Segment rot(double phi)
	{
		start.rot(phi);
		finish.rot(phi);
		return this;
	}

	public Segment symAxis(int i)
	{
		start.symAxis(i);
		finish.symAxis(i);
		return this;
	}

	public boolean cross(IShape i)
	{
		// NOT WORK
		return true;
	}

	@Override
	public String toString()
	{
		// NOT WORK
		return "";
	}
}
