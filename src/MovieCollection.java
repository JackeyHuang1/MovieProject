import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<String> stringData = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public MovieCollection() {
        try {
            Scanner input = new Scanner(new File("src\\movies_data.csv"));
            while (input.hasNext()) {
                String data = input.nextLine();
                stringData.add(data);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (String movie: stringData) {
            String[] split = movie.split(",");
            movies.add(new Movie(split[0], split[1], split[2], split[3], Integer.valueOf(split[4]), Double.valueOf(split[5])));
        } mainMenu();
    }

    public void mainMenu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                System.out.println("empty right now");
//                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {
        System.out.print("Enter a title search term: ");
        String search = scanner.nextLine();
        ArrayList<String> titles = new ArrayList<>();
        for (Movie movie: movies) {
            if (movie.getTitle().contains(search)) {
                titles.add(movie.getTitle());
            }
        } if (titles.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(i+1 + ". " + titles.get(i));
            }
        }

    }


}



