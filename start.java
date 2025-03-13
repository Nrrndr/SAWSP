import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Scanner; 
public class start {
    public static void main(String[] args)
    {
        int amount=0;
        boolean saw=true;
        float[] percent=new float[7];
        data_node head=new data_node();
        try {
            File myObj = new File(args.length>0?args[0]:"data.txt");
            Scanner myReader = new Scanner(myObj);
            while (true) {
              if(myReader.nextLine().equals("start"))break;
            }
            saw=myReader.nextBoolean();
            myReader.nextLine();
            String[] temp=myReader.nextLine().split("\\s+");
            if(temp.length<7)System.out.println("not enough of weight inputed");
            for (int i=0;i<7;i++)percent[i]=Float.parseFloat(temp[i]);
            data_node current=head;
            while (myReader.hasNextLine()) {
                amount++;
                data cur_data=new data(myReader.nextLine());
                current.cur=cur_data;
                current.next=new data_node();
                current=current.next;
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          calculate(head, amount, percent, saw);
    } 
    static void calculate(data_node head,int amount,float[] Weight,boolean saw)
    {
        data_node cur=head;
        float[][] complete=new float[amount][8];
        for(int i=0;i<amount;i++)
        {
            complete[i][0]=cur.cur.profit_margin;
            complete[i][1]=cur.cur.demand;
            complete[i][2]=cur.cur.shelf_life;
            complete[i][3]=cur.cur.retention;
            complete[i][4]=cur.cur.cost;
            complete[i][5]=cur.cur.competitor;
            complete[i][6]=cur.cur.return_rate;
            if(cur.next!=null)cur=cur.next;
        }
        if(saw)saw(head,complete,amount,Weight);
        else sp3(head,complete,amount,Weight);
    }
    static void saw(data_node head,float[][] complete,int amount,float[] Weight)
    {
        data_node current=head;
        data_node high=current;
        float highest=0;
        for (int i=0;i<4;i++)
        {
            float max=complete[0][i];
            for (int j=0;j<amount;j++)if(complete[j][i]>max)max=complete[j][i];
            for(int j=0;j<amount;j++)complete[j][i]=complete[j][i]/max;
        }
        for (int i=4;i<7;i++)
        {
            float min=complete[0][i];
            for (int j=0;j<amount;j++)if(complete[j][i]<min)min=complete[j][i];
            for(int j=0;j<amount;j++)
            complete[j][i]=min/complete[j][i];
        }
        float sum=0;
        for(int j=0;j<7;j++)sum+=Weight[j];
        for(int j=0;j<7;j++)Weight[j]/=sum;
        for (int i=0;i<amount;i++)
        {
            float res=0;
            for(int j=0;j<7;j++)res+=complete[i][j]*Weight[j];
            complete[i][7]=res;
            if(res>highest)
            {
            highest=res;
            high=current;
            }
            if(current.next!=null)current=current.next;
            
        }
        current=head;
        for (int i=0;i<amount;i++)
        {
            System.out.print(current.cur.name+"\t");
            for(int j=0;j<8;j++)
            System.out.printf("%.2f  ",complete[i][j]);
            System.out.println();
            if(current.next!=null) current=current.next;
        }
        System.out.println();
        System.out.println(high.cur.name + " : " + String.valueOf(highest));
    }
    static void sp3(data_node head,float[][] complete,int amount,float[] Weight)
    {
        data_node current=head;
        data_node high=current;
        float highest=0;
        float sum=0;
        for(int j=0;j<7;j++)sum+=Weight[j];
        for(int j=0;j<7;j++)Weight[j]/=sum;
        for (int i=0;i<amount;i++)
        {
            float res=1;
            for(int j=0;j<7;j++)
            {
                double base=complete[i][j];
                double power=Weight[j];
                if(j<4)res*=Math.pow(base, power);
                else res*=Math.pow(base, -power);
            }
            complete[i][7]=res;
            if(res>highest)
            {
            highest=res;
            high=current;
            }
            if(current.next!=null)current=current.next;
            
            
        }
        current=head;
        for (int i=0;i<amount;i++)
        {
            System.out.print(current.cur.name+"\t");
            System.out.printf("%.2f",complete[i][7]);
            System.out.println();
            if(current.next!=null) current=current.next;
        }
        System.out.println();
        System.out.println(high.cur.name + " : " + String.valueOf(highest));
    }
}
