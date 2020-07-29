package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.User;

public class UserTest {
	static User user = null;
	@BeforeAll
	static void テスト前処理() {
	    user = new User("");
	}
	@Test
	void 正常系_ユーザー管理コード登録参照() {
		user.setCode("aaa");
		assertThat(user.getCode()).isEqualTo("aaa");
	}
	@Test
	void 正常系_名前登録参照() {
		user.setName("bbb");
		assertThat(user.getName()).isEqualTo("bbb");
	}
	@Test
	void 正常系_年齢登録参照() {
		user.setAge(1);
		assertThat(user.getAge()).isEqualTo(1);
	}
	@Test
	void 異常系_範囲外年齢登録() {
		user.setAge(2147483647);
		assertThat(user.getAge()).isEqualTo(-1);
	}
	
	void 異常系_のなにか() {
		assertThat(user.getAge()).isEqualTo(-1);
//		user.setAge(200);
//		assertThat(user.getAge()).isEqualTo(-1);
	}

	@AfterAll
	static void テスト後処理() {
	    user = null;
	}

}
