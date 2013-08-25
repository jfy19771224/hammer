package game.src;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import hammer.opengl2d.System2D;

import android.util.Log;

/**
 * XML∂”¡–º”‘ÿ∆˜
 * @author TK
 *
 */
public class Interpreter {

	private DocumentBuilderFactory docBuilderFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	public Interpreter()
	{
		
	}
	
	/*public Structure getXml(String address,String str)
	{
		ReadXML(address);
		Element root = doc.getDocumentElement();
		NodeList nodeList = root.getElementsByTagName("fishNode");
		for(int i =0;i< nodeList.getLength();i++)
		{ 
			Element personNode = (Element) nodeList.item(i); 
			String strname=personNode.getAttribute("name");
			if(strname.equals(str))
			{
				Structure structure=new Structure();
				structure.name=str;
				NodeList childsNodes = personNode.getChildNodes();  
				for (int j = 0; j < childsNodes.getLength(); j++) {  
					Node node = (Node) childsNodes.item(j);
					if(node.getNodeType() == Node.ELEMENT_NODE)
					{   
						Element childNode = (Element) node;  
						structure.length.add(Integer.parseInt(childNode.getAttribute("length")));
						structure.rotation.add(Integer.parseInt(childNode.getAttribute("rotation")));
						structure.angle.add(Integer.parseInt(childNode.getAttribute("angle")));
					}
				}
				structure.size=structure.length.size();
				return structure;
			}
		}
		return null;
	}*/
	private void ReadXML(String url)
    {
		if(doc==null)
		{
		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(System2D.context.getResources().getAssets().open(url));
		} catch (IOException e) {
		} catch (SAXException e) {
		} catch (ParserConfigurationException e) {
		} finally {	
		}
		}
    }
}
