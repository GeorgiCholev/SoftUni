package ExamPreparation_10.June_26th_2021;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class p01_OsPlanning {
    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> taskStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).forEach(taskStack::push);

        ArrayDeque<Integer> threadQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).forEach(threadQueue::offer);

        int taskToKill = scanner.nextInt();

        while (taskToKill > -1) {

            int task = taskStack.peek();
            int thread = threadQueue.peek();

            if (task == taskToKill) {
                taskToKill = -1;
                System.out.println("Thread with value " + thread + " killed task " + task);
                continue;
            }
            threadQueue.poll();
            if (task <= thread) {
                taskStack.pop();
            }
        }

        while (!threadQueue.isEmpty()) {
            System.out.print(threadQueue.poll() + " ");
        }
    }
}
