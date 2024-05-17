package com.li.principle.ocp;

/**
 * @author LiXL
 * @date 2024/1/2
 */
public class GraphicEditorDemo1 {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
    }
}

// 调用方
class GraphicEditor {
    public void drawShape(Shape shape) {
        if (shape.type == 1) {
            drawRectangle();
        } else if (shape.type == 2) {
            drawCircle();
        } else if (shape.type == 3) {
            drawTriangle();
        }
    }
    private void drawRectangle() {
        System.out.println("绘制矩形");
    }

    private void drawCircle() {
        System.out.println("绘制圆形");
    }

    private void drawTriangle() {
        System.out.println("绘制三角形");
    }
}

class Shape {
    int type;
}

class Rectangle extends Shape {
    Rectangle() {
        super.type = 1;
    }
}

class Circle extends Shape {
    Circle() {
        super.type = 2;
    }
}

class Triangle extends Shape {
    Triangle() {
        super.type = 3;
    }
}
