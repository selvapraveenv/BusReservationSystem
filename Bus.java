public class Bus{
    private int busNo;
    private  int capacity;
    private boolean ac;
    Bus(int busNo,int capacity,boolean ac){
        this.busNo = busNo;
        this.capacity = capacity;
        this.ac = ac;
    }
    public int getBusNo(){
        return busNo;
    }
    public boolean isAc(){
        return ac;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int cap){
        capacity = cap;
    }
    public void getBusInfo(){
        System.out.println("Bus No:"+ busNo + ", Ac:"+ac+", Capacity:"+capacity);
    }
}