import java.io.*;

class FIFO {
    public static void main(String args[]) throws IOException {
        int n;    // Number of inputs (pages)
        int f;    // Number of frames

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of FRAMES:");
        f = Integer.parseInt(br.readLine());
        int fifo[] = new int[f];
        
        System.out.println("Enter the number of INPUTS:");
        n = Integer.parseInt(br.readLine());
        int inp[] = new int[n];
        
        System.out.println("Enter INPUT:");
        for (int i = 0; i < n; i++) {
            inp[i] = Integer.parseInt(br.readLine());
        }

        System.out.println("----------------------");

        // Initialize all frames to -1 (indicating they are empty)
        for (int i = 0; i < f; i++) {
            fifo[i] = -1;
        }

        int hit = 0;       // Count of page hits
        int fault = 0;     // Count of page faults
        int j = 0;         // Pointer for FIFO replacement
        
        for (int i = 0; i < n; i++) {
            boolean isHit = false;

            // Check if the page is already in the frames
            for (int k = 0; k < f; k++) {
                if (fifo[k] == inp[i]) {
                    isHit = true;
                    hit++;
                    break;
                }
            }

            // If page not found in frames, it is a page fault
            if (!isHit) {
                fifo[j] = inp[i]; // Replace the page in FIFO manner
                j = (j + 1) % f;  // Update the pointer for FIFO
                fault++;
            }
        }

        // Calculate hit ratio
        float hitRatio = (float) hit / (float) n;

        System.out.println("HIT: " + hit + "  FAULT: " + fault + "   HIT RATIO: " + hitRatio);
    }
}
