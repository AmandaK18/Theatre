//Author's name: Kiringodage Amanda
//Student  ID:w1956091
public class Ticket {
    int row;
    int seat;
    double price;
    Person person;

    public Ticket(int row,int seat,double price,Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    public void print(){
        System.out.println("Name: "+this.person.name);
        System.out.println("Surname: "+this.person.surname);
        System.out.println("Email: "+this.person.email);
        System.out.println("Row: "+this.row);
        System.out.println("Seat: "+this.seat);
        System.out.println("Price: Rs."+this.price+"\n");
    }
}
