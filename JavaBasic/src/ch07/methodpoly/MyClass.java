package ch07.methodpoly;

import java.util.Objects;

public class MyClass { // extends Object
	int n;
	String str;
	
	// Source -> generate toString()
	@Override
	public String toString() {
		return "MyClass [n=" + n + ", str=" + str + "]";
	}

	// toString() 재정의 하지 않은 경우 Object 의 toString() 사용
//	@Override
//	public String toString() {
//		return "n = " + n + ", str = " + str;
//	}
	
	@Override
	public int hashCode() {
		return Objects.hash(n, str);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyClass other = (MyClass) obj;
		return n == other.n && Objects.equals(str, other.str);
	}

}
