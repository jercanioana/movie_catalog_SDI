//package ro.ubb.movie_catalog.core.repository.file;
//
//import ro.ubb.movie_catalog.core.domain.entities.BaseEntity;
//import ro.ubb.movie_catalog.core.domain.entities.Movie;
//import ro.ubb.movie_catalog.core.domain.validators.Validator;
//import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
//import ro.ubb.movie_catalog.core.repository.inmemory.InMemoryRepository;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class FileRepositoryMovie<ID, T extends BaseEntity<ID>> extends InMemoryRepository<Long, Movie> {
//
//    private String fileName;
//    public FileRepositoryMovie(Validator<Movie> validator, String fileName) {
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
//                String name = items.get(1);
//                String director = items.get(2);
//
//                Movie movie = new Movie(name, director);
//                movie.setId(id);
//                try{
//                    super.save(movie);
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
//    public Optional<Movie> save(Movie entity) throws ValidatorException {
//        Optional<Movie> optional = super.save(entity);
//        if(optional.isPresent()){
//            return optional;
//        }
//        saveToFile(entity);
//        return Optional.empty();
//
//    }
//
//    @Override
//    public Optional<Movie> delete(Long id) {
//        Optional<Movie> optional = super.delete(id);
//        if(optional.isPresent()){
//            updateFile();
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Movie> update(Movie entity) throws ValidatorException {
//        Optional<Movie> optional = super.update(entity);
//        if(optional.isPresent()){
//            updateFile();
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    private void updateFile(){
//
//        Iterable<Movie> movies = super.findAll();
//        Path path = Paths.get(fileName);
//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
//               StandardOpenOption.WRITE)){
//            for (Movie movie: movies) {
//                int id = Math.toIntExact(movie.getId());
//                bufferedWriter.write(Integer.toString(id) + ',' + movie.getName() + ',' + movie.getDirector() + '\n');
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void saveToFile(Movie movie) {
//        Path path = Paths.get(fileName);
//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
//                StandardOpenOption.APPEND)){
//            int id = Math.toIntExact(movie.getId());
//            bufferedWriter.write(Integer.toString(id) + ',' + movie.getName() + ',' + movie.getDirector() +'\n');
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
