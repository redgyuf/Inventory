package inventory;

import java.util.ArrayList;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public abstract class Store implements StoreCapable{
	
	private static String filename = "products.xml";
	private static File file = new File(filename);
	

	private void saveToXml(Product product){		
		
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			
			Document doc;
            Element rootElement;
			
			if(file.exists()){
				doc = docBuilder.parse(file);
				rootElement = doc.createElement("Products");
			}
			else{
				doc = docBuilder.newDocument();
				rootElement = doc.createElement("Products");
				doc.appendChild(rootElement);
			}
						
			
			Element prdct = doc.createElement("Product");
			rootElement.appendChild(prdct);
			
			Attr name = doc.createAttribute("name");
			name.setValue(product.name);
			prdct.setAttributeNode(name);
			
			Attr price = doc.createAttribute("price");
			price.setValue(product.price.toString());
			prdct.setAttributeNode(price);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	protected void storeProduct(Product product){
		store(product);
	}
	
	
	protected Product createProduct(String type, String name, int price, int size){
		if(type.equals("CD")){
            return new CDProduct(name, price, size);
        }
        else{
            return new BookProduct(name, price, size);
        }
		
	}
	
	
	public ArrayList<Product> loadProducts(){
		ArrayList<Product> productsArrayList = new ArrayList<Product>();
		
		try {            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filename));
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Product");
            
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String Name = eElement.getAttribute("Name");
                    int Price = Integer.parseInt(eElement.getAttribute("Price"));

                    //saved xml file does not contain enough information
                    productsArrayList.add(createProduct("TotallyIdontKnow", Name, Price, 0));
                }
            }
            return productsArrayList;
            
        }catch (Exception e) {
        	e.printStackTrace();	
        }		
		return productsArrayList;
	}
	
		
	public void store(Product product){
		saveToXml(product);
		storeProduct(product);
	}
}
