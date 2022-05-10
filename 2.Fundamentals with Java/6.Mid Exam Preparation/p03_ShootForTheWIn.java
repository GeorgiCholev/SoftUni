package MidExam_6.Pre;

import java.util.Scanner;

public class p02_ShootForTheWIn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputInStringArray = input.split(" ");
        int[] targets = new int[inputInStringArray.length];
        for (int i = 0; i < targets.length; i++) {
            targets[i] = Integer.parseInt(inputInStringArray[i]);
        }
        int shotTargets = 0;
        String shoot = scanner.nextLine();
        while (!shoot.equals("End")) {
        int indexShot = Integer.parseInt(shoot);
        if (indexShot >= 0 && indexShot < targets.length && targets[indexShot] != -1) {
        int valueOfShotTarget = targets[indexShot];
        targets[indexShot] = -1;
        shotTargets++;
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] <= valueOfShotTarget && targets[i] != -1) {
                targets[i] += valueOfShotTarget;
            } else if (targets[i] > valueOfShotTarget && targets[i] != -1) {
                targets[i] -= valueOfShotTarget;
            }
        }
        } else {
            shoot = scanner.nextLine();
            continue;
        }
            shoot = scanner.nextLine();
        }
        System.out.printf("Shot targets: %d -> ", shotTargets);
        for (int target : targets) {
            System.out.print(target + " ");
        }
    }
}
