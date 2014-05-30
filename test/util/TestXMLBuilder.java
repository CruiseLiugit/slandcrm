package util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.InputSource;

import com.jamesmurty.utils.XMLBuilder;
import com.sland.model.dao.CreateXML;

public class TestXMLBuilder {


    public static final String EXAMPLE_XML_DOC_START =
            "<Projects>" +
              "<java-xmlbuilder language=\"Java\" scm=\"SVN\">" +
                "<Location type=\"URL\">http://code.google.com/p/java-xmlbuilder/</Location>" +
              "</java-xmlbuilder>" +
              "<JetS3t language=\"Java\" scm=\"CVS\">" +
                "<Location type=\"URL\">http://jets3t.s3.amazonaws.com/index.html</Location>";

    public static final String EXAMPLE_XML_DOC_END =
              "</JetS3t>" +
            "</Projects>";

    public static final String EXAMPLE_XML_DOC = EXAMPLE_XML_DOC_START + EXAMPLE_XML_DOC_END;

	
	@Test
	public void test1()
	{
		try {
			/* Build XML document in-place */
            XMLBuilder builder = XMLBuilder.create("Projects")
                .e("java-xmlbuilder")
                    .a("language", "Java")
                    .a("scm","SVN")
                    .e("Location")
                        .a("type", "URL")
                        .t("http://code.google.com/p/java-xmlbuilder/")
                    .up()
                .up()
                .e("JetS3t")
                    .a("language", "Java")
                    .a("scm","CVS")
                    .e("Location")
                        .a("type", "URL")
                        .t("http://jets3t.s3.amazonaws.com/index.html");

            /* Set output properties */
            Properties outputProperties = new Properties();
            outputProperties.put(javax.xml.transform.OutputKeys.VERSION, "1.0");
            outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");
            outputProperties.put(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");

            // Use Document@setXmlStandalone(true) to ensure OutputKeys.STANDALONE is respected.
            builder.getDocument().setXmlStandalone(true);
            outputProperties.put(javax.xml.transform.OutputKeys.STANDALONE, "yes");
            
            // Get 2-space indenting when using the Apache transformer
            //outputProperties.put("{http://xml.apache.org/xslt}indent-amount", "2");

            /* Serialize builder document */
            //StringWriter writer = new StringWriter();
            //builder.toWriter(writer, outputProperties);

            PrintWriter writer = new PrintWriter(new FileOutputStream("projects.xml"));
            builder.toWriter(writer, outputProperties);
            
            //System.out.println(builder.asString());
            //assertEquals(EXAMPLE_XML_DOC, writer.toString());

			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testCreate()
	{
		try {
			/* Build XML document in-place */
            XMLBuilder builder = XMLBuilder.create("Projects")
                .e("java-xmlbuilder")
                    .a("language", "Java")
                    .a("scm","SVN")
                    .e("Location")
                        .a("type", "URL")
                        .t("http://code.google.com/p/java-xmlbuilder/")
                    .up()
                .up()
                .e("JetS3t")
                    .a("language", "Java")
                    .a("scm","CVS")
                    .e("Location")
                        .a("type", "URL")
                        .t("http://jets3t.s3.amazonaws.com/index.html");
            
         // Basic output settings
            Properties outputProperties = new Properties();
            outputProperties.put(javax.xml.transform.OutputKeys.VERSION, "1.0");
            outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");
            outputProperties.put(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");

            // Use Document@setXmlStandalone(true) to ensure OutputKeys.STANDALONE is respected.
            builder.getDocument().setXmlStandalone(true);
            outputProperties.put(javax.xml.transform.OutputKeys.STANDALONE, "yes");

            /* Serialize builder document */
            StringWriter writer = new StringWriter();
            builder.toWriter(writer, outputProperties);

            System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+builder.asString());
            
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	/**
     * Test for strange issue raised by user on comments form where OutputKeys.STANDALONE setting
     * in transformer is ignored.
     *
     * @throws Exception
     */
    public void testSetStandaloneToYes() throws Exception {
        String xmlDoc = "<RootNode><InnerNode/></RootNode>";
        XMLBuilder builder = XMLBuilder.parse(
            new InputSource(new StringReader(xmlDoc)));

        // Basic output settings
        Properties outputProperties = new Properties();
        outputProperties.put(javax.xml.transform.OutputKeys.VERSION, "1.0");
        outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");
        outputProperties.put(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");

        // Use Document@setXmlStandalone(true) to ensure OutputKeys.STANDALONE is respected.
        builder.getDocument().setXmlStandalone(true);
        outputProperties.put(javax.xml.transform.OutputKeys.STANDALONE, "yes");

        /* Serialize builder document */
        StringWriter writer = new StringWriter();
        builder.toWriter(writer, outputProperties);

        assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + xmlDoc,
            writer.toString());
    }

    
    
	@Test
    public void testCreateListXML()
    {
		CreateXML cxml = new CreateXML();
		cxml.createListXML();
    	
    }
	
	
	@Test
    public void testCreateMonthXML()
    {
		CreateXML cxml = new CreateXML();
		cxml.createMonthXML("2013.07","path",1);
    	
    }
    
    
}
