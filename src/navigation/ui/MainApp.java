package navigation.ui;

import navigation.model.Point;
import navigation.logic.SVMClassifier;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        List<Point> trainingData = generateData();
        SVMClassifier classifier = new SVMClassifier();

        System.out.println("Eğitim başlatılıyor... Lütfen bekleyin.");
        classifier.train(trainingData);
        System.out.println("Eğitim tamamlandı. Görselleştirme açılıyor.");

        JFrame frame = new JFrame("Otonom Araç Güvenlik Modülü");
        frame.add(new SafetyVisualizer(trainingData, classifier));
        frame.setSize(800, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static List<Point> generateData() {
        List<Point> points = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 30; i++) {
            // Engel Grubu A (Üst Sağ)
            points.add(new Point(r.nextDouble() * 4 + 1, r.nextDouble() * 4 + 1, 1));
            // Engel Grubu B (Alt Sol)
            points.add(new Point(r.nextDouble() * 4 - 5, r.nextDouble() * 4 - 5, -1));
        }
        return points;
    }
}