package edu.miu;

import edu.miu.utils.FileContentReader;
import edu.miu.utils.PseudoDatabase;

import java.util.Optional;
import java.util.Scanner;

import static edu.miu.movielens.functions.FunctionUtils.*;


public class Application {
    private static final String OPTION_FILE = "options";
    private static final PseudoDatabase database = PseudoDatabase.getInstance();

    public static void main(String[] args) {
        Optional<String> optional = FileContentReader.readFileContent(OPTION_FILE);
        String options = optional.orElseThrow(RuntimeException::new);
        String userSelection = "go";
        Scanner scanner = new Scanner(System.in);
        while(!userSelection.equals("q")) {
            System.out.println("=============");
            System.out.println(options);
            System.out.println("=============");
            System.out.print("Enter Query Number: ");
            userSelection = scanner.nextLine();
            switch (userSelection) {
                case "1":
                    System.out.print("Enter K: ");
                    int k = scanner.nextInt();
                    System.out.print("Enter Year: ");
                    int year = scanner.nextInt();
                    System.out.println("------------------");
                    topKMoviesInGivenYear
                            .apply(database.getMovies(), k, year)
                            .forEach(System.out::println);
                    System.out.println("------------------");
                    System.out.print("Do you want to test more?: ");
                    int cont = scanner.nextInt();
                    if(cont == 0) {
                        System.exit(0);
                    }
                    break;
                case "2":
                    System.out.print("Enter Year: ");
                    year = scanner.nextInt();
                    System.out.println("------------------");
                    mostSuccessfulDirectorInGivenYear
                            .apply(database.getMovies(), year)
                            .ifPresentOrElse(System.out::println, () -> System.out.println("not found"));
                    System.out.println("------------------");
                    System.out.print("Do you want to test more?: ");
                    cont = scanner.nextInt();
                    if(cont == 0) {
                        System.exit(0);
                    }
                    break;
                case "15":
                    System.out.print("Enter K: ");
                    k = scanner.nextInt();
                    System.out.print("Enter Year: ");
                    year = scanner.nextInt();
                    System.out.println("------------------");
                    topKMovieCountByDirectorInAGivenYear
                            .apply(database.getMovies(), k, year)
                            .forEach(System.out::println);
                    System.out.println("------------------");
                    System.out.print("Do you want to test more?: ");
                    cont = scanner.nextInt();
                    if(cont == 0) {
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("not implemented");
            }
        }
    }
}
