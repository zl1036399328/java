import test.IpmsWebApiPortType;
import weather.*;
import test.IpmsWebApi;

public class Main {

    public static void main(String[] args) {
       WeatherWebService weatherWebService = new WeatherWebService();
       WeatherWebServiceSoap soap = weatherWebService.getWeatherWebServiceSoap();
       ArrayOfString weatherbyCityName = soap.getWeatherbyCityName("58238");
       for (String st : weatherbyCityName.getString()) {
           System.out.println(st);
       }
        
        // IpmsWebApi webApi = new IpmsWebApi();
        // IpmsWebApiPortType ipmsWebApiPortType = webApi.getIpmsWebApi();
        // System.out.println(ipmsWebApiPortType.getWorkOrderSystemAccount());
    }
}
