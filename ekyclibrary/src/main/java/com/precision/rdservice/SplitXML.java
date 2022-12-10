package com.precision.rdservice;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SplitXML {

	public DeviceInfo SplitDeviceInfo(String strDeviceInfo) {
		DeviceInfo deviceInfo = null;
		
		
		try {
			
			if (strDeviceInfo != null && !strDeviceInfo.isEmpty())
				strDeviceInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
						+ strDeviceInfo;

			DocumentBuilderFactory dbBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = dbBuilderFactory
					.newDocumentBuilder();

			InputSource inputSource = new InputSource(new StringReader(
					strDeviceInfo));
			Document document = documentBuilder.parse(inputSource);

			deviceInfo = new DeviceInfo();

			document.getDocumentElement().normalize();
			Node nodedeviceInfo = document.getElementsByTagName("DeviceInfo")
					.item(0);
			Element deviceInfoElement = (Element) nodedeviceInfo;
			deviceInfo.dpId = deviceInfoElement.getAttribute("dpId");
			deviceInfo.rdsId = deviceInfoElement.getAttribute("rdsId");
			deviceInfo.rdsVer = deviceInfoElement.getAttribute("rdsVer");
			deviceInfo.dc = deviceInfoElement.getAttribute("dc");
			deviceInfo.mi = deviceInfoElement.getAttribute("mi");
			deviceInfo.mc = deviceInfoElement.getAttribute("mc");
			
			////////////////////// To get Serial number ////////////////////////
			try{
			NodeList paramnode = (NodeList) document.getElementsByTagName("Param");
			if(paramnode!=null){
				for (int c = 0; c < paramnode.getLength(); c++) {
			        Node currentItem = paramnode.item(c);
			        String name = currentItem.getAttributes().getNamedItem("name").getNodeValue();
			        String value = currentItem.getAttributes().getNamedItem("value").getNodeValue();
			        if(name.equalsIgnoreCase("srno")){
			        	
						deviceInfo.serialnumber = value;

			        }
                   if(name.equalsIgnoreCase("ts")){
			        	
						deviceInfo.ts = value;

			        }
                   if(name.equalsIgnoreCase("sysid")){
			        	
						deviceInfo.Sysid = value;

			        }
			    }
				
			}
			}catch(Exception ex){
				ex.getMessage().toString();
			}
			/////////////////////////////////////////////////////////////////
			
		} catch (Exception e) {
			deviceInfo = null;
		} finally {

		}
		return deviceInfo;
	}

	public RDServiceInfo SplitRDServiceInfo(String strRDServiceInfo) {
		RDServiceInfo rdServiceInfo = null;
		try {
		//	RDserviceLog.WriteLog("splitRDServiceInfo :" +strRDServiceInfo);
			if (strRDServiceInfo != null && !strRDServiceInfo.isEmpty())
				strRDServiceInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
						+ strRDServiceInfo;
			DocumentBuilderFactory dbBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = dbBuilderFactory
					.newDocumentBuilder();

			InputSource is = new InputSource(new StringReader(strRDServiceInfo));
			Document document = documentBuilder.parse(is);

			rdServiceInfo = new RDServiceInfo();

			document.getDocumentElement().normalize();
			Node noderdServiceInfo = document.getElementsByTagName("RDService")
					.item(0);
			Element rdServiceInfoElement = (Element) noderdServiceInfo;
			rdServiceInfo.status = rdServiceInfoElement.getAttribute("status");
			rdServiceInfo.info = rdServiceInfoElement.getAttribute("info");

			NodeList noderdServiceInterfaceList = document
					.getElementsByTagName("Interface");

			for (int i = 0; i < noderdServiceInterfaceList.getLength(); i++) {
				Element interfaceElement = (Element) noderdServiceInterfaceList
						.item(i);
				if (interfaceElement.getAttribute("id").equalsIgnoreCase(
						"CAPTURE")) {
				//	RDserviceLog.WriteLog("splitRDServiceInfo method: capture");

					rdServiceInfo.CapturePath = interfaceElement
							.getAttribute("path");
				} else if (interfaceElement.getAttribute("id")
						.equalsIgnoreCase("DEVICEINFO")) {
				//	RDserviceLog.WriteLog("splitRDServiceInfo method: device info");

					rdServiceInfo.DeviceInfopath = interfaceElement
							.getAttribute("path");
				}
			}
		} catch (Exception e) {
		//	RDserviceLog.WriteLog("splitRDServiceInfo method: exception : " +e.getMessage().toString());

			rdServiceInfo = null;
		} finally {

		}
		return rdServiceInfo;
	}

    // TODO: new method for multi line to single line from stringg
	public static String trim2(String input) {
		BufferedReader reader = new BufferedReader(new StringReader(input));
		StringBuffer result = new StringBuffer();
		try {
			String line;
			while ( (line = reader.readLine() ) != null)
				result.append(line.trim());
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
	}

	public CaptureResponse SplitCaptureResponse(String strcaptureRespone) {
		CaptureResponse captureResponse = null;

		try {
			captureResponse = new CaptureResponse();
			if (strcaptureRespone != null && !strcaptureRespone.isEmpty())
				strcaptureRespone = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
						+ strcaptureRespone;
			DocumentBuilderFactory dbBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = dbBuilderFactory
					.newDocumentBuilder();

			InputSource is = new InputSource(
					new StringReader(strcaptureRespone));
			Document document = documentBuilder.parse(is);

			document.getDocumentElement().normalize();
			Element elementResp = (Element) document.getElementsByTagName(
					"Resp").item(0);
			captureResponse.errCode = elementResp.getAttribute("errCode");
			captureResponse.errInfo = elementResp.getAttribute("errInfo");
			captureResponse.fCount = elementResp.getAttribute("fCount");
			captureResponse.fType = elementResp.getAttribute("fType");
			captureResponse.iCount = elementResp.getAttribute("iCount");
			captureResponse.pCount = elementResp.getAttribute("pCount");
			captureResponse.pType = elementResp.getAttribute("pType");

			if (captureResponse.errCode.equalsIgnoreCase("0")) {
				captureResponse.nmPoints = elementResp.getAttribute("nmPoints");
				captureResponse.qScore = elementResp.getAttribute("qScore");
				Element elementsKey = (Element) document.getElementsByTagName(
						"Skey").item(0);
				captureResponse.ci = elementsKey.getAttribute("ci");
				captureResponse.sessionKey = trim2(elementsKey.getTextContent().trim());
				Element elementHmac = (Element) document.getElementsByTagName(
						"Hmac").item(0);
				captureResponse.hmac = trim2(elementHmac.getTextContent().trim());
				Element elementData = (Element) document.getElementsByTagName(
						"Data").item(0);
				captureResponse.PidDatatype = elementData.getAttribute("type");
				captureResponse.Piddata = trim2(elementData.getTextContent().trim());
			}
			
//////////////////////To get Serial number ////////////////////////
	try{
	NodeList paramnode = (NodeList) document.getElementsByTagName("Param");
	if(paramnode!=null){
		for (int c = 0; c < paramnode.getLength(); c++) {
	        Node currentItem = paramnode.item(c);
	        String name = currentItem.getAttributes().getNamedItem("name").getNodeValue();
	        String value = currentItem.getAttributes().getNamedItem("value").getNodeValue();
	        if(name.equalsIgnoreCase("srno")){
	        	
	        	captureResponse.serialnumber = value;

	        }
        if(name.equalsIgnoreCase("ts")){
	        	
        	captureResponse.ts = value;

	        }
        if(name.equalsIgnoreCase("sysid")){
	        	
        	captureResponse.Sysid = value;

	        }
        if(name.equalsIgnoreCase("Locking")){
        	
        	captureResponse.Locking = value;

	        }
	    }
		
	}
	}catch(Exception ex){
		ex.getMessage().toString();
	}
	/////////////////////////////////////////////////////////////////
		} catch (Exception e) {
			captureResponse = null;
		} finally {

		}
		return captureResponse;
	}

	public UIDAIResponse SplitUIDAIResponse(String response) {
		UIDAIResponse uidaiResponse = null;
		try {
			DocumentBuilderFactory dbBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = dbBuilderFactory
					.newDocumentBuilder();

			InputSource is = new InputSource(new StringReader(response));
			Document document = documentBuilder.parse(is);

			document.getDocumentElement().normalize();
			Element elementAuthRes = (Element) document.getElementsByTagName(
					"PBAuthRes").item(0);
			uidaiResponse = new UIDAIResponse();
			uidaiResponse.ret = elementAuthRes.getAttribute("ret");
			uidaiResponse.ts = elementAuthRes.getAttribute("ts");
			uidaiResponse.err = elementAuthRes.getAttribute("err");
			uidaiResponse.txn = elementAuthRes.getAttribute("txn");
			uidaiResponse.info = elementAuthRes.getAttribute("info");
		} catch (Exception e) {
			uidaiResponse = null;
		} finally {

		}
		return uidaiResponse;
	}

	public UIDAIResponse parseResponseXML(String strResponse)  {
		UIDAIResponse uidaiResponse = null;

	//	strResponseXML = strResponse;
		/*
		 * Log.WriteLog( "123", "AUA RESPONSE"+strResponse, LogType.Debug);
		 */
		try{
		DocumentBuilderFactory dbBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = dbBuilderFactory.newDocumentBuilder();

		@SuppressWarnings("deprecation")
        Document document = documentBuilder.parse(new StringBufferInputStream(
				strResponse));
		document.getDocumentElement().normalize();
		System.out.println("Root element :"
				+ document.getDocumentElement().getNodeName());
		
		uidaiResponse = new UIDAIResponse();
		NodeList nodeList = document.getElementsByTagName("AuaAuthRes");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = (Node) nodeList.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) n;
				uidaiResponse.requestType = element.getAttribute("TransactionInfo");
				uidaiResponse.responseCode = element
						.getElementsByTagName("ResponseCode").item(0)
						.getTextContent();
				uidaiResponse.responseMessage = element
						.getElementsByTagName("ResponseMsg").item(0)
						.getTextContent();
				uidaiResponse.rrn = element
						.getElementsByTagName("RRN").item(0)
						.getTextContent();
			}
		}

		NodeList authresnodelist = document.getElementsByTagName("AuthRes");
		Node n = (Node) authresnodelist.item(0);
		Element element = (Element) n;
		uidaiResponse.errCode = element.getAttribute("err");
		uidaiResponse.ts = element.getAttribute("ts");
		uidaiResponse.ret = element.getAttribute("ret");
		uidaiResponse.txn = element.getAttribute("txn");
		uidaiResponse.info = element.getAttribute("info");
		uidaiResponse.code = element.getAttribute("code");

		}catch(Exception ex){
			ex.printStackTrace();
			uidaiResponse = null;

		}

		return uidaiResponse;
	}
}
