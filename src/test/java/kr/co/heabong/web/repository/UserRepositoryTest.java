package kr.co.heabong.web.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.User;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
	
	 @Autowired 
	 private UserRepository repository;

	   //@Test
	   void testFindAll() {
		   List<User>list = repository.findAll();
		   System.out.println(list);
	   }
	   
	   //@Test
	   void testFindById() {
		   User user = repository.findById(1);
		   System.out.println(user);
	   }
	   
	   @Test
	   void testSave() {
		   User user = User.builder()
		   			   .name("")
		   			   .phoneNumber(null)
		   			   .gender(0)
		   			   .age(1)
		   			   .birthDate("")
		   			   .nickname("")
		   			   .build();
		   repository.update(user);
	   }
	   
	   //@Test
	   void testUpdate() {
		  
	   }
	   
	   //@Test
	   void testDelete() {
		  
	   }

}
