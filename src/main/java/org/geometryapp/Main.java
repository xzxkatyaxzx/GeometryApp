package org.geometryapp;

import org.example.*;
import org.example.threed.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(5.0));
        shapes.add(new Rectangle(4.0, 6.0));
        shapes.add(new Triangle(3.0, 4.0, 5.0));
        shapes.add(new Cube(4.0));
        shapes.add(new Sphere(3.0));
        shapes.add(new Cylinder(3.0, 5.0));

        // geometry utils
        System.out.println("=== Статистика ===");

        GeometryUtils.ShapeCollectionResult separated = GeometryUtils.separate2DAnd3D(shapes);
        System.out.println("2D фигуры:");
        separated.getTwoDShapes().forEach(shape -> System.out.println("  - " + shape));
        System.out.println("3D фигуры:");
        separated.getThreeDShapes().forEach(shape -> System.out.println("  - " + shape));

        System.out.printf("Сравнение круга и треугольника по площади: %d\n",
                GeometryUtils.compareByArea(shapes.get(0), shapes.get(1)));

        GeometryUtils.findMaxArea(shapes).ifPresent(shape ->
                System.out.printf("Фигура с максимальной площадью: %s (%.2f)\n",
                        shape.getName(), shape.calculateArea()));

        GeometryUtils.findMinPerimeter(shapes).ifPresent(shape ->
                System.out.printf("Фигура с минимальным периметром: %s (%.2f)\n",
                        shape.getName(), shape.calculatePerimeter()));

        GeometryUtils.findMaxVolume(shapes).ifPresent(shape ->
                System.out.printf("Максимальный объем: %s (%.2f)\n",
                        shape.getName(), shape.calculateVolume()));

        System.out.printf("Общая площадь всех фигур: %.2f\n",
                GeometryUtils.calculateTotalArea(shapes));
        System.out.printf("Общий периметр всех фигур: %.2f\n",
                GeometryUtils.calculateTotalPerimeter(shapes));
        System.out.printf("Общий объем: %.2f\n",
                GeometryUtils.calculateTotalVolume(shapes));
    }
}