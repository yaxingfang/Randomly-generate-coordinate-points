import java.util.Random;
import java.util.Scanner;

public class GenerateRandomPoints {
    public static void main(String[] args) {
        RandomPoint randomPoint = new RandomPoint();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入模拟坐标数量: ");
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            double rand1 = random.nextDouble(), rand2 = random.nextDouble();
            double[] point = randomPoint.generatePoint(rand1, rand2);
            System.out.printf("Latitude: %.5f, Longitude: %.5f, Distance: %.5f, Bearing: %.5f\n",
                    point[2], point[3], point[4], point[5]);
        }
    }
}
