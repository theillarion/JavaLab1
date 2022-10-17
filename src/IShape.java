public interface IShape extends IMoveable, Cloneable
{
	double square();
	double length();
	boolean cross(IShape i);
	IShape clone();
}
