package com.yr;


public class StringFinal {
	public static void main(String []args){
		String a = "1"; //常量是一样的
		String b = "1";
		String c = a;
		String d = new String("3"); //常量改变
		System.out.println("a==b :"+(a==b));
		System.out.println("b==c :"+(b==c)); //内存地址相同(都在常量池) true
		System.out.println("a==c :"+(a==c));

		/**
		 * 如果第六行的常量为 1 那么这里会打印false
		 * 因为 == 比较的是 地址是否相同
		 * 而 equals 比较的是 值是否相同
		 * System.out.println("d==a :"+(d==a));
		 */
		System.out.println("c==d :"+(c==d)); //内存地址不同 false
		System.out.println("d.equals(b) :"+d.equals(b)); //内存地址中的值不同 false
		
		/**
		 * Test
		 */
		Double dous = 2.3;
		Float f = 1.5f;
		Long l = 2L;
		Integer i = 3;
		Short s = 2;
		Byte b2 = 1;
		Boolean bb = false;
		Character c1 = 'a';
		
	}
}
