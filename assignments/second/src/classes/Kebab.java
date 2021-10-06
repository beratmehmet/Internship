package classes;

@Food(price = 12.5)
public class Kebab {
    @Time(takes = 12)
    public void Prepare(){}
    @Time(takes = 20)
    public void Cook(){}
    @Time(takes = 10)
    public void Send(){}
}
