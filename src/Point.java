import java.util.Arrays;

public class Point
{
	protected int		dim;
	protected double[]	x;

	public Point(Point p)
	{
		this.dim = p.dim;
		this.x = new double[this.dim];
		System.arraycopy(p.x, 0, this.x, 0, this.dim);
	}

	public Point(int dim)
	{
		this.dim = dim;
		this.x = new double[this.dim];
		Arrays.fill(this.x, 0);
	}

	public Point(int dim, double[] x)
	{
		this.dim = dim;
		this.x = new double[this.dim];
		System.arraycopy(x, 0, this.x, 0, this.dim);
	}

	public int getDim()
	{
		return dim;
	}

	public double[] getX()
	{
		return x;
	}

	public double getX(int i) throws ArrayIndexOutOfBoundsException
	{
		if (i < 0 || i >= dim)
			throw new ArrayIndexOutOfBoundsException();
		return x[i];
	}

	public void setX(double[] x) throws IllegalArgumentException
	{
		if (this.dim != x.length)
			throw new IllegalArgumentException();
		System.arraycopy(x, 0, this.x, 0, this.dim);
	}

	public void setX(double x, int i) throws ArrayIndexOutOfBoundsException
	{
		if (i < 0 || i >= this.dim)
			throw new ArrayIndexOutOfBoundsException();
		this.x[i] = x;
	}

	public double abs()
	{
		double result = 0.d;
		for (int i = 0; i < this.dim; ++i)
			result += Math.pow(this.x[i], 2);
		return Math.sqrt(result);
	}

	static public Point add(Point a, Point b) throws IllegalArgumentException
	{
		if (a.dim != b.dim)
			throw new IllegalArgumentException();
		double[] c = new double[a.dim];
		for (int i = 0; i < a.dim; i++)
			c[i] = a.x[i] + b.x[i];
		return new Point(a.dim, c);
	}

	Point add(Point a) throws IllegalArgumentException
	{
		if (this.dim != a.dim)
			throw new IllegalArgumentException();
		for (int i = 0; i < this.dim; i++)
			this.x[i] += a.x[i];
		return this;
	}

	static public Point sub(Point a, Point b) throws IllegalArgumentException
	{
		if (a.dim != b.dim)
			throw new IllegalArgumentException();
		double[] c = new double[a.dim];
		for (int i = 0; i < a.dim; i++)
			c[i] = a.x[i] - b.x[i];
		return new Point(a.dim, c);
	}

	public Point sub(Point a) throws IllegalArgumentException
	{
		if (this.dim != a.dim)
			throw new IllegalArgumentException();
		for (int i = 0; i < this.dim; i++)
			this.x[i] -= a.x[i];
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
		for (int i = 0; i < this.dim; i++)
			this.x[i] *= r;
		return this;
	}

	static public double mult(Point a,Point b) throws IllegalArgumentException
	{
		if (a.dim != b.dim)
			throw new IllegalArgumentException();
		double result = 0;
		for (int i = 0; i < a.dim; i++)
			result += a.x[i] * b.x[i];
		return Math.sqrt(result);
	}

	public double mult(Point b) throws IllegalArgumentException
	{
		if (this.dim != b.dim)
			throw new IllegalArgumentException();
		double result = 0;
		for (int i = 0; i < this.dim; i++)
			result += this.x[i] * b.x[i];
		return Math.sqrt(result);
	}

	static public Point symAxis(Point a, int i) throws ArrayIndexOutOfBoundsException
	{
		if (i < 0 || i >= a.dim)
			throw new ArrayIndexOutOfBoundsException();
		double[] new_x = new double[a.dim];
		for (int j = 0; j < a.dim; j++)
			if (j != i)
				new_x[j] = -a.x[j];
		return new Point(a.dim, new_x);
	}

	public Point symAxis(int i) throws ArrayIndexOutOfBoundsException
	{
		if (i < 0 || i >= this.dim)
			throw new ArrayIndexOutOfBoundsException();
		for (int j = 0; j < this.dim; j++)
			if (j != i)
				this.x[j] = -x[j];
		return this;
	}

	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < dim; i++)
			str.append(x[i]).append(" ");
		return String.format("[ %s]", str);
	}
}
