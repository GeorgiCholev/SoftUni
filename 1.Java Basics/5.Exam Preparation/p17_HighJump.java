package ExamPreparation;

import java.util.Scanner;

public class HighJump {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int desiredGoal = Integer.parseInt(scanner.nextLine());

        boolean success = true;
   int jumpTotal = 0;
        int i;

        for (i = desiredGoal - 30; i <= desiredGoal; i += 5) {
            int jumpFail = 0;
            int jumpSuccess = 0;
            while (true) {
                int jump = Integer.parseInt(scanner.nextLine());
                if (jump > i) {
                    jumpSuccess++;
                    break;
                } else {
                    jumpFail++;
                }
                if (jumpFail == 3) {
                    success = false;
                    break;
                }
            }
            jumpTotal += jumpSuccess + jumpFail;
            if (!success) {
                break;
            }
        }
        if (!success) {
            System.out.printf("Tihomir failed at %dcm after %d jumps.", i, jumpTotal);
        } else {
            System.out.printf("Tihomir succeeded, he jumped over %dcm after %d jumps.", desiredGoal, jumpTotal);
        }
    }
}
