//package ro.ubb.movie_catalog.core.repository.xml;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//import ro.ubb.movie_catalog.core.domain.entities.Client;
//import ro.ubb.movie_catalog.core.domain.entities.Movie;
//import ro.ubb.movie_catalog.core.domain.entities.Rentals;
//import ro.ubb.movie_catalog.core.domain.validators.Validator;
//import ro.ubb.movie_catalog.core.domain.validators.ValidatorException;
//import ro.ubb.movie_catalog.core.repository.inmemory.InMemoryRepository;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class XMLRepositoryMovie extends InMemoryRepository<Long, Movie> {
//
//    public XMLRepositoryMovie(Validator validator) throws IOException, SAXException, ParserConfigurationException, ValidatorException {
//        super(validator);
//
//        loadData();
//    }
//
//
//    private List<Movie> loadData() throws ParserConfigurationException, IOException, SAXException, ValidatorException {
//        List<Movie> result = new ArrayList<>();
//        Document document = DocumentBuilderFactory.newInstance()
//                .newDocumentBuilder().parse("./data/Movies.xml");
//        Element root = document.getDocumentElement();
//        NodeList children = root.getChildNodes();
//        result =  IntStream
//                .range(0, children.getLength())
//                .mapToObj(index -> children.item(index))
//                .filter(node -> node instanceof Element)
//                .map(node -> createMovieFromElement((Element) node))
//                .collect(Collectors.toList());
//        for(Movie m: result)
//            super.save(m);
//        return result;
//
//    }
//
//    public static Movie createMovieFromElement(Element movieElement) {
//        Movie movie = new Movie();
//        String id = movieElement.getAttribute("id");
//        movie.setId(Long.valueOf(id));
//        movie.setName(getTextFromTagName(movieElement, "name"));
//        movie.setDirector(getTextFromTagName(movieElement, "director"));
//        return movie;
//    }
//    private static String getTextFromTagName(Element parentElement, String tagName){
//        Node node = parentElement.getElementsByTagName(tagName).item(0);
//        return node.getTextContent();
//
//    }
//
//    @Override
//    public Optional<Movie> save(Movie movie) throws ValidatorException {
//        Optional<Movie> optional = super.save(movie);
//        if(optional.isPresent()){
//            return optional;
//        }
//        try {
//            saveToFile(movie);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return Optional.empty();
//    }
//    public static void deleteMovie(Long id) throws ParserConfigurationException, IOException, SAXException {
//        Document document = DocumentBuilderFactory
//                .newInstance().newDocumentBuilder()
//                .parse("./data/Movies.xml");
//        Element root = document.getDocumentElement();
//        NodeList nodes = root.getChildNodes();
//        for (int i = 0; i < nodes.getLength(); i++) {
//            Element el = (Element) nodes.item(i);
//            if (el.getAttribute("id").equals(id.toString()))
//                el.getParentNode().removeChild(el);
//        }
//    }
//    @Override
//    public Iterable<Movie> findAll() {
//        return super.findAll();
//    }
//
//    @Override
//    public Optional<Movie> delete(Long id) {
//        Optional<Movie> optional = super.delete(id);
//        if (optional.isPresent())
//        {
//            try {
//                deleteMovie(id);
//            } catch (ParserConfigurationException | SAXException | IOException e) {
//                e.printStackTrace();
//            }
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    public static void saveToFile(Movie movie) throws ParserConfigurationException, TransformerException, IOException, SAXException {
//
//        Document document = DocumentBuilderFactory
//                .newInstance().newDocumentBuilder()
//                .parse("./data/Movies.xml");
//
//        Element root = document.getDocumentElement();
//        Node movieNode = movieToNode(movie, document);
//        root.appendChild(movieNode);
//
//        Transformer transformer = TransformerFactory
//                .newInstance()
//                .newTransformer();
//        transformer.transform(new DOMSource(document),
//                new StreamResult(new File("./data/Movies.xml")));
//
//    }
//    public static Node movieToNode(Movie movie, Document document){
//        Element movieElement = document.createElement("movie");
//        movieElement.setAttribute("id", movie.getId().toString());
//        appendChildWithTextNode(document, movieElement, "name", movie.getName());
//        appendChildWithTextNode(document, movieElement, "director", movie.getDirector());
//        return movieElement;
//
//    }
//
//    private static void appendChildWithTextNode(Document document, Node parentNode, String tagname, String textContent) {
//        Element element = document.createElement(tagname);
//        element.setTextContent(textContent);
//        parentNode.appendChild(element);
//    }
//
//}
