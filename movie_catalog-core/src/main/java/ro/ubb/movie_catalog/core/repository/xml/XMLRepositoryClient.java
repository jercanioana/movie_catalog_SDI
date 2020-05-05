//package ro.ubb.movie_catalog.core.repository.xml;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//import ro.ubb.movie_catalog.core.domain.entities.Client;
//import ro.ubb.movie_catalog.core.domain.entities.Movie;
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
//public class XMLRepositoryClient extends InMemoryRepository<Long,Client> {
//
//    public XMLRepositoryClient(Validator validator) throws IOException, SAXException, ParserConfigurationException {
//        super(validator);
//
//        loadData();
//    }
//
//    private static List<Client> loadData() throws ParserConfigurationException, IOException, SAXException {
//        List<Client> result = new ArrayList<>();
//        Document document = DocumentBuilderFactory.newInstance()
//                .newDocumentBuilder().parse("./data/Clients.xml");
//        Element root = document.getDocumentElement();
//        NodeList children = root.getChildNodes();
//        return IntStream
//                .range(0, children.getLength())
//                .mapToObj(index -> children.item(index))
//                .filter(node -> node instanceof Element)
//                .map(node -> createClientFromElement((Element) node))
//                .collect(Collectors.toList());
//
//    }
//    public static Client createClientFromElement(Element clientElement) {
//        Client client = new Client();
//        String id = clientElement.getAttribute("id");
//        client.setId(Long.valueOf(id));
//        client.setName(getTextFromTagName(clientElement, "name"));
//        client.setAge(Integer.parseInt(getTextFromTagName(clientElement, "age")));
//        return client;
//    }
//    private static String getTextFromTagName(Element parentElement, String tagName){
//        Node node = parentElement.getElementsByTagName(tagName).item(0);
//        return node.getTextContent();
//    }
//
//    @Override
//    public Optional<Client> save(Client client) throws ValidatorException {
//        Optional<Client> optional = super.save(client);
//        if(optional.isPresent()){
//            return optional;
//        }
//        try {
//            saveClient(client);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    public static void deleteClient(Long id) throws ParserConfigurationException, IOException, SAXException {
//        Document document = DocumentBuilderFactory
//                .newInstance().newDocumentBuilder()
//                .parse("./data/Clients.xml");
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
//    public Optional<Client> delete(Long id) {
//        Optional<Client> optional = super.delete(id);
//        if (optional.isPresent())
//        {
//            try {
//                deleteClient(id);
//            } catch (ParserConfigurationException | SAXException | IOException e) {
//                e.printStackTrace();
//            }
//            return optional;
//        }
//        return Optional.empty();
//    }
//
//    public static void saveClient(Client client) throws ParserConfigurationException, TransformerException, IOException, SAXException {
//        Document document = DocumentBuilderFactory
//                .newInstance().newDocumentBuilder()
//                .parse("./data/Clients.xml");
//
//        Element root = document.getDocumentElement();
//        Node clientNode = clientToNode(client, document);
//        root.appendChild(clientNode);
//
//        Transformer transformer = TransformerFactory
//                .newInstance()
//                .newTransformer();
//        transformer.transform(new DOMSource(document),
//                new StreamResult(new File("./data/Clients.xml")));
//    }
//
//    public static Node clientToNode(Client client, Document document){
//        Element clientElement = document.createElement("client");
//        clientElement.setAttribute("id", client.getId().toString());
//        appendChildWithTextNode(document, clientElement, "name", client.getName());
//        appendChildWithTextNode(document, clientElement, "age",  String.valueOf(client.getAge()));
//        return clientElement;
//
//    }
//
//    private static void appendChildWithTextNode(Document document, Node parentNode, String tagname, String textContent) {
//        Element element = document.createElement(tagname);
//        element.setTextContent(textContent);
//        parentNode.appendChild(element);
//    }
//}
