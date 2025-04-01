package ch13;

// Car 를 빌려주는 실제 구현체
// generic P를 가진 Rentable 인터페이스를 사용함으로 이 때, 타입을 지정
public class HomeAgency implements Rentable<Home> {

	@Override
	public Home rent() {
		return new Home();
	}
}
