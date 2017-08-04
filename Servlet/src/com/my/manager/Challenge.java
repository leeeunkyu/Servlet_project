package com.my.manager;

import java.util.Date;
import java.util.Random;

public class Challenge {
	//깃 추가좀 되라..
	private static final int KEY_SIZE = 128;
    private static final int ITERATION_COUNT = 100;
    private static final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
    private static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
    private static final String PASSPHRASE = "passPhrase passPhrase aes encoding algorithm";
	int response_answer;
   
	public String toclinet(){
    	String encrypt_challenge;
    	AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
        Date da = new Date();
        int r_num=0;
		Random random = new Random();
		r_num=random.nextInt(9);
        encrypt_challenge=util.encrypt(SALT,IV, PASSPHRASE, r_num+",+,"+da.getSeconds());
		response_answer=2+da.getSeconds();
		System.out.println("생성된 첼린지======>"+response_answer);
		return encrypt_challenge;
		
	}
    public String toresponse(String en){
    	String decrypt_challenge;
    	int toint_decrypt;
    	AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
    	decrypt_challenge=util.decrypt(SALT,IV, PASSPHRASE, en);
    	System.out.println("받은첼린지 응답====>"+decrypt_challenge);
    	toint_decrypt=Integer.parseInt(decrypt_challenge);
    	if(Integer.parseInt(decrypt_challenge)<=response_answer+10 
    			|| Integer.parseInt(decrypt_challenge)>=response_answer-10){
    		System.out.println("첼린지 결과=====>success");
    		return "success";
    	}
		System.out.println("첼린지 결과=====>failed");

    		return "failed";
    }
}
