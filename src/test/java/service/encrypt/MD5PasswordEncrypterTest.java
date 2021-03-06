package service.encrypt;

import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aceleradev.api.service.user.encrypt.PasswordEncrypter;
import com.aceleradev.api.service.user.encrypt.impl.BCryptPasswordEncrypter;


public class MD5PasswordEncrypterTest {
	
	private PasswordEncrypter encrypter;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Before
	public void createEncrypter() {
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.encrypter = new BCryptPasswordEncrypter(passwordEncoder);
	}
	
	@Test
	public void assertMd5HashOk() throws NoSuchAlgorithmException {
		String hash = this.encrypter.encrypt(ENCRYPTED_VALUE);
		boolean matches = this.passwordEncoder.matches(ENCRYPTED_VALUE, hash);
		assertTrue(matches);
	}
	
	private static final String ENCRYPTED_VALUE = "12345";
}
