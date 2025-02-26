package ch08;

// team 2
// 하나에 두개를 구현
public class MyIFImpl implements MyIF, YourIF {

	@Override
	public void m() {
		System.out.println("MYIFImpl m()");
	}

	@Override
	public void m2() {
		System.out.println("MyIFImple m2()");
		
	}
	
}
