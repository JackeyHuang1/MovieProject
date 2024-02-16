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
        }
        mainMenu();
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
                searchCast();
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
        ArrayList<Movie> titles = new ArrayList<>();
        for (Movie movie: movies) {
            if (movie.getTitle().toLowerCase().contains(search)) {
                titles.add(movie);
            }
        } titles = sortMovieTitles(titles);
        if (titles.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(i+1 + ". " + titles.get(i).getTitle());
            }
            System.out.print("Which movie would you like to learn more about? ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nTitle: " + titles.get(choice-1).getTitle() + "\nRuntime: " + titles.get(choice-1).getRuntime() + " minutes\nDirected by: " + titles.get(choice-1).getDirector() + "\nCast: " + titles.get(choice-1).getCast() + "\nOverview: " + titles.get(choice-1).getOverview() + "\nUser Rating: " + titles.get(choice-1).getUserRating());
        }
    }

    private ArrayList<Movie> sortMovieTitles(ArrayList<Movie> titles) {
        for (int i = 1; i < titles.size(); i++) {
            Movie current = titles.get(i);
            String currentTitle = titles.get(i).getTitle();
            int index = i;
            while (index > 0 && currentTitle.compareTo(titles.get(index-1).getTitle()) < 0) {
                titles.set(index, titles.get(index-1));
                index--;
            }
            titles.set(index, current);
        } return titles;
    }

    public void searchCast() {
        System.out.print("Enter a person to search for (first or last name: ");

    }


}



