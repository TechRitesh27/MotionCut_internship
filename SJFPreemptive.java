import java.util.*;

class SJFPreemptive {

    // Process class to store process details
    static class Process {
        int id;      // Process ID
        int bt;      // Burst Time
        int at;      // Arrival Time
        int rt;      // Remaining Time
        int ct;      // Completion Time
        int wt;      // Waiting Time
        int tat;     // Turnaround Time
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Number of processes
        System.out.println("Enter number of processes:");
        int p = in.nextInt();

        Process[] processes = new Process[p];

        // Input burst time and arrival time for each process
        System.out.println("Enter burst time and arrival time for each process:");
        for (int i = 0; i < p; i++) {
            processes[i] = new Process();
            processes[i].id = i + 1;
            System.out.print("Burst time for P" + (i + 1) + ": ");
            processes[i].bt = in.nextInt();
            processes[i].rt = processes[i].bt;  // Remaining time is initially the same as burst time
            System.out.print("Arrival time for P" + (i + 1) + ": ");
            processes[i].at = in.nextInt();
        }

        // Sorting processes based on arrival time
        Arrays.sort(processes, Comparator.comparingInt(p1 -> p1.at));

        int time = 0; // Current time
        int completed = 0; // Number of completed processes
        int[] completeTime = new int[p]; // Completion Time

        // Keep track of remaining burst times
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(p1 -> p1.rt));

        // While there are incomplete processes
        while (completed < p) {
            // Add all processes that have arrived by the current time and haven't been completed
            for (int i = 0; i < p; i++) {
                if (processes[i].at <= time && processes[i].rt > 0 && !pq.contains(processes[i])) {
                    pq.add(processes[i]);
                }
            }

            // If there's a process to execute
            if (!pq.isEmpty()) {
                Process current = pq.poll(); // Process with the shortest remaining time

                // Execute the process for 1 unit of time
                current.rt--;
                time++;

                // If the process is completed
                if (current.rt == 0) {
                    completed++;
                    current.ct = time; // Set the completion time
                    current.tat = current.ct - current.at; // Turnaround Time = Completion Time - Arrival Time
                    current.wt = current.tat - current.bt; // Waiting Time = Turnaround Time - Burst Time
                } else {
                    pq.add(current); // Add back the process if it's not completed
                }
            } else {
                time++; // No process to execute, increment time
            }
        }

        // Calculate average waiting time and turnaround time
        float totalWt = 0, totalTat = 0;
        System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < p; i++) {
            totalWt += processes[i].wt;
            totalTat += processes[i].tat;
            System.out.println("P" + processes[i].id + "\t\t" + processes[i].at + "\t\t" + processes[i].bt + "\t\t" + processes[i].wt + "\t\t" + processes[i].tat);
        }

        System.out.println("Average Waiting Time = " + (totalWt / p));
        System.out.println("Average Turnaround Time = " + (totalTat / p));
    }
}
