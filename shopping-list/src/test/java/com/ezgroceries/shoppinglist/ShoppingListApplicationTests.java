package com.ezgroceries.shoppinglist;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingListApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetCocktails() throws Exception {
		mockMvc.perform(get("/cocktails").param("search", "lalala")).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("23b3d85a-3928-41c0-a533-6538a71e17c4")));
	}

	@Test
	public void testCreateShoppingList() throws Exception {
		mockMvc.perform(post("/shopping-lists")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Stephanie's birthday\"}")).
				andDo(print()).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Stephanie's birthday")));
	}

	@Test
	public void testAddCocktailToShoppingList() throws Exception {
		mockMvc.perform(post("/shopping-lists/97c8e5bd-5353-426e-b57b-69eb2260ace3/cocktails")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[\n"
						+ "    {\n"
						+ "        \"cocktailId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"\n"
						+ "    },\n"
						+ "    {\n"
						+ "        \"cocktailId\": \"d615ec78-fe93-467b-8d26-5d26d8eab073\"\n"
						+ "    }\n"
						+ "]"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("23b3d85a-3928-41c0-a533-6538a71e17c4")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("d615ec78-fe93-467b-8d26-5d26d8eab073")));
	}


	@Test
	public void testGetShoppingList() throws Exception {
		mockMvc.perform(get("/shopping-lists/eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Stephanie's birthday")));
	}

	@Test
	public void testGetShoppingLists() throws Exception {
		mockMvc.perform(get("/shopping-lists"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Stephanie's birthday")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("My Birthday")));
	}



}
