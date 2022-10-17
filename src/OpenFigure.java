public abstract class OpenFigure implements IShape
{
	@Override
	public double square()
	{
		return 0;
	}

	public OpenFigure clone()
	{
		try
		{
			return (OpenFigure) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new AssertionError();
		}
	}
}
