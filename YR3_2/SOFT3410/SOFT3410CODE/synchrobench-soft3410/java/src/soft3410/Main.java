package soft3410;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SkiplistIntSet list=new SkiplistIntSet();
        //System.out.println(list);
        list.addInt(3);
        list.addInt(2);
        list.addInt(1);
        list.addInt(4);
        list.addInt(5);
        list.addInt(6);
        System.out.println(list);
        System.out.println(list.size() + "  " + list.level);
        
        list.removeInt(4);
        list.removeInt(1);
        list.removeInt(6);
        System.out.println(list.size() + "  " + list.level);
        
        list.clear();
        System.out.println(list.size() + "  " + list.level);
    }
}