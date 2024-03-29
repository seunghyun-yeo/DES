
public class Key {
static private int[] pc1=
{57, 49, 41, 33, 25, 17,  9,
  1, 58, 50, 42, 34, 26, 18,
 10,  2, 59, 51, 43, 35, 27,
 19, 11,  3, 60, 52, 44, 36,
 63, 55, 47, 39, 31, 23, 15,
  7, 62, 54, 46, 38, 30, 22,
 14,  6, 61, 53, 45, 37, 29,
 21, 13,  5, 28, 20, 12,  4};


static private int[] pc2= 
	      {14, 17, 11, 24,  1,  5,
	        3, 28, 15,  6, 21, 10,
	       23, 19, 12,  4, 26,  8,
	       16,  7, 27, 20, 13,  2,
	       41, 52, 31, 37, 47, 55,
	       30, 40, 51, 45, 33, 48,
	       44, 49, 39, 56, 34, 53,
	       46, 42, 50, 36, 29, 32};

	
static int[] shift = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

	
	int rotateLeft(int target, int amount)
	{
		int ltarget=target;
		int lamount=amount;
		int add;
		while(lamount!=0)
		{
			ltarget=(int)ltarget<<1;
			add=(int)(ltarget&0x10000000)>>>28;
			ltarget|=add;
			ltarget=(int)ltarget&0x0fffffff;
			lamount--;
		}
		return ltarget&0x0fffffff;
	}
	
	public long[] MakeSubKey(long key) {
		String keystr = String.format( "%64s", Long.toBinaryString(key) ).replace(' ', '0');
		long[] subkey=new long[16];
		String afterpc1="";
		
		for(int i=0;i<pc1.length;i++)
		{
			afterpc1= afterpc1 + keystr.charAt(pc1[i]-1);
		}
		
		//System.out.println(afterpc1.substring(0,28));
	//	System.out.println(afterpc1.substring(28,56));
		int beforepc2_1=(int)Integer.parseInt(afterpc1.substring(0,28),2);
		int beforepc2_2=(int)Integer.parseInt(afterpc1.substring(28,56),2);

		for(int i=0; i<subkey.length;i++)
		{
			beforepc2_1=rotateLeft(beforepc2_1, shift[i]);
			beforepc2_2=rotateLeft(beforepc2_2, shift[i]);

			
			subkey[i]= (((long)beforepc2_1<<28) | ((long)beforepc2_2)&0xfff_ffffL);
			
			String afterpc2="";
			String test = String.format( "%56s", Long.toBinaryString(subkey[i]) ).replace(' ', '0');
			
			for(int j=0; j<pc2.length;j++)
			{
				afterpc2=afterpc2+test.charAt(pc2[j]-1);
			}
			
			subkey[i]=(long)Long.parseLong(afterpc2,2);
			//System.out.println(subkey[i]);
		}
		
		
		return subkey;
	}
}
