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

@SpringBootTest
@AutoConfigureMockMvc
public class FundTransferControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@MockBean
	private CustomerService transactionService;

	@Test
	public void FundTransferTest() throws Exception {
		final String expectedResponse = "{\"srcAccountId\":90001,\"destAccountId\":90002,\"amount\":5000}";
		RequestBuilder request = MockMvcRequestBuilders.post("/accounts/90001/transfer/90002/5000")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).
				andExpect(status().isOk()).
				andExpect(content().json(expectedResponse))
				.andReturn();
	}
}
