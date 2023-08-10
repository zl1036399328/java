<?xml version="1.0" encoding="UTF-8"?>
<definitions name="IpmsWebApi"
  targetNamespace="http://192.168.1.201:9999/IpmsWebApi.wsdl"
  xmlns:tns="http://192.168.1.201:9999/IpmsWebApi.wsdl"
  xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema"
  xmlns:ns="urn:IpmsWebApi"
  xmlns:SOAP="http://schemas.xmlsoap.org/wsdl/soap/"
  xmlns:HTTP="http://schemas.xmlsoap.org/wsdl/http/"
  xmlns:MIME="http://schemas.xmlsoap.org/wsdl/mime/"
  xmlns:DIME="http://schemas.xmlsoap.org/ws/2002/04/dime/wsdl/"
  xmlns:WSDL="http://schemas.xmlsoap.org/wsdl/"
  xmlns="http://schemas.xmlsoap.org/wsdl/">

<types>

  <schema targetNamespace="urn:IpmsWebApi"
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:ns="urn:IpmsWebApi"
    xmlns="http://www.w3.org/2001/XMLSchema"
    elementFormDefault="unqualified"
    attributeFormDefault="unqualified">
    <import namespace="http://schemas.xmlsoap.org/soap/encoding/"/>
  </schema>

</types>

<message name="GetWorkOrderSystemAccountRequest">
</message>

<message name="GetWorkOrderSystemAccountResponse">
  <part name="result" type="xsd:string"/><!-- ns__GetWorkOrderSystemAccount::result -->
</message>

<message name="IpmsWorkOrderSignInRequest">
  <part name="usr" type="xsd:string"/><!-- ns__IpmsWorkOrderSignIn::usr -->
  <part name="pwd" type="xsd:string"/><!-- ns__IpmsWorkOrderSignIn::pwd -->
</message>

<message name="IpmsWorkOrderSignInResponse">
  <part name="result" type="xsd:int"/><!-- ns__IpmsWorkOrderSignIn::result -->
</message>

<portType name="IpmsWebApiPortType">
  <operation name="GetWorkOrderSystemAccount">
    <documentation>Service definition of function ns__GetWorkOrderSystemAccount</documentation>
    <input message="tns:GetWorkOrderSystemAccountRequest"/>
    <output message="tns:GetWorkOrderSystemAccountResponse"/>
  </operation>
  <operation name="IpmsWorkOrderSignIn">
    <documentation>Service definition of function ns__IpmsWorkOrderSignIn</documentation>
    <input message="tns:IpmsWorkOrderSignInRequest"/>
    <output message="tns:IpmsWorkOrderSignInResponse"/>
  </operation>
</portType>

<binding name="IpmsWebApi" type="tns:IpmsWebApiPortType">
  <SOAP:binding style="rpc" transport="http://schemas.xmlsoap.org/soap/http"/>
  <operation name="GetWorkOrderSystemAccount">
    <SOAP:operation style="rpc" soapAction=""/>
    <input>
          <SOAP:body use="encoded" encodingStyle="http://schemas.xmlsoap.org/soap/encoding/" namespace="urn:IpmsWebApi"/>
    </input>
    <output>
          <SOAP:body use="encoded" encodingStyle="http://schemas.xmlsoap.org/soap/encoding/" namespace="urn:IpmsWebApi"/>
    </output>
  </operation>
  <operation name="IpmsWorkOrderSignIn">
    <SOAP:operation style="rpc" soapAction=""/>
    <input>
          <SOAP:body use="encoded" encodingStyle="http://schemas.xmlsoap.org/soap/encoding/" namespace="urn:IpmsWebApi"/>
    </input>
    <output>
          <SOAP:body use="encoded" encodingStyle="http://schemas.xmlsoap.org/soap/encoding/" namespace="urn:IpmsWebApi"/>
    </output>
  </operation>
</binding>

<service name="IpmsWebApi">
  <documentation>gSOAP 2.8.91 generated service definition</documentation>
  <port name="IpmsWebApi" binding="tns:IpmsWebApi">
    <SOAP:address location="http://192.168.1.201:9999/IpmsWebApiServer.cgi"/>
  </port>
</service>

</definitions>
