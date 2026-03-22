import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            
             System.out.println("Enter your Choice: \n1.Book \n2.Cancel \n3.Print Booked Ticket \n4.Print Available Ticket \n5.Exit");
             
             System.out.println("Enter Number only : ");
             
             int choice = sc.nextInt();
             
            switch(choice){
                case 1:
                    System.out.println("Enter Your name : ");
                    String name = sc.next();
                    
                    System.out.println("Enter Your Age : ");
                    int age = sc.nextInt();
                    
                    System.out.println("Enter your Gender(M/F) : ");
                    String gender = sc.next();
                    
                    System.out.println("Enter your Breth Preference U/M/L : ");
                    String berthPreference = sc.next();
                    
                    TicketBooking.book(name,age,gender,berthPreference);
                    break;
                case 2:
                    System.out.println("Enter your Ticket Id to cancel");
                    int id = sc.nextInt();
                    
                    TicketBooking.cancelTicket(id);
                    
                    break;
                    
                case 3:
                    System.out.println("Your booked Ticket");
                    TicketBooking.printBooked();
                    break;
                    
                case 4:
                    System.out.println("The Available Tickets");
                    TicketBooking.printAvailableTicket();
                    break;
                    
                case 5:
                    return;
                    
                default:
                     System.out.println("Your choice is Invalid choose Again, Thank you");
            }
        }
    }
}

class TicketBooking{
    
    static Passenger[] confirmed = new Passenger[63];
    static Passenger[] rac = new Passenger[18];
    static Passenger[] waiting = new Passenger[10];
    
    static int confirmedCount = 0;
    static int racCount = 0;
    static int waitingCount = 0;
    static int idCounter = 1;
    
    public static void book(String name,int age,String gender,String pref){
        Passenger p = new Passenger(idCounter++,name,age,gender,pref);
        if(age<5){
            System.out.println("Child added(No Berth)");
            return;
        }
        if(confirmedCount<63){
            p.allocated = allocateBerth(p);
            confirmed[confirmedCount++]=p;
            System.out.println("Ticket Booked :"+p.allocated);
        }
        else if(racCount<18){
            p.allocated = "RAC-SL";
            rac[racCount++]=p;
            System.out.println("Added to RAC");
        }
        else if(waitingCount<10){
            p.allocated = "Waiting-List";
            waiting[waitingCount++]=p;
            System.out.println("Added to waiting List");
        }
        else{
            System.out.println("No Ticket Available");
        }
        
    }
    
    public static String allocateBerth(Passenger p){
        if(p.age > 65){
            return "Lower";
        }
        
        if(p.gender.equalsIgnoreCase("F")){
            return "Lower";
        }
        
        if(p.berthPreference.equalsIgnoreCase("L")){
            return "Lower";
        }
        
        if(p.berthPreference.equalsIgnoreCase("U")){
            return "Upper";
        }
        
        return "Upper";
    }
    
    public static void cancelTicket(int id){
        for(int i=0;i<confirmedCount;i++){
            if(confirmed[i].id == id){
                // cancel the ticket
                for(int j=i;j<confirmedCount-1;j++){
                    confirmed[j]=confirmed[j+1];
                }
                confirmedCount--;
                
                System.out.println("Ticket Cancelled Successfully");
                
                // RAC --> Confirm
                if(racCount>0){
                    Passenger p = rac[0];
                    for(int j=0;j<racCount-1;j++){
                        rac[j]=rac[j+1];
                    }
                    racCount--;
                    
                    p.allocated="confirmed";
                    confirmed[confirmedCount++]=p;
                    
                    //Waiting --> RAC
                if(waitingCount>0){
                    Passenger w = waiting[0];
                    
                    for(int j=0;j<waitingCount-1;j++){
                        waiting[j]=waiting[j+1];
                    }
                    waitingCount--;
                    w.allocated="RAC-LS";
                    rac[racCount++]=w;
                }
                }
                
                
                
                return;
            }
        }
        System.out.println("Ticket not Found");
    }
    
    public static void printBooked(){
        int total = 0;
        
        System.out.println("======Confirmed Ticket=======");
        for(int i=0;i<confirmedCount;i++){
            printPassenger(confirmed[i]);
            total++;
        }
        
         System.out.println("======RAC Ticket=======");
         for(int j=0;j<racCount;j++){
             printPassenger(rac[j]);
             total++;
         }
         
         System.out.println("======Waiting Ticket=======");
         for(int k=0;k<waitingCount;k++){
             printPassenger(waiting[k]);
             total++;
         }
        
        System.out.println("Total Booked: " + total);
    }
    
    public static void printPassenger(Passenger p){
        System.out.println("Id: "+p.id+" Name : "+p.name+" Age : "+p.age+" Gender "+p.gender+" Allocated "+p.allocated);
    }
    
    public static void printAvailableTicket(){
        int c = 63 - confirmedCount;
        int r = 18 - racCount;
        int w = 10 - waitingCount;
        
        System.out.println("Available Confirmed Ticket "+c);
        System.out.println("Available RAC Ticket "+r);
        System.out.println("Available Waiting List Ticket "+w);
        
        System.out.println("Total Available Ticket "+(c+r+w));
    }
    
}

class Passenger{
    int id;
    String name;
    int age;
    String gender;
    String berthPreference;
    String allocated;
    
    Passenger(int id,String name,int age,String gender,String Pref){
    this.id=id;
    this.name=name;
    this.age=age;
    this.gender=gender;
    this.berthPreference=Pref;
    this.allocated="";
    }
    
    
    
}