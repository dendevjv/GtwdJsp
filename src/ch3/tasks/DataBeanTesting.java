package ch3.tasks;

public class DataBeanTesting {

	public static void main(String[] args) {
		DataBean d1 = new DataBean("John", "GB", "Oxford");
		print(d1);
		
		DataBean d2 = new DataBean("Lena", "DE", "London");
		print(d2);
		
		DataBean d3 = new DataBean("Dima", "CH", "Tokio");
		print(d3);
	}

	private static void print(DataBean d1) {
		System.out.println("toString():      " + d1.toString());
		System.out.println("toStringInner(): " + d1.toStringInner());
	}

}
