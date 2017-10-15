package com.gen.tool.actUpdate;


	import java.net.URLConnection;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.net.URLEncoder;
	import java.io.InputStreamReader;
	import java.io.BufferedReader;
	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.xpath.XPath;
	import javax.xml.xpath.XPathExpression;
	import javax.xml.xpath.XPathFactory;
	import javax.xml.xpath.XPathConstants;
	import org.w3c.dom.Document;

	/**
	 * This class will get the lat long values.
	 */
	public class geoTrans {
	
			
//		
//		public static void main(String args[]) throws Exception {
//			String adr="338 桃園市蘆竹區五福一路92巷1號";
//			System.out.println(adrToGeo(adr));
//		}
//		
			
		
		
	  public static String adrToGeo(String adr) throws Exception {
	      System.setProperty("java.net.useSystemProxies", "true");
	      String address=adr;
	      String latLongs[] = getLatLongPositions(address);
	      String Res=latLongs[0]+","+latLongs[1];
	      System.out.println("Latitude: "+latLongs[0]+" and Longitude: "+latLongs[1]);
	      return Res;
	  }

	  public static String[] getLatLongPositions(String address) throws Exception	  {
	    int responseCode = 0;
	    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    System.out.println("URL : "+api);
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	      Document document = builder.parse(httpConnection.getInputStream());
	      XPathFactory xPathfactory = XPathFactory.newInstance();
	      XPath xpath = xPathfactory.newXPath();
	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
	      if(status.equals("OK"))
	      {
	         expr = xpath.compile("//geometry/location/lat");
	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         expr = xpath.compile("//geometry/location/lng");
	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         return new String[] {latitude, longitude};
	      }
	      else
	      {
	         throw new Exception("Error from the API - response status: "+status);
	      }
	    }
	    return null;
	  }
	}

