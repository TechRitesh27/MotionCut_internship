import java.util.*;

class PriorityScheduling {
    static class Process {
        int id;       // Process ID
        int bt;       // Burst Time
        int at;       // Arrival Time
        int priority; // Priority (smaller number means higher priority)
        int ct;       // Completion Time
        int wt;       // Waiting Time
        int tat;      // Turnaround Time
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Number of processes
        System.out.print("Enter the number of processes: ");
        int n = in.nextInt();

        Process[] processes = new Process[n];

        // Input burst time, arrival time, and priority for each process
        System.out.println("Enter burst time, arrival time, and priority for each process:");
        for (int i = 0; i < n; i++) {
            processes[i] = new Process();
            processes[i].id = i + 1;
            System.out.print("Burst time for P" + (i + 1) + ": ");
            processes[i].bt = in.nextInt();
            System.out.print("Arrival time for P" + (i + 1) + ": ");
            processes[i].at = in.nextInt();
            System.out.print("Priority for P" + (i + 1) + ": ");
            processes[i].priority = in.nextInt();
        }

        // Sort processes based on arrival time
        Arrays.sort(processes, Comparator.comparingInt(p -> p.at));

        int time = 0; // Current time
        int completed = 0; // Number of completed processes
        ArrayList<Process> availableProcesses = new ArrayList<>();

        // While there are incomplete processes
        while (completed < n) {
            // Add all arrived processes to available processes list
            for (Process p : processes) {
                if (p.at <= time && p.ct == 0) {
                    availableProcesses.add(p);
                }
            }

            if (!availableProcesses.isEmpty()) {
                // Find the process with the highest priority (smallest priority number)
                Process current = availableProcesses.stream()
                        .min(Comparator.comparingInt(p -> p.priority))
                        .orElse(null);

                // Update the completion time, turnaround time, and waiting time
                current.ct = time + current.bt;
                current.tat = current.ct - current.at;
                current.wt = current.tat - current.bt;

                time = current.ct; // Update current time to completion time of the current process
                completed++; // One process is completed
                availableProcesses.remove(current); // Remove the process from available processes
            } else {
                time++; // Increment time if no process is ready
            }
        }

        // Calculate average waiting time and turnaround time
        float totalWt = 0, totalTat = 0;
        System.out.println("Process\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            totalWt += p.wt;
            totalTat += p.tat;
            System.out.println("P" + p.id + "\t\t" + p.at + "\t\t" + p.bt + "\t\t" + p.priority + "\t\t" + p.wt + "\t\t" + p.tat);
        }

        System.out.println("Average Waiting Time = " + (totalWt / n));
        System.out.println("Average Turnaround Time = " + (totalTat / n));
    }
}
