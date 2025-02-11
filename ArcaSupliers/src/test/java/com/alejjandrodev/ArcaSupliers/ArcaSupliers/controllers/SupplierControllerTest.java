package com.alejjandrodev.ArcaSupliers.ArcaSupliers.controllers;

import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.CreateSuplierDto;
import com.alejjandrodev.ArcaSupliers.ArcaSupliers.dtos.SupplierContactDto;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class SupplierControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void create_validInput_returns200() throws JSONException {  // Renamed for clarity
        SupplierContactDto contactDto = new SupplierContactDto();
        contactDto.setName("John Doe");
        contactDto.setEmail("john.doe@example.com");
        contactDto.setPhone("123-456-7890");

        CreateSuplierDto supplierDto = new CreateSuplierDto();
        supplierDto.setName("Acme Corp");
        supplierDto.setDescription("Leading supplier of widgets");
        supplierDto.setAddress("123 Main St");
        supplierDto.setTelefono("987-654-3210");
        supplierDto.setEmail("info@acmecorp.com");
        supplierDto.setSitioWeb("http://www.ejemplo.com");
        supplierDto.setIsActive(true);
        supplierDto.setContact(contactDto);

        //1. Define header to use Json format
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //2. Create request
        HttpEntity<CreateSuplierDto> request = new HttpEntity<>(supplierDto,headers);

        //3. Send request
        ResponseEntity<String> suplierResponse = restTemplate.postForEntity("/api/suppliers",
                request,
                String.class);

        assertThat(suplierResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED); // Assert OK status
        assertThat(suplierResponse.getBody()).isNotNull();

        String expectedJson = """
            {
                "id": 1,
                "name": "Acme Corp",
                "description": "Leading supplier of widgets",
                "address": "123 Main St",
                "telefono": "987-654-3210",
                "email": "info@acmecorp.com",
                "sitioWeb": "http://www.ejemplo.com",
                "isActive": true,
                "firstPurchase": null,
                "stores": null,
                "contact": {
                    "id": 1,
                    "name": "John Doe",
                    "email": "john.doe@example.com",
                    "phone": "123-456-7890"
                }
            }
            """;

        // Use JsonAssert to compare the actual response with the expected JSON
        JSONAssert.assertEquals(expectedJson, suplierResponse.getBody(), JSONCompareMode.STRICT);
    }



}
