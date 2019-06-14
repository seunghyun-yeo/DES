

public class Encode {
	int[] ip =   

		 {58, 50, 42, 34, 26, 18, 10, 2,
	      60, 52, 44, 36, 28, 20, 12, 4,
	      62, 54, 46, 38, 30, 22, 14, 6,
	      64, 56, 48, 40, 32, 24, 16, 8,
	      57, 49, 41, 33, 25, 17, 9, 1,
	      59, 51, 43, 35, 27, 19, 11, 3,
	      61, 53, 45, 37, 29, 21, 13, 5,
	      63, 55, 47, 39, 31, 23, 15, 7};


	
	int[] ipi =		
	      {40, 8, 48, 16, 56, 24, 64, 32,
	      39, 7, 47, 15, 55, 23, 63, 31,
	      38, 6, 46, 14, 54, 22, 62, 30,
	      37, 5, 45, 13, 53, 21, 61, 29,
	      36, 4, 44, 12, 52, 20, 60, 28,
	      35, 3, 43, 11, 51, 19, 59, 27,
	      34, 2, 42, 10, 50, 18, 58, 26,
	      33, 1, 41, 9, 49, 17, 57, 25};



	public String Crypto(String input, long[] subkey) {

		Feistel feistel= new Feistel();
		String afterip = "";
		String chiper="";
		
		for(int i=0;i<ip.length;i++) {
		
			afterip=afterip+input.charAt(ip[i]-1);
		}
		
		String afterip_1_str = String.format("%32s", afterip.substring(0,32) ).replace(' ', '0');
		
		//for(int i=0; i<afterip_1_str.length();i=i+4)
		//System.out.print(afterip_1_str.substring(i,i+4)+" ");
		//System.out.println();
		
		String afterip_2_str = String.format("%32s", afterip.substring(32,64) ).replace(' ', '0');

		//for(int i=0; i<afterip_2_str.length();i=i+4)
		//System.out.print(afterip_2_str.substring(i,i+4)+" ");
		//System.out.println();
		
		int afterip_1=(int)(Integer.parseUnsignedInt(afterip_1_str,2)&0xffffffff);
		int afterip_2=(int)(Integer.parseUnsignedInt(afterip_2_str,2)&0xffffffff);
		
		int tmp, afterfiestal;
		
		for(int i=0; i<subkey.length; i++) {
		
			
			afterfiestal = feistel.dofeistel(afterip_2, subkey[i]);
			//System.out.println("f : "+String.format("%32s", Integer.toBinaryString(afterfiestal)).replace(' ', '0'));
			tmp = afterip_2;
			afterip_2 = afterfiestal ^ afterip_1;
			//System.out.println("r1: "+String.format("%32s", Integer.toBinaryString(afterip_2)).replace(' ', '0'));
			afterip_1 = tmp;
		}

		long result=((long)afterip_2<<32) | afterip_1;
		String resultstr=String.format("%64s", Long.toBinaryString(result) ).replace(' ','0');
		for(int i=0; i<ipi.length;i++) {
		
			chiper=chiper + resultstr.charAt(ipi[i]-1);
		}
		//System.out.println("chiper" + chiper);
			return chiper;
	}
}
