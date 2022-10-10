import java.io.IOException;
import java.util.*;

public class Main
{
    private static String getType(IShape shape)
    {
        if (shape instanceof Segment)
            return "Segment";
        else if (shape instanceof Polyline)
            return "Polyline";
        else if (shape instanceof TGon)
            return "TGon";
        else if (shape instanceof Rectangle)
            return "Rectangle";
        else if (shape instanceof Trapeze)
            return "Trapeze";
        else if (shape instanceof QGon)
            return "QGon";
        else if (shape instanceof NGon)
            return "NGon";
        else
            return "Circle";
    }
    private static void getInput(Scanner in, List<IShape> shapes, String nameFigure)
    {
        Point2D[] arr;
        int size;
        double radius = 0;
        switch (nameFigure)
        {
            case "Segment" -> size = 2;
            case "TGon" -> size = 3;
            case "QGon", "Rectangle", "Trapeze" -> size = 4;
            case "Circle" -> {
                System.out.println("Введите радиус: ");
                radius = in.nextDouble();
                size = 1;
            }
            case "Polyline", "NGon" -> {
                System.out.println("Введите кол-во точек: ");
                size = in.nextInt();
            }
            default -> {
                System.out.println("Неверный тип!");
                return;
            }
        }
        arr = new Point2D[size];
        System.out.println("Введите точки: ");
        for (int j = 0; j < size; j++)
            arr[j] = new Point2D(new double[] {in.nextDouble(), in.nextDouble()});
        switch (nameFigure)
        {
            case "Segment" -> shapes.add(new Segment(arr[0], arr[1]));
            case "Polyline" -> shapes.add(new Polyline(arr));
            case "NGon" -> shapes.add(new NGon(arr));
            case "TGon" -> shapes.add(new TGon(arr));
            case "QGon" -> shapes.add(new QGon(arr));
            case "Rectangle" -> shapes.add(new Rectangle(arr));
            case "Trapeze" -> shapes.add(new Trapeze(arr));
            case "Circle" -> shapes.add(new Circle(arr[0], radius));
        }
    }
    public static void main(String[] args)
    {
        List<IShape> shapes = new ArrayList<>();
        List<IShape> shapes2 = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int N = 0;
        double square = 0;
        double length = 0;

        try
        {
            System.out.println("Введите кол-во фигур: ");
            N = in.nextInt();
            for (int i = 0; i < N; i++) {
                System.out.println("Введите название фугур: ");
                getInput(in, shapes, in.next());
            }

            for (var shape : shapes) {
                square += shape.square();
                length += shape.length();
            }
            System.out.println("Square all shapes: " + square);
            System.out.println("Length all shapes: " + length);
            System.out.println("Middle square all shapes: " + square / shapes.size());

            for (var shape : shapes) {
                String nameFigure = getType(shape);
                System.out.println("Введите характеристики фигуры " + nameFigure + ": ");
                getInput(in, shapes2, nameFigure);
            }

            for (int i = 0; i < N; i++) {
                System.out.println("Фигура 1: " + shapes.get(i));
                System.out.println("Фигура 2: " + shapes2.get(i));
                System.out.println("Пересекаются: " + shapes.get(i).cross(shapes2.get(i)));
            }

            for (var shape : shapes2) {
                System.out.println("Введите тип движения для фугуры " + getType(shape) + ": ");
                String move = in.next();
                switch (move) {
                    case "rotate", "rot" -> {
                        System.out.println("Введите угол поворота: ");
                        shape.rot(in.nextDouble());
                    }
                    case "shift" -> {
                        System.out.println("Введите смещение (двухмерную точку): ");
                        shape.shift(new Point2D(new double[]{in.nextDouble(), in.nextDouble()}));
                    }
                    case "symmetry", "sym" -> {
                        System.out.println("Введите симметрию относительно оси: ");
                        shape.symAxis(in.nextInt());
                    }
                    default -> System.out.println("Неправильный тип движения! ");
                }
            }

            for (int i = 0; i < N; i++) {
                System.out.println("Фигура 1: " + shapes.get(i));
                System.out.println("Фигура 2: " + shapes2.get(i));
                System.out.println("Пересекаются: " + shapes.get(i).cross(shapes2.get(i)));
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e + ": " + e.getMessage());
        }
    }
}