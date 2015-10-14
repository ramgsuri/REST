package com.beingjavaguys.rest;

import java.nio.charset.Charset;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.beingjavaguys.model.Employee;
import com.beingjavaguys.model.Status;
import com.beingjavaguys.model.XEventSMSModel;
import com.beingjavaguys.services.SmsServiceImpl;
import com.google.gson.Gson;

//import com.salesmanager.core.utils.ajax.AjaxResponse;
//import com.salesmanager.web.entity.shop.ContactForm;

public class CRUDAPITest {
	
	private RestTemplate restTemplate;


	private HttpHeaders getHeader(){
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		//MediaType.APPLICATION_JSON //for application/json
		headers.setContentType(mediaType);
		return headers;
	}
	
	/**
	 * Contact us email
	 * @throws Exception
	 */
/*	@Test
	@Ignore
	public void contactUs() throws Exception {
		restTemplate = new RestTemplate();
		
		
		ContactForm contact = new ContactForm();
		contact.setComment("A few good words for you!");
		contact.setEmail("me@test.com");
		contact.setName("Johny Depp");
		contact.setSubject("Hello ny friend");
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(contact);
		
		System.out.println(json);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(json, getHeader());
		
		ResponseEntity<AjaxResponse> response = restTemplate.exchange("http://localhost:9996/sm-shop/services/public/DEFAULT/contact", HttpMethod.POST, httpEntity, AjaxResponse.class);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody() + " Success sending contact");
		}
	}
	
*/	

	@Test
	public void getCustomers() throws Exception {
		
		
		//get customers
		restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeader());
		ResponseEntity<Employee[]> response = restTemplate.exchange("http://104.207.148.112:9090/SpringRestCrud/employee/list", HttpMethod.GET, httpEntity, Employee[].class);
		System.out.println("The response is ----"+response.getBody());
		Gson gson = new Gson();
		String jsonString = gson.toJson(response.getBody());
		System.out.println("The response is ----"+response.getBody());
		System.out.println("JSON :"+jsonString);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody().length + " Customer records found.");
		}
	}
	
	@Test
	public void postCustomer() throws Exception {
		restTemplate = new RestTemplate();
		Employee customer = new Employee();
		customer.setId(25);
		customer.setFirstName("Gagan");
		customer.setLastName("Suri");
		customer.setEmail("asdsad@asds.com");
		customer.setPhone("9999991191");
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(customer);
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());
		ResponseEntity response = restTemplate.postForEntity("http://104.207.148.112:9090/SpringRestCrud/employee/create", entity, Status.class);
		Status status = (Status) response.getBody();
		System.out.println("New Customer ID : " + status.getCode());
		
	}
	
	@Test
	public void sendSMS() throws Exception {
		restTemplate = new RestTemplate();
		XEventSMSModel model = new XEventSMSModel();
		model.setMessageId(1);
		model.setFromMobileNumber("919920283339");
		model.setToMobileNumber("919582983628");
		model.setPriority("HIGH");
		model.setTextMessage("Thanks");
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(model);
		System.out.println("The json string is "+json);
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());
		ResponseEntity response = restTemplate.postForEntity(
						"http://104.207.148.112:9090/x37sms/x37/sendsms", entity, Status.class
						);
		Status status = (Status) response.getBody();
		System.out.println("Status ID : " + status.getCode());
		System.out.println("Status Message : " + status.getMessage());
		
		
	}
	
}
