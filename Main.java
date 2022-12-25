import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            int choice = ((promptForChoice(scanner))-1);
            Movie movie = store.getMovie((choice));
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
            System.out.print("\nPlease choose an integer between 1 - 10: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice < 1 || choice > 10) {
                    continue;
                }
                return choice;
            } else {
                scanner.next();
                continue;
            }

        }
    }

    public static boolean incorrectChoice(int choice) {
        if (choice < 1 || choice > 10) {
            return true;
        }
        return false;
    }

    public static double promptForRating(Scanner scanner, String name) {
        while (true) {
            System.out.print("\nSet a new rating for '" + name + "': ");
            
            if (scanner.hasNextDouble()) {
                double rating = scanner.nextDouble();
                if (rating < 0 || rating > 10) {
                    continue;
                }
                return rating;
            } else {
                scanner.next();
                continue;
            }

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
        
        while (scanFile.hasNextLine()) {

            String nameInFile = scanFile.next();
            String formatInFile = scanFile.next();
            double ratingInFile = Double.parseDouble((scanFile.nextLine()).substring(2));

            store.addMovie(new Movie(nameInFile, formatInFile, ratingInFile));
        }
        scanFile.close();
    }

    public static void printStore() {
        System.out.println("\n********************************MOVIE STORE*******************************\n");
        System.out.println(store);
    }

}
