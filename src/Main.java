import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        var result = Point3D.cross_prod(new Point3D(new double[] {1, 2, 3}),
            new Point3D(new double[] {4, 5, 6}));
        System.out.println(result.toString());
    }
}
