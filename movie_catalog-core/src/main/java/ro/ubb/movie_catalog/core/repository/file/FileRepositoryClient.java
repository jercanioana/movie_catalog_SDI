//package ro.ubb.movie_catalog.core.repository.file;
//
//import ro.ubb.movie_catalog.core.domain.entities.Client;
//import ro.ubb.movie_catalog.core.domain.entities.Movie;
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
//public class FileRepositoryClient extends InMemoryRepository<Long, Client> {
//
//    private String fileName;
//    public FileRepositoryClient(Validator<Client> validator, String fileName) {
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
//                int age = Integer.parseInt(items.get(2));
//
//                Client client = new Client(name, age);
//                client.setId(id);
//                try{
//                    super.save(client);
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
//    public Optional<Client> save(Client entity) throws ValidatorException {
//        Optional<Client> optional = super.save(entity);
//        if(optional.isPresent()){
//            return optional;
//        }
//        saveToFile(entity);
//        return Optional.empty();
//
//    }
//    @Override
//    public Optional<Client> delete(Long id) {
//        Optional<Client> optional = super.delete(id);
//        if(optional.isPresent()){
//            updateFile();
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Client> update(Client entity) throws ValidatorException {
//        Optional<Client> optional = super.update(entity);
//        if(optional.isPresent()){
//            updateFile();
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    private void saveToFile(Client client) {
//        Path path = Paths.get(fileName);
//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
//                StandardOpenOption.APPEND)){
//            int id = Math.toIntExact(client.getId());
//            bufferedWriter.write(Integer.toString(id) + ',' + client.getName() + ',' + client.getAge() + '\n');
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateFile(){
//
//        Iterable<Client> clients = super.findAll();
//        Path path = Paths.get(fileName);
//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
//                StandardOpenOption.WRITE)){
//            for (Client c: clients) {
//                int id = Math.toIntExact(c.getId());
//                bufferedWriter.write(Integer.toString(id) + ',' + c.getName() + ',' + c.getAge() + '\n');
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
