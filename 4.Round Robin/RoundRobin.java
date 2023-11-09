import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();

        int[] process = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];

        System.out.println("Enter time quantum:");
        int timeQuantum = sc.nextInt();

        // Input process and burst time for each process
        for (int i = 0; i < n; i++) {
            process[i] = i + 1;
            System.out.println("Enter burst time for Process " + (i + 1) + ":");
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        // Initialize variables
        int currentTime = 0;
        boolean[] completed = new boolean[n];

        System.out.println("\nGantt Chart:");
        System.out.print("0");
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;

        // Simulate Round Robin
        while (true) {
            boolean allCompleted = true;

            for (int i = 0; i < n; i++) {
                if (!completed[i]) {
                    allCompleted = false;
                    if (remainingTime[i] > 0) {
                        int executeTime = Math.min(timeQuantum, remainingTime[i]);
                        currentTime += executeTime;
                        remainingTime[i] -= executeTime;

                        System.out.print(" -> P" + process[i]);

                        if (remainingTime[i] == 0) {
                            completed[i] = true;
                            int turnaroundTime = currentTime;
                            totalTurnaroundTime += turnaroundTime;
                            int waitingTime = turnaroundTime - burstTime[i];
                            totalWaitingTime += waitingTime;
                        }
                    }
                }
            }

            if (allCompleted) {
                break;
            }
        }

        System.out.println("\n\nProcess\tBurst Time\tTurnaround Time\tWaiting Time");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" + burstTime[i] + "\t\t" + (currentTime - (burstTime[i] - remainingTime[i])) + "\t\t" + (currentTime - (burstTime[i] - remainingTime[i]) - burstTime[i]));
        }

        System.out.println("\nAverage Turnaround Time: " + (float) totalTurnaroundTime / n);
        System.out.println("Average Waiting Time: " + (float) totalWaitingTime / n);

        sc.close();
    }
}
