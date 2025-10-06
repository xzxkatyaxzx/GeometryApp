package org.geometryapp;

import org.twod.*;
import org.threed.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UniversalGeometryUtils {
    public static class UniversalShape {
        private final Object shape;
        private final boolean is3D;

        public UniversalShape(Shape shape2D) {
            this.shape = shape2D;
            this.is3D = false;
        }

        public UniversalShape(ThreeDimensionalShape shape3D) {
            this.shape = shape3D;
            this.is3D = true;
        }

        public boolean is3D() {
            return is3D;
        }

        public Shape get2DShape() {
            if (is3D) {
                throw new IllegalStateException("Its 3D shape");
            }
            return (Shape) shape;
        }

        public ThreeDimensionalShape get3DShape() {
            if (!is3D) {
                throw new IllegalStateException("Its 2D shape");
            }
            return (ThreeDimensionalShape) shape;
        }

        public String getName() {
            return is3D ? get3DShape().getName() : get2DShape().getName();
        }

        public double getArea() {
            return is3D ? get3DShape().calculateSurfaceArea() : get2DShape().calculateArea();
        }

        @Override
        public String toString() {
            return shape.toString();
        }
    }

    public static List<UniversalShape> createUniversalList(List<Shape> shapes2D,
                                                           List<ThreeDimensionalShape> shapes3D) {
        List<UniversalShape> result = new ArrayList<>();

        if (shapes2D != null) {
            shapes2D.forEach(shape -> result.add(new UniversalShape(shape)));
        }

        if (shapes3D != null) {
            shapes3D.forEach(shape -> result.add(new UniversalShape(shape)));
        }

        return result;
    }

    public static void printStatistics(List<UniversalShape> shapes) {
        long count2D = shapes.stream().filter(shape -> !shape.is3D()).count();
        long count3D = shapes.stream().filter(UniversalShape::is3D).count();

        double totalArea = shapes.stream().mapToDouble(UniversalShape::getArea).sum();
        double totalVolume = shapes.stream()
                .filter(UniversalShape::is3D)
                .mapToDouble(shape -> shape.get3DShape().calculateVolume())
                .sum();

        System.out.printf("2D count: %d\n", count2D);
        System.out.printf("3D count: %d\n", count3D);
        System.out.printf("Total area: %.2f\n", totalArea);
        System.out.printf("Total volume: %.2f\n", totalVolume);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes2D = Arrays.asList(
                new Circle(5.0),
                new Rectangle(4.0, 6.0),
                new Triangle(3.0, 4.0, 5.0)
        );

        List<ThreeDimensionalShape> shapes3D = Arrays.asList(
                new Cube(4.0),
                new Sphere(3.0),
                new Cylinder(2.0, 5.0)
        );

        System.out.println("2D:");
        shapes2D.forEach(System.out::println);

        System.out.println("3D:");
        shapes3D.forEach(System.out::println);

        System.out.println();

        org.twod.GeometryUtils.findMaxArea(shapes2D)
                .ifPresent(shape -> System.out.println("Max area (2D): " +
                        shape.getName() + " - " + shape.calculateArea()));

        org.threed.ThreeDUtils.findMaxVolume(shapes3D)
                .ifPresent(shape -> System.out.println("Max volume (3D): " +
                        shape.getName() + " - " + shape.calculateVolume()));

        List<UniversalGeometryUtils.UniversalShape> allShapes =
                UniversalGeometryUtils.createUniversalList(shapes2D, shapes3D);

        UniversalGeometryUtils.printStatistics(allShapes);
    }
}