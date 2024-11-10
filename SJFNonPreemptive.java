import java.util.*;

class SJFNonPreemptive {

    // Process class to store process details
    static class Process {
        int id;      // Process ID
        int bt;      // Burst Time
        int at;      // Arrival Time
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
            System.out.print("Arrival time for P" + (i + 1) + ": ");
            processes[i].at = in.nextInt();
        }

        // Sorting processes based on arrival time
        Arrays.sort(processes, Comparator.comparingInt(p1 -> p1.at));

        int time = 0; // Current time
        int completed = 0; // Number of completed processes

        // While there are incomplete processes
        while (completed < p) {
            // Find all processes that have arrived by the current time
            ArrayList<Process> availableProcesses = new ArrayList<>();
            for (int i = 0; i < p; i++) {
                if (processes[i].at <= time && processes[i].ct == 0) {
                    availableProcesses.add(processes[i]);
                }
            }

            if (!availableProcesses.isEmpty()) {
                // Select the process with the shortest burst time
                Process current = availableProcesses.stream()
                        .min(Comparator.comparingInt(p1 -> p1.bt))
                        .orElse(null);

                // Update the completion time, waiting time, and turnaround time
                current.ct = time + current.bt;
                current.tat = current.ct - current.at;
                current.wt = current.tat - current.bt;

                time = current.ct; // Update time to the completion time of the current process
                completed++; // One process completed
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
