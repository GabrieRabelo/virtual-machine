import enums.Opcode;

public class PCB {

    private int id;
    private int[]allocatedPages;
    private Context context;

    public PCB(int id, int[]allocatedPages) {
        this.id= id;
        this.allocatedPages = allocatedPages;
        this.context = new Context(0,1024,allocatedPages,new int[8], 0, new Word(Opcode.___,-1,-1,-1));
    }
    public int[] getAllocatedPages(){
        return this.allocatedPages;
    }

    public int getId(){
        return this.id;
    }

    public Context getContext() {
        return context;
    }

    public void saveContext(Context context){
        this.context = context;
    }
}