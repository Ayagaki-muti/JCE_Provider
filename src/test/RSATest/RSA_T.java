package test.RSATest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.No_N_Name.jce.provider.No_NameProvider;

public class RSA_T {
	
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		byte[] plain = {1, -128, -115, 90, 127, 23, -10, -31, 109, 59, 118, -6, 99, 29, -34, -30, 45, -15, -109, 102, -118, 109, -93, -122, -51, 99, -114, -93, -67, 8, 37, 26, -123, 16, 3, -21, -50, -2, -40, -125, 95, -52, 25, -41, -58, 8, 16, -13, 85, 1, 76, 84, 114, 70, 7, -32, 96, -83, -98, -116, 38, -120, -99, -77, 31, -27, 49, -97, -121, 57, 67, -84, 20, -72, -31, -63, 82, -117, 57, -33, -42, 25, 93, 8, 124, -31, -63, 24, 117, -118, 72, 53, 126, 70, -79, -112, 53, -110, -51, -67, -64, 48, 79, 71, 99, 93, 86, -78, 127, 77, 126, 26, 72, -15, 31, 10, -77, -87, -88, -127, -5, -97, 124, 93, -97, -25, 24, 121};
		System.out.println(Arrays.toString(plain));
//需要加密的数据
		No_NameProvider myprovider = new No_NameProvider();	//申请provider
		Security.addProvider(myprovider);					//嵌入provider
		int outKeySize = 1024;						//设置key大小
		//获取钥匙对
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", myprovider);
		
		kpg.initialize(outKeySize);
		KeyPair outRSAKeyPair = kpg.genKeyPair();		//生成钥匙对
		//获取公钥与私钥		
		
		PublicKey publickey = outRSAKeyPair.getPublic();        //获得公钥
		PrivateKey privatekey = outRSAKeyPair.getPrivate();     //获得私钥
		
		System.out.println("公钥为：\n"+publickey.toString());
		System.out.println("私钥为：\n"+privatekey.toString());
		

		//开始加密过程
		Cipher cipher = Cipher.getInstance("RSA", myprovider);
		cipher.init(Cipher.ENCRYPT_MODE, publickey);
		byte[] tTemp = cipher.doFinal(plain);
		System.out.println(Arrays.toString(tTemp));
		
		//开始解密过程
		cipher = Cipher.getInstance("RSA", myprovider);
		cipher.init(Cipher.DECRYPT_MODE, privatekey);
		byte[] tResult = cipher.doFinal(tTemp);
		
		System.out.println(tResult.length+":"+Arrays.toString(tResult));

	}

}
