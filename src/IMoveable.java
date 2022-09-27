public interface IMoveable
{
	IMoveable shift(Point2D a);
	IMoveable rot(double phi);
	IMoveable symAxis(int i);
}
