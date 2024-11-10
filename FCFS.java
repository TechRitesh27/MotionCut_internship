import java.util.*;

class FCFS {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        // Input: Number of processes
        int p;
        float t1 = 0, t2 = 0;
        System.out.println("Enter number of processes:");
        p = in.nextInt();
        
        // If the number of processes is non-positive, exit the program
        if (p <= 0) {
            System.out.println("Invalid number of processes.");
            return;
        }

        int bt[] = new int[p];  // Burst time array

        // Input: Burst times
        System.out.println("Enter burst time for each process:");
        for (int i = 0; i < p; i++) {
            System.out.println("Burst time for P" + (i + 1) + " =");
            bt[i] = in.nextInt();
        }

        int wt[] = new int[p];  // Waiting time array
        wt[0] = 0;  // Waiting time for the first process is always 0

        // Calculate waiting times
        for (int i = 1; i < p; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
            t1 += wt[i];
        }

        int tat[] = new int[p];  // Turnaround time array
        // Calculate turnaround times
        for (int i = 0; i < p; i++) {
            tat[i] = bt[i] + wt[i];
            t2 += tat[i];
        }

        // Display process details
        System.out.println("Process\tBurst time\tWaiting time\tTurnaround time");
        for (int i = 0; i < p; i++) {
            System.out.println("P" + (i + 1) + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }

        // Calculate and display average waiting time and turnaround time
        float awt = t1 / p;
        float atat = t2 / p;
        System.out.printf("Average Waiting time = %.2f\n", awt);
        System.out.printf("Average Turnaround time = %.2f\n", atat);

        in.close();
    }
}
