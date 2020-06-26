package com.reve.antivirus.constants;

public class ProductTypes {
	public static String[] products = {
			"Undefined",
			"REVE Antivirus",
			"REVE Internet Security",
			"REVE Total Security",
			"REVE Server Security",
			"REVE End Point Security"
	};
	public static final int UNDEFINED = 0;
	public static final int ANTIVIRUS = 1;
	public static final int INTERNET_SECURITY = 2;
	public static final int TOTAL_SECURITY = 3;
	public static final int SERVER_SECURITY = 4;
	public static final int END_POINT_SECURITY = 5;

	public static String getProduct(int i) {
		return products[i];
	}
}
