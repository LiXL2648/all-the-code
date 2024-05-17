package com.li.principle.ocp;

/**
 * @author LiXL
 * @date 2024/1/2
 */
public class GraphicEditorDemo2 {

    public static void main(String[] args) {
        GraphicEditor1 graphicEditor = new GraphicEditor1();
        graphicEditor.drawShape(new Rectangle1());
        graphicEditor.drawShape(new Circle1());
        graphicEditor.drawShape(new Triangle1());
        graphicEditor.drawShape(new OtherGraphic());
    }
}

class GraphicEditor1 {
    public void drawShape(Shape1 shape) {
        shape.drawShape();
    }
}

class Rectangle1 extends Shape1 {
    Rectangle1() {
        super.type = 1;
    }

    @Override
    public void drawShape() {
        System.out.println("绘制矩形");
    }
}

class Circle1 extends Shape1 {
    Circle1() {
        super.type = 2;
    }

    @Override
    public void drawShape() {
        System.out.println("绘制圆形");
    }
}

class Triangle1 extends Shape1 {
    Triangle1() {
        super.type = 3;
    }

    @Override
    public void drawShape() {
        System.out.println("绘制三角形");
    }
}

class OtherGraphic extends Shape1 {
    OtherGraphic() {
        super.type = 4;
    }

    @Override
    public void drawShape() {
        System.out.println("绘制其他图形");
    }
}

abstract class Shape1 {
    int type;

    abstract void drawShape();
}
