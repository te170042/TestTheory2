package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserManagerTest {
	@BeforeAll
	static void テスト前処理() {
		
	}
	
	UserManager userM1 = UserManager.getInstance();
	
	User user01 = new User("01");
	User user02 = new User("02");
	User user03 = new User("03");
	
	@Test
	void 正常系_UserManagerインスタンス同一() {
		UserManager userM2 = UserManager.getInstance();
		assertTrue(userM1 == userM2);
	}
	@Test
	void 正常系_userList登録参照() {
		userM1.setUserToList(user01);
		userM1.setUserToList(user02);
		userM1.setUserToList(user03);
		
		List<User> testUserList = new ArrayList<User>();
		testUserList.add(user01);
		testUserList.add(user02);
		testUserList.add(user03);
		
		assertThat(userM1.getUserList()).containsAll(testUserList);
	}
	
	@Test
	void 正常系_userMap登録参照() {
		userM1.setUserToMap(user01);
		userM1.setUserToMap(user02);
		userM1.setUserToMap(user03);
		
		Map<String, User> testUserMap = new HashMap<String, User>();
		testUserMap.put("01", user01);
		testUserMap.put("02", user02);
		testUserMap.put("03", user03);
		
		assertThat(userM1.getUserMap()).containsAllEntriesOf(testUserMap);
	}

	@Test
	void 正常系_user全削除() {
		userM1.setUserToList(user01);
		userM1.setUserToMap(user01);
		
		userM1.deleteAllUser();
		assertThat(userM1.getUserList()).isEmpty();
		assertThat(userM1.getUserMap()).isEmpty();
	
	}

	@Test
	void 正常系_code指定user削除() {
		User user04 = new User("04");
		userM1.setUserToList(user04);
		userM1.setUserToMap(user04);
		
		userM1.deleteUser(user04.getCode());
		assertThat(userM1.getUserList()).doesNotContain(user04);
		assertThat(userM1.getUserMap()).doesNotContainValue(user04);
	}
	
	@Test
	void 異常系_のなにか(){
		User user04 = new User("04");
		userM1.setUserToList(user04);
		userM1.setUserToList(user04);
		
		userM1.deleteUser(user04.getCode());
		//assertThat(user04).isNotIn(userM1.getUserList());
		assertThat(userM1.getUserList()).contains(user04);
	}
	
	@Test
	@Order(1)
	void 正常系_MapList初期生成() {
		 assertThat(UserManager.getInstance()).isNotNull();
		 assertThat(userM1.getUserList()).isNotNull().isEmpty();
		 assertThat(userM1.getUserMap()).isNotNull().isEmpty();
	}
	@Test
	void 正常系_List登録順序保持() {
		userM1.setUserToList(user01);
		userM1.setUserToList(user02);
		userM1.setUserToList(user03);
		assertThat(userM1.getUserList()).containsSequence(user01,user02,user03);
	}
	
	@Test
	void 正常系_Mapキー確認() {
		Map<String, User> testUserMap = new HashMap<String, User>();
		testUserMap.put("01", user01);
		testUserMap.put("02", user02);
		testUserMap.put("03", user03);
		
		assertThat(userM1.getUserMap()).containsKeys("01","02","03");
	}
	
	

}
