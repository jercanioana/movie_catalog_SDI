//package ro.ubb.movie_catalog.core.repository.file;
//
//import ro.ubb.movie_catalog.core.domain.entities.Client;
//import ro.ubb.movie_catalog.core.domain.entities.Movie;
//import ro.ubb.movie_catalog.core.domain.entities.Rentals;
//import ro.ubb.movie_catalog.core.domain.validators.Validator;
//import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
//import ro.ubb.movie_catalog.core.repository.inmemory.InMemoryRepository;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class FileRepositoryRentals extends InMemoryRepository<Long, Rentals> {
//
//    private String fileName;
//    public FileRepositoryRentals(Validator<Rentals> validator, String fileName) {
//        super(validator);
//        this.fileName = fileName;
//        loadData();
//    }
//
//    private void loadData(){
//        Path path = Paths.get(fileName);
//        try{
//            Files.lines(path).forEach(line -> {
//                List<String> items = Arrays.asList(line.split(","));
//                Long id = Long.valueOf(items.get(0));
//                Long movieID = Long.valueOf(items.get(1));
//                Long clientID = Long.valueOf(items.get(2));
//                int numberOfDays = Integer.parseInt(items.get(3));
//
//                Rentals rental = new Rentals(clientID, movieID, numberOfDays);
//                rental.setId(id);
//                try{
//                    super.save(rental);
//                }catch(ValidatorException exception){
//                    exception.printStackTrace();
//                }
//            });
//
//        }catch(IOException ex){
//            ex.printStackTrace();
//        }
//
//    }
//
//
//    @Override
//    public Optional<Rentals> save(Rentals entity) throws ValidatorException {
//        Optional<Rentals> optional = super.save(entity);
//        if(optional.isPresent()){
//            return optional;
//        }
//        saveToFile(entity);
//        return Optional.empty();
//
//    }
//
//    private void saveToFile(Rentals rentals) {
//        Path path = Paths.get(fileName);
//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
//                StandardOpenOption.APPEND)){
//            int id = Math.toIntExact(rentals.getId());
//            int idc = Math.toIntExact(rentals.getClientID());
//            int idm = Math.toIntExact(rentals.getMovieID());
//            bufferedWriter.write(Integer.toString(id) + ',' + idc + ',' + idm + '\n');
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateFile(){
//
//        Iterable<Rentals> rentals = super.findAll();
//        Path path = Paths.get(fileName);
//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
//                StandardOpenOption.WRITE)){
//            for (Rentals r: rentals) {
//                int id = Math.toIntExact(r.getId());
//                int idc = Math.toIntExact(r.getClientID());
//                int idm = Math.toIntExact(r.getMovieID());
//                bufferedWriter.write(Integer.toString(id) + ',' + idc + ',' + idm + '\n');
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public Optional<Rentals> delete(Long id) {
//        Optional<Rentals> optional = super.delete(id);
//        if(optional.isPresent()){
//            updateFile();
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Rentals> update(Rentals entity) throws ValidatorException {
//        Optional<Rentals> optional = super.update(entity);
//        if(optional.isPresent()){
//            updateFile();
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//}
