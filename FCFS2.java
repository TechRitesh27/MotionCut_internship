import java.util.*;

class FCFS2 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        // Get the number of processes
        System.out.println("Enter number of processes");
        int p = in.nextInt();
        
        // Arrays to store burst times, arrival times, waiting times, and turnaround times
        int bt[] = new int[p]; // Burst time
        int at[] = new int[p]; // Arrival time
        int wt[] = new int[p]; // Waiting time
        int tat[] = new int[p]; // Turnaround time
        
        // Input burst time and arrival time for each process
        System.out.println("Enter burst time and arrival time for each process");
        for (int i = 0; i < p; i++) {
            System.out.print("Burst time for P" + (i + 1) + ": ");
            bt[i] = in.nextInt();
            System.out.print("Arrival time for P" + (i + 1) + ": ");
            at[i] = in.nextInt();
        }
        
        // Sorting processes by arrival time (if arrival time is same, FCFS will handle by order)
        for (int i = 0; i < p; i++) {
            for (int j = i + 1; j < p; j++) {
                if (at[i] > at[j]) {
                    // Swap burst time
                    int tempBt = bt[i];
                    bt[i] = bt[j];
                    bt[j] = tempBt;
                    
                    // Swap arrival time
                    int tempAt = at[i];
                    at[i] = at[j];
                    at[j] = tempAt;
                }
            }
        }
        
        // Calculate waiting time for each process
        wt[0] = 0; // Waiting time for the first process is always 0
        for (int i = 1; i < p; i++) {
            wt[i] = bt[i - 1] + wt[i - 1] - at[i] + at[i - 1];
        }
        
        // Calculate turnaround time for each process
        for (int i = 0; i < p; i++) {
            tat[i] = bt[i] + wt[i];
        }
        
        // Calculate average waiting time and average turnaround time
        float totalWt = 0, totalTat = 0;
        for (int i = 0; i < p; i++) {
            totalWt += wt[i];
            totalTat += tat[i];
        }
        
        // Output the results
        System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < p; i++) {
            System.out.println("P" + (i + 1) + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }
        
        System.out.println("Average Waiting Time = " + (totalWt / p));
        System.out.println("Average Turnaround Time = " + (totalTat / p));
    }
}
