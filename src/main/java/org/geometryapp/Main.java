package org.geometryapp;

import twod.*;
import threed.*;
import geometryutils.GeometryUtils;

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
        GeometryUtils.ShapeCollectionResult separated = GeometryUtils.separate2DAnd3D(shapes);
        System.out.println("2D:");
        separated.getTwoDShapes().forEach(shape -> System.out.println("  - " + shape));
        System.out.println("3D:");
        separated.getThreeDShapes().forEach(shape -> System.out.println("  - " + shape));

        System.out.printf("Compare circle and rectangle by area: %d\n",
                GeometryUtils.compareByArea(shapes.get(0), shapes.get(1)));

        GeometryUtils.findMaxArea(shapes).ifPresent(shape ->
                System.out.printf("Shape with max area: %s (%.2f)\n",
                        shape.getName(), shape.calculateArea()));

        GeometryUtils.findMinPerimeter(shapes).ifPresent(shape ->
                System.out.printf("Shape with max perimeter: %s (%.2f)\n",
                        shape.getName(), shape.calculatePerimeter()));

        GeometryUtils.findMaxVolume(shapes).ifPresent(shape ->
                System.out.printf("Max volume: %s (%.2f)\n",
                        shape.getName(), shape.calculateVolume()));

        System.out.printf("Total area: %.2f\n",
                GeometryUtils.calculateTotalArea(shapes));
        System.out.printf("Summary perimeter: %.2f\n",
                GeometryUtils.calculateTotalPerimeter(shapes));
        System.out.printf("Summary volume: %.2f\n",
                GeometryUtils.calculateTotalVolume(shapes));
    }
}