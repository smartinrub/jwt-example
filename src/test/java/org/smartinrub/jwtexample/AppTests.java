package org.smartinrub.jwtexample;

import org.junit.runner.RunWith;
import org.smartinrub.jwtexample.controllers.AuthenticationController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AppTests {

//    @Autowired
//    private MockMvc mockMvc;
//
//	@Test
//	public void getTokenShouldReturnTokenWhenValidEmailAndPassword() throws Exception {
//		User user = new User();
//		user.setEmail("email@domain.com");
//		user.setPassword("Password1");
//				
//		mockMvc.perform(post("/token").accept(MediaType.parseMediaType("application/json;charset=UTF-8")).content("{\"email\":\"email@domain.com\",\"password\":\"Password1\"}"))
//        .andExpect(status().isOk());
//		
//	}

}
