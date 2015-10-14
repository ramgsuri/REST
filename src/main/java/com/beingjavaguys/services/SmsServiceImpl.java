package com.beingjavaguys.services;

import java.io.IOException;
import java.util.Properties;
import org.marre.sms.SmsAddress;
import org.marre.sms.SmsException;
import org.marre.sms.SmsTextMessage;
import org.marre.sms.transport.SmsTransport;
import org.marre.sms.transport.SmsTransportManager;
import javax.annotation.Resource;
import org.marre.sms.transport.SmsTransport;
import org.springframework.beans.factory.annotation.Value;
import com.beingjavaguys.model.XEventSMSModel;

public class SmsServiceImpl implements SmsService{
	
	@Value("${smsj.clickatell.username}")
	private String username;
	
	@Value("${smsj.clickatell.password}")
	private String password;
	
	@Value("${smsj.clickatell.apiid}")
	private String apiid;
	
	@Override
	public boolean sendSMS(XEventSMSModel smsModel) {
		
		 // Load the clickatell transport
		  SmsTransport transport = null;
		  try {
		   transport = SmsTransportManager.getTransport(
		     "org.marre.sms.transport.clickatell.ClickatellTransport",
		     	setProperties());
		  } catch (SmsException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  // Connect to clickatell
		  try {
		   transport.connect();
		  } catch (SmsException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  // Create the sms message
		  SmsTextMessage textMessage = new SmsTextMessage(
				  						"A sample SMS sending tru net. ");
		  // Send the sms to "461234" from "461235"
		  try {
		   transport.send(textMessage, new SmsAddress(smsModel.getToMobileNumber()),
		     new SmsAddress(smsModel.getFromMobileNumber()));  // CC means the Country Code
		   	 System.out.println(" SMS HTTP Request send ");
		  } catch (SmsException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }

		  // Disconnect from clickatell
		  try {
		   transport.disconnect();
		   System.out.println(" transport.disconnect();");
		  } catch (SmsException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  return true;
	}
		
	
	private Properties setProperties(){
	  // The username, password and apiid is sent to the clickatell transport
	  // in a Properties
	  Properties props = new Properties();
/*	  props.setProperty("smsj.clickatell.username", username);
	  props.setProperty("smsj.clickatell.password", password);
	  props.setProperty("smsj.clickatell.apiid",apiid);
*/	  
	  props.setProperty("smsj.clickatell.username", "X37_CO");
	  props.setProperty("smsj.clickatell.password", "ELQTaZYHMLSKXJ");
	  props.setProperty("smsj.clickatell.apiid", "3562569");

	  
	  return props;
	
}
}
