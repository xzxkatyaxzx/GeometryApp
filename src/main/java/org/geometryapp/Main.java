package org.geometryapp;

import org.example.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(5.0));
        shapes.add(new Rectangle(4.0, 6.0));
        shapes.add(new Triangle(3.0, 4.0, 5.0));
        shapes.add(new Circle(2.5));
        shapes.add(new Rectangle(5.0, 5.0));
        shapes.add(new Triangle(6.0, 6.0, 6.0));

        System.out.println("=== Все фигуры ===");
        for (Shape shape : shapes) {
            System.out.println(shape);
            System.out.printf("  Площадь: %.2f, Периметр: %.2f\n\n",
                    shape.calculateArea(), shape.calculatePerimeter());
        }

        System.out.println("=== Статистика ===");
        printStatistics(shapes);

        // geometry utils
        Circle circle = new Circle(5.0);
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);

        System.out.printf("Сравнение круга и треугольника по площади: %d\n",
                GeometryUtils.compareByArea(circle, triangle));

        GeometryUtils.findMaxArea(shapes).ifPresent(shape ->
                System.out.printf("Фигура с максимальной площадью: %s (%.2f)\n",
                        shape.getName(), shape.calculateArea()));

        GeometryUtils.findMinPerimeter(shapes).ifPresent(shape ->
                System.out.printf("Фигура с минимальным периметром: %s (%.2f)\n",
                        shape.getName(), shape.calculatePerimeter()));

        System.out.printf("Общая площадь всех фигур: %.2f\n",
                GeometryUtils.calculateTotalArea(shapes));
        System.out.printf("Общий периметр всех фигур: %.2f\n",
                GeometryUtils.calculateTotalPerimeter(shapes));
    }

    public static void printStatistics(List<Shape> shapes) {
        double totalArea = 0;
        double totalPerimeter = 0;

        for (Shape shape : shapes) {
            totalArea += shape.calculateArea();
            totalPerimeter += shape.calculatePerimeter();
        }

        System.out.printf("Общая площадь всех фигур: %.2f\n", totalArea);
        System.out.printf("Общий периметр всех фигур: %.2f\n", totalPerimeter);
        System.out.printf("Средняя площадь: %.2f\n", totalArea / shapes.size());
    }
}