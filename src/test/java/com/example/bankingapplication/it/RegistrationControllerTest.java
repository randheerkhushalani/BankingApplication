package com.example.bankingapplication.it;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.bankingapplication.service.AccountService;
import com.example.bankingapplication.service.CustomerService;
import com.example.bankingapplication.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccountService accountService;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private UserService userService;
	

	@Test
	public void RegistrationTest() throws Exception {
		final String jsonRequest = "{ \"aadharNo\": \"556678901234\", \"accountType\": \"savings\", "
				+ "\"addressLine1\": \"626\", \"addressLine2\": "
				+ "\"street 4\", \"birthDate\": \"1996-11-10\", \"city\": \"Nagpur\", \"countryCode\": \"91\", "
				+ "\"emailAddress\": \"riyak@gmail.com\", \"firstName\": \"Riya\", \"initialDeposit\": 15000, "
				+ "\"lastName\": \"Khushalani\", \"mobilePhone\": \"9834705279\", \"stateProvince\": \"Maha\", "
				+ "\"zipCode\": \"440014\"}";
		final String expectedResponse = "{\"customer\":{\"lastName\":\"Khushalani\","
				+ "\"firstName\":\"Riya\",\"aadharNo\":\"556678901234\",\"address\":{\"street\":\"626,street 4\","
				+ "\"city\":\"Nagpur\",\"state\":\"Maha\",\"zip\":\"440014\"},"
				+ "\"phone\":\"9834705279\",\"email\":\"riyak@gmail.com\",\"beneficiaries\":[],\"login\":"
				+ "{\"userName\":\"riyak@gmail.com\","
				+ "\"active\":true,\"roles\":\"USER\",\"customer\":null}}}";
		RequestBuilder request = MockMvcRequestBuilders.post("/registrations").accept(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).
				andExpect(status().isCreated()).
				andExpect(content().json(expectedResponse))
						.andReturn();
	}
	
}
