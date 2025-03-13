public class data {
    String name;
    int profit_margin;
    int demand;
    int shelf_life;
    int retention;
    //negative
    int cost;
    int competitor;
    int return_rate;
    //hasil
    float res;
    data(String in)
    {
        String[] parseStrings=in.split("\\s+");
        name=parseStrings[0];
        profit_margin=Integer.parseInt(parseStrings[1]);
        demand=Integer.parseInt(parseStrings[2]);
        shelf_life=Integer.parseInt(parseStrings[3]);
        retention=Integer.parseInt(parseStrings[4]);
        cost=Integer.parseInt(parseStrings[5]);
        competitor=Integer.parseInt(parseStrings[6]);
        return_rate=Integer.parseInt(parseStrings[7]);
    }
}