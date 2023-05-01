package kr.mjc.kys.basics.generics;

public class BoxTest {
    public static void main(String[] args) {
        BoxTest boxTest = new BoxTest();
        boxTest.useBox();
        boxTest.useTypeBox();
        boxTest.useGenericBox();}

    public void useBox(){
        Box box1 = new Box();
        box1.set(5);
        int i = (Integer) box1.get();

        box1.set("abc");
        String s = (String) box1.get();

        int x = (Integer) box1.get();
    }

    public void useTypeBox(){
        StringBox stringBox = new StringBox();
        stringBox.set("abc");
        String s = stringBox.get();

        IntegerBox integerBox = new IntegerBox();
        integerBox.set(5);
        int i = integerBox.get();
    }

    public void useGenericBox(){
        GenericBox<Integer> integerBox =
                new GenericBox<>();
        integerBox.set(5);
        int i = integerBox.get();

        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.set("abc");
        String s = stringBox.get();
    }
}
