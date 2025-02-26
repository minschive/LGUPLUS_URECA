package ch13;

// 과목 등록
// Course 가 generic 타입을 사용 X
// Course 의 메소드가 파라미터를 갖는데, 그 파라미터 타입 (Applicant) 이 generic 타입을 갖는다.
public class Course {
    public static void registerCourse1(Applicant<?> applicant) {
  
    }
    
    public static void registerCourse2(Applicant<? extends Student> applicant) {
            
        }
    
    public static void registerCourse3(Applicant<? super Worker> applicant) {
        
    }
}