public class PCB {

    private int id;
    private int[]allocatedPages;

    public PCB(int id, int[]allocatedPages) {
        this.id= id;
        this.allocatedPages = allocatedPages;
    }
    public int[] getAllocatedPages(){
        return this.allocatedPages;
    }

    public int getId(){
        return this.id;
    }
}