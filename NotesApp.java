import java.io.*;
import java.util.Scanner;

public class NotesApp {
    static final String FILE_NAME = "notes.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Write a Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Clear All Notes");
            System.out.println("4. Search Notes");
            System.out.println("5. Delete Notes File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1: writeNote(); break;
                case 2: viewNotes(); break;
                case 3: clearNotes(); break;
                case 4: searchNotes(); break;
                case 5: deleteNotesFile(); break;
                case 6: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    static void writeNote() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME, true); 
            System.out.print("Enter your note: ");
            String note = sc.nextLine();
            writer.write(note + "\n");
            writer.close();
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    static void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) System.out.println("No notes available.");
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found.");
        } catch (IOException e) {
            System.out.println("Error reading notes.");
        }
    }

    static void clearNotes() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(""); // overwrite with empty content
            writer.close();
            System.out.println("All notes cleared.");
        } catch (IOException e) {
            System.out.println("Error clearing notes.");
        }
    }

    static void searchNotes() {
        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Search Results ---");
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println("- " + line);
                    found = true;
                }
            }
            if (!found) System.out.println("No matching notes found.");
        } catch (IOException e) {
            System.out.println("Error searching notes.");
        }
    }

    static void deleteNotesFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Notes file deleted successfully.");
            } else {
                System.out.println("Could not delete the file.");
            }
        } else {
            System.out.println("No notes file to delete.");
        }
    }
}

