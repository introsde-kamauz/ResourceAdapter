package introsde.document.client;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import introsde.document.model.*;
import introsde.resourceadapter.soap.*;

public class Client {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://resourceadapter.herokuapp.com/?wsdl");
    	//URL url = new URL("http://10.38.224.129:6902/resourceadapter?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.resourceadapter.introsde/", "ResourceService");
        Service service = Service.create(url, qname);
        ResourceAdapterLogic hello = service.getPort(ResourceAdapterLogic.class);
 
        Preference p = new Preference();
        hello.register("Marco");
        
        Person person = hello.getPersonByUser("1");
        Artist a = new Artist();
        a.setId("44022");
        a.setName("Metallica");
        p.setArtistId(a);
        p.setUserId(person);
        hello.addArtist(p);
        
        List<Preference> l = hello.getProfilePreferences("1");
        System.out.println(l.size());
        
        System.out.println(hello.getArtistExternalId("Slash"));
        l = hello.getProfilePreferences("1");
        System.out.println(l.size());
        
        System.out.println(hello.getMotivation());
        
        hello.register("Ornella Brol");
        System.out.println(hello.getAllUsers().size());
        /**/
    }
}