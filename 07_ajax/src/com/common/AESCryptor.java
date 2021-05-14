package com.common;

import java.io.*;
import java.nio.charset.*;
import java.security.*;
import java.util.*;

import javax.crypto.*;

// 양방향 암호화를 지원하는 클래스
// 양방향 암호화 처리 클래스는 기본 java api에서 제공하고 있다. 
// javax.crypto 패키지, java.security 패키지에 구성되어 있다. 

// key 관리가 중요하다 !
// key는 프로젝트에서 한개만 있어야 한다 = ignore 처리 필요 ( git에 올릴 필요 없다 ) 

public class AESCryptor {

	
	
	private static SecretKey key; // 암호화, 복호화를 위한 키 객체 
	
	// private static 는 Singletone 객체 ( 함부로 수정되면 안되니까 ) 
	
	
	
	private String path;  // 파일로 저장된 암호화키의 위치(경로)
	
	// key를 파일로 저장해서 영원히 저장해놓을것임 
	// 서버를 껐다 켜도 자동으로 로드될 수 있게 
	
	
	// 기본생성자
	public AESCryptor() {
		
		// 클래스가 인스턴스화 될 때 ( 객체가 생성될 때 )
		// 기본 설정을 해야한다 
		
		// 1. key값이 있으면 ( key파일이 있으면 ) key파일에서 SecretKey 객체를 불러오고, 
		// 없으면 SecretKey 객체를 생성해서 파일로 저장 
		
		// 경로는 WEB-INF에 저장할 것 
		
		this.path = AESCryptor.class.getResource("/").getPath(); // this.path라고 쓴 이유는 필드인 path인것을 알아보기 쉽게 쓴것. 매개변수에 같은 이름의 변수가 없으면 this. 안써도 됌 
		
		System.out.println(path); // /Users/yejin/servletJsp/06_HelloMVC/web/WEB-INF/classes/
		
		
		
		this.path = this.path.substring(0, this.path.indexOf("classes")); // WEB-INF 폴더의 경로 찾는 것 
		
		System.out.println(path); // /Users/yejin/servletJsp/06_HelloMVC/web/WEB-INF/
		
		
		
		File f = new File(this.path + "/bslove.bs");
		
		// key를 저장하고 있는 파일의 이름이 bslove.bs 이다 
		// = SecretKey 객체가 저장되어 있다 
		// = SecretKey 객체를 생성했으면 이 파일이 있다 
		
		
		if(f.exists()) {
			
			// key를 저장하는 파일이 있으면 -> 불러오기 
			
			// 자동으로 스트림을 닫기 위해 try with resource 사용 
			try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f)); ) {
				
				
				this.key = (SecretKey)ois.readObject();
				
			} catch (IOException | ClassNotFoundException e) {
				
				e.printStackTrace();
				
			} 
			
			
		} else {
			
			// key를 저장하는 파일이 없으면 -> 생성하기
			
			// SecretKey 생성
			
			if(key == null) {
				
				getGenerator(); // key값을 생성하는 메소드 호출 
			}
			
		}
		
		
	} // 기본 생성자 
	
	
	
	
	
	// SecretKey를 생성하는 메소드 
	private void getGenerator() {
		
		
		SecureRandom ser = new SecureRandom(); // key값이 중복되지 않도록, 확실한 랜덤key를 생성하기 위해 사용 
		// salt 값이라고 부른대 
		
		// KeyGenerator : Key를 생성하는 클래스 
		KeyGenerator keygen = null;
		
		try {
			
			// key를 생성하기 위해서 기본값을 세팅한다 
			
			
			
			// 1. 적용할 알고리즘 세팅 ( AES인지, RSA인지 .. ) 
			// AES : key가 1개, RSA : key가 2개 ( 공개키, 개인키 )
			
			keygen = KeyGenerator.getInstance("AES");
			
			
			
			// 2. key값 생성 초기화
			
			keygen.init(128, ser);
			
			
			// 3. key 생성 
			
			this.key = keygen.generateKey(); //변수에만 저장하면 RAM에 저장되므로 영원히 저장할 수 는 없음 -> 안돼 ! -> 파일에 저장해야해
			
			
			
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		} 
		

		// 4. 생성된 key 객체를 파일로 저장 
		File f = new File(this.path + "bslove.bs");
		
		// 자동으로 스트림을 닫기 위해 try with resource 사용 
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));) {
			
			
			oos.writeObject(this.key);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	// 암호화 처리 메소드 ( 공통으로 이용하는 Template처럼 만들기 ) 
	public static String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, 
											InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		
		
		// Cipher : key를 이용해서 암호화 처리하는 클래스 
		Cipher cipher = Cipher.getInstance("AES"); // AES 맞춰줘야함 
		
		
		// 암호화 모드 설정? 
		cipher.init(Cipher.ENCRYPT_MODE, AESCryptor.key); // key가 static이라서 AESCryptor.key 이렇게 씀 
		
		
		// 매개변수로 넘어온 값을 암호화처리 하기 
		// 암호화 처리 하려면 비트연산 해야하니까 바이트로 변환해줌 
		
		byte[] encrypty = str.getBytes(Charset.forName("utf-8"));
		byte[] result = cipher.doFinal(encrypty);
		
		String resultValue = Base64.getEncoder().encodeToString(result);
		
		
		
		return resultValue;
		
		
	}
	
	
		// 복호화 처리 메소드  ( encryptedStr : 암호화된 Str이다 라는 뜻 ) 
		public static String decrypt(String encryptedStr ) throws NoSuchAlgorithmException, NoSuchPaddingException, 
												InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
			
			
			// Cipher : key를 이용해서 암호화 처리하는 클래스 
			Cipher cipher = Cipher.getInstance("AES"); // AES 맞춰줘야함 
			
			
			// 암호화 모드 설정? 
			cipher.init(Cipher.DECRYPT_MODE, AESCryptor.key); // key가 static이라서 AESCryptor.key 이렇게 씀 
			
			
			// 매개변수로 넘어온 값을 복호화처리 하기 
			byte[] decodeStr = Base64.getDecoder().decode(encryptedStr.getBytes(Charset.forName("utf-8")));
			byte[] oriVal = cipher.doFinal(decodeStr); // 복호화 됌 
			
			
			return new String(oriVal);
			
			
		}
	
	
	
}
