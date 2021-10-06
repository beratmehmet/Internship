package classes;

@Food(price = 30)
public class Sushi {
    @Time(takes = 30)
    public void Prepare(){}
    @Time()
    public void Cook(){}
    @Time(takes = 20)
    public void Send(){}
}
