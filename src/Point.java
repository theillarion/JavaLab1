import java.util.Arrays;

public class Point
{
	protected int		dim = 0;
	protected double[]	x;

	public Point(int dim)
	{
		this.dim = dim;
		x = new double[dim];
		Arrays.fill(x, 0);
	}

	public Point(int dim, double[] x)
	{
		this.dim = dim;
		this.x = new double[dim];
		System.arraycopy(x, 0, this.x, 0, dim);
	}

	public int getDim()
	{
		return dim;
	}

	public double[] getX()
	{
		return x;
	}

	public double getX(int i)
	{
		if (i < 0 || i >= dim)
			throw new ArrayIndexOutOfBoundsException();
		return x[i];
	}

	public void setX(double[] x)
	{
		if (dim != x.length)
			throw new IllegalArgumentException();
		System.arraycopy(x, 0, this.x, 0, dim);
	}

	public void setX(double x, int i)
	{
		if (i < 0 || i >= dim)
			throw new ArrayIndexOutOfBoundsException();
		this.x[i] = x;
	}

	public double abs()
	{
		double result = 0.d;
		for (int i = 0; i < dim; ++i)
			result += Math.pow(x[i], 2);
		return Math.sqrt(result);
	}

	static public Point add(Point a, Point b)
	{
		if (a.dim != b.dim)
			throw new IllegalArgumentException();
		double[] c = new double[a.dim];
		for (int i = 0; i < a.dim; i++)
			c[i] = a.x[i] + b.x[i];
		return new Point(a.dim, c);
	}

	Point add(Point a)
	{
		if (dim != a.dim)
			throw new IllegalArgumentException();
		x = new double[dim];
		for (int i = 0; i < dim; i++)
			x[i] += a.x[i];
		return this;
	}

	static public Point sub(Point a, Point b)
	{
		if (a.dim != b.dim)
			throw new IllegalArgumentException();
		double[] c = new double[a.dim];
		for (int i = 0; i < a.dim; i++)
			c[i] = a.x[i] - b.x[i];
		return new Point(a.dim, c);
	}

	public Point sub(Point a)
	{
		if (dim != a.dim)
			throw new IllegalArgumentException();
		x = new double[dim];
		for (int i = 0; i < a.dim; i++)
			x[i] -= a.x[i];
		return this;
	}

	static public Point mult(Point a, double r)
	{
		double[] c = new double[a.dim];
		for (int i = 0; i < a.dim; i++)
			c[i] = a.x[i] * r;
		return new Point(a.dim, c);
	}

	public Point mult(double r)
	{
		for (int i = 0; i < dim; i++)
			x[i] *= r;
		return this;
	}

	static public double mult(Point a,Point b)
	{
		if (a.dim != b.dim)
			throw new IllegalArgumentException();
		double result = 0;
		for (int i = 0; i < a.dim; i++) {
			result += a.x[i] * b.x[i];
		}
		return Math.sqrt(result);
	}

	public double mult(Point b)
	{
		if (dim != b.dim)
			throw new IllegalArgumentException();
		double result = 0;
		for (int i = 0; i < dim; i++) {
			result += x[i] * b.x[i];
		}
		return Math.sqrt(result);
	}

	static public Point symAxis(Point a, int i)
	{
		if (i < 0 || i >= a.dim)
			throw new ArrayIndexOutOfBoundsException();
		double[] new_x = new double[a.dim];
		for (int j = 0; j < a.dim; j++)
			if (j != i)
				new_x[j] = -a.x[j];
		return new Point(a.dim, new_x);
	}

	public Point symAxis(int i)
	{
		if (i < 0 || i >= dim)
			throw new ArrayIndexOutOfBoundsException();
		for (int j = 0; j < dim; j++)
			if (j != i)
				x[j] = -x[j];
		return this;
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < dim; i++)
			str.append(Double.toString(x[i])).append(" ");
		return "Dim: " + dim + "; Elements: { " + str + "}";
	}
}
