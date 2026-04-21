package navigation.ui;

import navigation.model.Point;
import navigation.logic.SVMClassifier;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SafetyVisualizer extends JPanel {
    private List<Point> points;
    private SVMClassifier svm;
    private final int SCALE = 50;
    private final int OFFSET = 300;

    public SafetyVisualizer(List<Point> points, SVMClassifier svm) {
        this.points = points;
        this.svm = svm;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Eksenler
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine(0, OFFSET, 800, OFFSET);
        g2d.drawLine(OFFSET, 0, OFFSET, 600);

        // Engelleri Çiz
        for (Point p : points) {
            g2d.setColor(p.getLabel() == 1 ? Color.RED : Color.BLUE);
            int drawX = (int) (p.getX() * SCALE) + OFFSET;
            int drawY = OFFSET - (int) (p.getY() * SCALE);
            g2d.fillOval(drawX - 5, drawY - 5, 10, 10);
        }

        drawDecisionBoundaries(g2d);
    }

    private void drawDecisionBoundaries(Graphics2D g2d) {
        double w1 = svm.getW1();
        double w2 = svm.getW2();
        double b = svm.getB();

        // Ana Karar Çizgisi (Optimal Sınır)
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        renderLine(g2d, w1, w2, b);

        // Güvenlik Marjinleri (Kesikli Çizgiler)
        g2d.setColor(Color.GRAY);
        float[] dash = {10.0f};
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        renderLine(g2d, w1, w2, b - 1);
        renderLine(g2d, w1, w2, b + 1);
    }

    private void renderLine(Graphics2D g2d, double w1, double w2, double b) {
        int x1 = -10, x2 = 10;
        int y1 = (int) (-(w1 * x1 + b) / w2);
        int y2 = (int) (-(w1 * x2 + b) / w2);

        g2d.drawLine(x1 * SCALE + OFFSET, OFFSET - y1 * SCALE,
                x2 * SCALE + OFFSET, OFFSET - y2 * SCALE);
    }
}