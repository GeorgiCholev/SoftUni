package _6_Exercise_Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class p04_Salaries {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfEmployees = Integer.parseInt(scanner.nextLine());
        int[][] graph = new int[numberOfEmployees][numberOfEmployees];

        fillGraphRelationsBetweenEmployees(scanner, numberOfEmployees, graph);

        Set<Integer> unmanaged = findUnmanagedEmployees(graph);

        long[] employeeSalary = new long[graph.length];
        for (Integer unmanEmployee : unmanaged) {
            employeeSalary[unmanEmployee] = depthFirstSearch(unmanEmployee, graph, employeeSalary);
        }

        System.out.println(Arrays.stream(employeeSalary).sum());
    }

    private static long depthFirstSearch(int employee, int[][] graph, long[] employeeSalary) {
        if (employeeSalary[employee] != 0) {
            return employeeSalary[employee];
        }

        List<Integer> managed = new ArrayList<>();
        findManagedEmployeesFor(employee, graph, managed);

        if (managed.size() == 0) {
            employeeSalary[employee] = 1L;
            return employeeSalary[employee];
        }

        long salary = 0;
        for (int manEmployee : managed) {
            salary += depthFirstSearch(manEmployee, graph, employeeSalary);
        }
        employeeSalary[employee] = salary;
        return salary;
    }

    private static void findManagedEmployeesFor(int employee, int[][] graph, List<Integer> managed) {
        for (int i = 0; i < graph[employee].length; i++) {
            if (graph[employee][i] == 1) {
                managed.add(i);
            }
        }
    }

    private static Set<Integer> findUnmanagedEmployees(int[][] graph) {
        HashSet<Integer> unmanaged = IntStream.rangeClosed(0, graph.length - 1).boxed()
                .collect(Collectors.toCollection(HashSet::new));
        for (int[] employeeRelations : graph) {
            for (int i = 0; i < employeeRelations.length; i++) {
                if (employeeRelations[i] == 1) {
                    unmanaged.remove(i);
                }
            }
        }
        return unmanaged;
    }

    private static void fillGraphRelationsBetweenEmployees(Scanner scanner, int numberOfEmployees, int[][] graph) {
        for (int i = 0; i < numberOfEmployees; i++) {
            String relationForEmployee = scanner.nextLine();
            for (int j = 0; j < relationForEmployee.length(); j++) {
                graph[i][j] = relationForEmployee.charAt(j) == 'Y' ? 1 : 0;
            }
        }
    }
}
