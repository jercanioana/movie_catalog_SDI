package ro.ubb.movie_catalog.client.ui;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.movie_catalog.core.domain.entities.Client;
import ro.ubb.movie_catalog.core.domain.entities.Movie;
import ro.ubb.movie_catalog.core.domain.entities.Rentals;
import ro.ubb.movie_catalog.web.dto.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@Component
public class Console {

    private static final String URLClient = "http://localhost:8080/api/clients";
    private static final String URLMovie = "http://localhost:8080/api/movies";
    private static final String URLRental = "http://localhost:8080/api/rentals";
    private RestTemplate restTemplate;
    public Console(RestTemplate rt) {
        restTemplate = rt;
    }


    public void runConsole() {

        String menu = "Choose an option: \n" +
                "1.Add client\n" +
                "2.Print clients\n" +
                "3.Add movie\n" +
                "4.Print movies\n" +
                "5.Filter clients by name\n" +
                "6.Filter movies by director\n" +
                "7.Remove movie\n" +
                "8.Remove client\n" +
                "9.Update movie\n"+
                "10.Add rental\n" +
                "11.Delete rental\n" +
                "12.Update rental\n" +
                "13.Print rentals\n" +
                "14.Print Most Rented Movie\n"+
                "15.Update Client\n" +
                "16.Get the client how rented the most movies\n" +
                "17.Exit";
        try(Scanner console = new Scanner(System.in)) {
            while (true) {
                System.out.println(menu);
                int option = console.nextInt();
                if (option == 1)
                    addClient();
                else if (option == 2)
                    printAllClients();
                else if (option == 3)
                    addMovie();
                else if (option == 4)
                    printAllMovies();
                else if (option == 5)
                    filterClients();
                else if (option == 6)
                    filterMoviesByDirector();
                else if (option == 7)
                    deleteMovie();
                else if (option == 8)
                    deleteClient();
                else if (option == 9)
                    updateMovie();
                else if (option == 10)
                    addRental();
                else if (option == 11)
                    deleteRental();
                else if (option == 12)
                    updateRental();
                else if (option == 13)
                    printAllRentals();
                else if (option == 14)
                    mostRentedMovie();
                else if (option == 15)
                    updateClient();
                else if (option == 16)
                    mostFrequentClient();
                else if (option == 17)
                    break;
            }
        }

    }
    private void addRental() {
       Rentals rental = readRental();
       RentalDTO savedrental = restTemplate.postForObject(URLRental,
                new RentalDTO(rental.getMovieID(), rental.getClientID(), rental.getNumberOfDays()), RentalDTO.class);

    }

    private void deleteRental() {
        System.out.println("Insert the id of the rental: ");
        Scanner console = new Scanner(System.in);
        long id = console.nextLong();
        restTemplate.delete(URLRental + "/{id}", id);

    }

    private void updateRental() {
        Rentals updatedRental = readRental();
        RentalDTO rentalDTO = new RentalDTO(updatedRental.getClientID(), updatedRental.getMovieID(), updatedRental.getNumberOfDays());
        rentalDTO.setId(updatedRental.getId());
        restTemplate.put(URLRental + "/{id}", rentalDTO, updatedRental.getId());


    }



    private void printAllRentals() {
        RentalsDTO rentalsDTO = restTemplate.getForObject(URLRental, RentalsDTO.class);
        System.out.println(rentalsDTO);

    }

    private void deleteClient() {
        System.out.println("Insert the id of the client: ");
        Scanner console = new Scanner(System.in);
        long id = console.nextLong();
        restTemplate.delete(URLClient + "/{id}", id);

    }

    private void updateClient() {
        Client client = readClient();
        ClientDTO clientDTO = new ClientDTO(client.getName(), client.getAge());
        clientDTO.setId(client.getId());
        restTemplate.put(URLClient + "/{id}", clientDTO, client.getId());

    }

    private void updateMovie(){
        Movie movie = readMovie();
        MovieDTO movieDTO = new MovieDTO(movie.getName(), movie.getDirector());
       movieDTO.setId(movie.getId());
        restTemplate.put(URLMovie + "/{id}", movieDTO, movie.getId());

    }

    private void deleteMovie(){
        System.out.println("Insert the id of the movie: ");
        Scanner console = new Scanner(System.in);
        long id = console.nextLong();
        restTemplate.delete(URLMovie + "/{id}", id);

    }


    private void printAllClients() {
        ClientsDTO clientsDTO = restTemplate.getForObject(URLClient, ClientsDTO.class);
        System.out.println(clientsDTO);

    }

    private void printAllMovies() {
        MoviesDTO moviesDTO = restTemplate.getForObject(URLMovie, MoviesDTO.class);
        System.out.println(moviesDTO);


    }

    private void addClient() {
        Client client = readClient();
        ClientDTO savedClient = restTemplate.postForObject(URLClient,
                new ClientDTO(client.getName(), client.getAge()), ClientDTO.class);

    }

    private void addMovie() {
       Movie movie = readMovie();
       MovieDTO savedMovie = restTemplate.postForObject(URLMovie,
                new MovieDTO(movie.getName(), movie.getDirector()), MovieDTO.class);

    }

    private Client readClient() {
        System.out.println("Read client {id, name, age}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long ID = Long.valueOf(bufferedReader.readLine());
            String name = bufferedReader.readLine();
            int age = Integer.parseInt(bufferedReader.readLine());
            Client client = new Client(name, age);
            client.setId(ID);
            return client;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Movie readMovie() {
        System.out.println("Read movie {id, name, director}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long ID = Long.valueOf(bufferedReader.readLine());
            String name = bufferedReader.readLine();
            String director = bufferedReader.readLine();
            Movie movie = new Movie(name, director);
            movie.setId(ID);
            return movie;
        }catch(IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    private Rentals readRental() {
        System.out.println("Read rental {id, clientID, movieID, Number of days}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long ID = Long.valueOf(bufferedReader.readLine());
            Long clientID = Long.valueOf(bufferedReader.readLine());
            Long MovieID = Long.valueOf(bufferedReader.readLine());
            int days = Integer.parseInt(bufferedReader.readLine());
            Rentals rental = new Rentals(clientID, MovieID, days);
            rental.setId(ID);
            return rental;

        }catch(IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    private void filterMoviesByDirector(){


    }

    private void filterClients() {
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        ClientsDTO clientsDTO = restTemplate.getForObject(URLClient + "/{name}", ClientsDTO.class, name);
        System.out.println(clientsDTO);


    }

    private void mostFrequentClient() {

    }

    private void mostRentedMovie() {

    }

}
