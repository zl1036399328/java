import java.net.MalformedURLException;
import java.net.URL;
import org.codehaus.xfire.client.Client;

public class ClientFromWSDL {
    public static void main(String[] args) throws MalformedURLException, Exception {
        Client client = new Client(new URL("http://192.168.1.201:8080/IpmsWebApi?wsdl"));
        Object[] results11 = client.invoke("sayHello", new Object[]{"Jadyer22"});
        System.out.println(results11[0]);
    }
}
