import java.io.*;
import java.util.*;

public class ArchitectureDetector {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the folder path");
        String folderPath = sc.nextLine();

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path!");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("No files found.");
            return;
        }

        List<String[]> results = new ArrayList<>();
        int totalFilesFound = 0;

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".so")) {
                try {
                    String architecture = detectArchitecture(file);
                    if (!architecture.equals("Invalid")) {
                        results.add(new String[]{file.getName(), architecture});
                        totalFilesFound++;
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + file.getName());
                }
            }
        }

        System.out.println("\nTotal number of libraries: " + totalFilesFound);
        System.out.println("\nFile                     ArchitectureType");
        System.out.println("=====================    ===========");
        for (String[] result : results) {
            System.out.printf("%-24s %s\n", result[0], result[1]);
        }
    }

    private static String detectArchitecture(File file) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            byte[] header = new byte[20];
            dis.readFully(header);

        
            if (header[0] != 0x7F || header[1] != 'E' || header[2] != 'L' || header[3] != 'F') {
                return "Invalid";
            }

          
            int e_machine = ((header[19] & 0xFF) << 8) | (header[18] & 0xFF);

            switch (e_machine) {
                case 3: return "x86";
                case 8: return "mips";
                case 40: return "armeabi-v7a";
                case 62: return "x86-64";
                case 183: return "arm64-v8a";
                default: return "Unknown";
            }
        }
    }
}
