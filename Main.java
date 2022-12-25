import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Store store = new Store();

    public static void main(String[] args) {

        try {
            loadMovies("movies.txt");
            printStore();
            userInput();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void userInput() {
        Scanner scanner = new Scanner(System.in);
        String status = "continue";
    
        while (status.equals("continue")) {
            int choice = (promptForChoice(scanner));
            Movie movie = store.getMovie(choice);
            double rating = promptForRating(scanner, movie.getName());
    
            movie.setRating(rating);
            store.setMovie(choice, movie);
            printStore();
            System.out.print("To edit another rating, type: 'continue': ");
            status = scanner.next();
        }
        scanner.close();
    }

    public static int promptForChoice(Scanner scanner) {
        while (true) {
            System.out.print("\nPlease choose an integer between 0 - 9: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice < 0 || choice > 9) {
                    continue;
                }
                return choice;
            } else {
                continue;
            }

        }
    }

    public static boolean incorrectChoice(int choice) {
        if (choice < 0 || choice > 9) {
            return true;
        }
        return false;
    }

    public static double promptForRating(Scanner scanner, String name) {
        while (true) {
            System.out.print("\nSet a new rating for " + name + ": ");
            
            // 1. Anticipate the user not entering a double.

            double rating = scanner.nextDouble();
            
            // 2. Anticipate the rating being incorrect.

            return rating;
         }
    }

    public static boolean incorrectRating(double rating) {
        if (rating < 0 || rating > 10) {
            return true;
        }
        return false;
    }

    public static void loadMovies(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);
        scanFile.useDelimiter("[-]{2}");
        int index = 0;
        
        while (scanFile.hasNextLine()) {

            String nameInFile = scanFile.next();
            String formatInFile = scanFile.next();
            Double ratingInFile = Double.parseDouble((scanFile.nextLine()).substring(2));

            System.out.println(index + ".\t" + ratingInFile + "\t" + formatInFile + "\t" + nameInFile);
            index++;
        }
        scanFile.close();
    }

    public static void printStore() {
        System.out.println("\n********************************MOVIE STORE*******************************\n");
        System.out.println(store);
    }

}
