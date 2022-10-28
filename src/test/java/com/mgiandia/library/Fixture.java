package com.mgiandia.library;

public class Fixture {
	
	public static class Borrowers {
		 public static final int GIAKOUMAKIS_ID = 1;
		 public static final int DIAMANTIDIS_ID = 2;
		 public static final int COUNT = 2;
	}
   
	public static class BorrowerCategories {

		public static final int BORROWER_CATEGORY_PROF_ID =3000;
		
	}
	
	public static class Items {

		public static int UML_USER_GUIDE_ID1 = 1;
		public static int UML_DISTILLED_ID1 = 2;
		public static int REFACTORING_ID = 3;
		public static int UML_USER_GUIDE_ID2 = 4;
		public static int UML_DISTILLED_ID2 = 5;
		
	}
	
	public static class Books {
		public static int UML_USER_GUIDE_ID = 1000;
		public static int COUNT = 3;
	}
    
    public static String API_ROOT  = "http://localhost:8081";
}
