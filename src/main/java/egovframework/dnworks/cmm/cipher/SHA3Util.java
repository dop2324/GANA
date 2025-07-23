package egovframework.dnworks.cmm.cipher;

import egovframework.dnworks.cmm.cipher.kisa.SHA3;


public class SHA3Util 
{
    private static final int MSG_1600_BUF_SIZE = 200;
    private static final int OUTPUT_BUF_SIZE = 512;
    
	public static String getSHA3(String mem_pass, int bit) 
	{
		String sha3_pwd="";
		byte buf_sha3_512[] = new byte[OUTPUT_BUF_SIZE];
		int len = 0;
		 
		SHA3 sha3 = new SHA3();
		len = bit / 8;
		sha3.sha3_hash(buf_sha3_512, len, mem_pass.getBytes(), MSG_1600_BUF_SIZE, bit, 0);
		print_hex("sha3-512", buf_sha3_512, len);

		return sha3_pwd;
	}
	
	private static void print_hex(String valName, byte[] data, int dataLen)
    {
        int i = 0;

        System.out.printf("%s :", valName);
        for (i = 0; i < dataLen; i++)
        {
            if ((i & 0x0F) == 0)
                System.out.println("");

            System.out.printf(" %02X", data[i]);
        }
        System.out.println("");
    }
}
