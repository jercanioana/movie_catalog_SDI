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
//public class XMLRepositoryRental extends InMemoryRepository<Long, Rentals>{
//    public XMLRepositoryRental(Validator validator) throws IOException, SAXException, ParserConfigurationException {
//        super(validator);
//
//        loadData();
//    }
//
//    private static List<Rentals> loadData() throws ParserConfigurationException, IOException, SAXException {
//        List<Rentals> result = new ArrayList<>();
//        Document document = DocumentBuilderFactory.newInstance()
//                .newDocumentBuilder().parse("");
//        Element root = document.getDocumentElement();
//        NodeList children = root.getChildNodes();
//        return IntStream
//                .range(0, children.getLength())
//                .mapToObj(index -> children.item(index))
//                .filter(node -> node instanceof Element)
//                .map(node -> createRentalFromElement((Element) node))
//                .collect(Collectors.toList());
//
//    }
//    public static Rentals createRentalFromElement(Element rentalElement) {
//        Rentals rental = new Rentals();
//        String id = rentalElement.getAttribute("id");
//        rental.setId(Long.valueOf(id));
//        rental.setClientID(Long.valueOf(getTextFromTagName(rentalElement, "clientID")));
//        rental.setMovieID(Long.valueOf(getTextFromTagName(rentalElement, "movieID")));
//        rental.setNumberOfDays(Integer.parseInt(getTextFromTagName(rentalElement, "numberOfDays")));
//        return rental;
//    }
//    private static String getTextFromTagName(Element parentElement, String tagName){
//        Node node = parentElement.getElementsByTagName(tagName).item(0);
//        return node.getTextContent();
//    }
//
//    @Override
//    public Optional<Rentals> save(Rentals rentals) throws ValidatorException {
//        Optional<Rentals> optional = super.save(rentals);
//        if(optional.isPresent()){
//            return optional;
//        }
//        try {
//            saveRental(rentals);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    public static void deleteRental(Long id) throws ParserConfigurationException, IOException, SAXException {
//        Document document = DocumentBuilderFactory
//                .newInstance().newDocumentBuilder()
//                .parse("./data/Rentals.xml");
//
//        Element root = document.getDocumentElement();
//        NodeList nodes = root.getChildNodes();
//        for (int i = 0; i < nodes.getLength(); i++) {
//            Element el = (Element) nodes.item(i);
//            if (el.getAttribute("id").equals(id.toString()))
//                el.getParentNode().removeChild(el);
//        }
//    }
//
//    @Override
//    public Optional<Rentals> delete(Long id) {
//        Optional<Rentals> optional = super.delete(id);
//        if (optional.isPresent())
//        {
//            try {
//                deleteRental(id);
//            } catch (ParserConfigurationException | SAXException | IOException e) {
//                e.printStackTrace();
//            }
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    public static void saveRental(Rentals rental) throws ParserConfigurationException, TransformerException, IOException, SAXException {
//        Document document = DocumentBuilderFactory
//                .newInstance().newDocumentBuilder()
//                .parse("./..");
//
//        Element root = document.getDocumentElement();
//        Node rentalNode = rentalToNode(rental, document);
//        root.appendChild(rentalNode);
//
//        Transformer transformer = TransformerFactory
//                .newInstance()
//                .newTransformer();
//        transformer.transform(new DOMSource(document),
//                new StreamResult(new File("")));
//    }
//
//    public static Node rentalToNode(Rentals rental, Document document){
//        Element rentalElement = document.createElement("rental");
//        rentalElement.setAttribute("id", rental.getId().toString());
//        appendChildWithTextNode(document, rentalElement, "clientID", String.valueOf(rental.getClientID()));
//        appendChildWithTextNode(document, rentalElement, "movieID", String.valueOf(rental.getMovieID()));
//        appendChildWithTextNode(document, rentalElement, "numberOfDays", String.valueOf(rental.getNumberOfDays()));
//        return rentalElement;
//
//    }
//
//    private static void appendChildWithTextNode(Document document, Node parentNode, String tagname, String textContent) {
//        Element element = document.createElement(tagname);
//        element.setTextContent(textContent);
//        parentNode.appendChild(element);
//    }
//}
