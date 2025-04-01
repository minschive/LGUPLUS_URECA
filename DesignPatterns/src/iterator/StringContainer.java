package iterator;

// StringIterator 는 Container 역할을 수행하기 위해 implements Container 하였고
// Container 인터페이스에 있는 getIterator() 구현해야 함.
public class StringContainer implements Container {
   String[] strArray = {"Hello", "Iterator", "Pattern"};

   @Override
   public Iterator getIterator() {
      return new StringIterator();
   }
   
   // 클래스 내부에 private Iterator 구현 클래스 정의
   private class StringIterator implements Iterator {

      int index; // default 0 // strArray 에서 현재 들여다 보는 객체
      
      @Override
      public boolean hasNext() {
         if(index < strArray.length) return true;
         return false;
      }

      @Override
      public Object next() {
         // hasNext() 를 호출하지 않은 경우를 대비하여 if() 추가
         if(this.hasNext()) return strArray[index++];
         return null;
      }
      
   }
}