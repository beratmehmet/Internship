package classes;

@Food(price = 22.5)
public class Pizza {
    @Time(takes = 10)
    public void Prepare(){System.out.println("prapare");}
    @Time(takes = 20)
    public void Cook(){System.out.println("cook");}

    public void Send(){System.out.println("send");}
}
