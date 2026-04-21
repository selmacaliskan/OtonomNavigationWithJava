package navigation.logic;

import navigation.model.Point;
import java.util.List;

public class SVMClassifier {
    private double w1 = 0, w2 = 0; // Ağırlıklar (Normal vektör)
    private double b = 0;          // Bias (Kayma)

    private final double learningRate = 0.0005;
    private final double lambda = 0.01; // Regularization (Marjin genişliğini kontrol eder)
    private final int epochs = 200000;

    public void train(List<Point> points) {
        for (int i = 0; i < epochs; i++) {
            for (Point p : points) {
                // Karar fonksiyonu: y * (w·x + b)
                double condition = p.getLabel() * (w1 * p.getX() + w2 * p.getY() + b);

                if (condition >= 1) {
                    // Doğru sınıflandırma ve marjin dışında: Sadece ağırlıkları küçült (Marjin maksimizasyonu)
                    w1 -= learningRate * (2 * lambda * w1);
                    w2 -= learningRate * (2 * lambda * w2);
                } else {
                    // Yanlış sınıflandırma veya marjin ihlali: Hata düzeltme
                    w1 -= learningRate * (2 * lambda * w1 - p.getLabel() * p.getX());
                    w2 -= learningRate * (2 * lambda * w2 - p.getLabel() * p.getY());
                    b += learningRate * p.getLabel();
                }
            }
        }
    }

    public double getW1() { return w1; }
    public double getW2() { return w2; }
    public double getB() { return b; }
}