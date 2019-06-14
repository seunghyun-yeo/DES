

public class Encode {
	static int[] ip =   

   {58,	50,	42,	34,	26,	18,	10,	2,
	60,	52,	44,	36,	28,	20,	12,	4,
	62,	54,	46,	38,	30,	22,	14,	6,
	64,	56,	48,	40,	32,	24,	16,	8,
	57,	49,	41,	33,	25,	17,	9,	1,
	59,	51,	43,	35,	27,	19,	11,	3,
	61,	53,	45,	37,	29,	21,	13,	5,
	63,	55,	47,	39,	31,	23,	15,	7};

	
	static int[] ipi =		
   {40,	8,	48,	16,	56,	24,	64,	32,
	39,	7,	47,	15,	55,	23,	63,	31,
	38,	6,	46,	14,	54,	22,	62,	30,
	37,	5,	45,	13,	53,	21,	61,	29,
	36,	4,	44,	12,	52,	20,	60,	28,
	35,	3,	43,	11,	51,	19,	59,	27,
	34,	2,	42,	10,	50,	18,	58,	26,
	33,	1,	41,	9,	49,	17,	57,	25};




	public String Crypto(String input, long[] subkey) {

		Feistel feistel= new Feistel();
		String afterip = "";
		String chiper="";
		
		for(int i=0;i<64;i++)
		{
			afterip=afterip+input.charAt(ip[i]-1);
		}
/*
		String resultstr1=afterip;
		for(int i=0; i<64;i++)
		{
			chiper=chiper + resultstr1.charAt(ipi[i]-1);
		}
		System.out.println("input: " + input + " finalip: " + chiper + "equals? " + input.equals(chiper));
		
		chiper = "";
*/		
		String afterip_1_str = String.format("%64s", afterip.substring(0,32) ).replace(' ', '0');
		
		String afterip_2_str = String.format("%64s", afterip.substring(32,64) ).replace(' ', '0');
		
		int afterip_1=(int)(Long.parseLong(afterip_1_str,2)&0xffffffff);
		int afterip_2=(int)(Long.parseLong(afterip_2_str,2)&0xffffffff);
		
		int tmp, afterfiestal;
		
		for(int i=0; i<16; i++)
		{
			
			afterfiestal = feistel.dofeistel(afterip_2, subkey[i]);
			tmp = afterip_2;
			afterip_2 = afterfiestal ^ afterip_1;
			afterip_1 = tmp;
		}

		long result=((long)afterip_2<<32) | afterip_1;
		String resultstr=String.format("%64s", Long.toBinaryString(result) ).replace(' ','0');
		for(int i=0; i<64;i++)
		{
			chiper=chiper + resultstr.charAt(ipi[i]-1);
		}
			return chiper;
	}
}
