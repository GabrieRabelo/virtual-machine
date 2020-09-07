//Classe gerente de memória
public class GM {

    private int memorySize;
    private int pageSize;
    private int frames;
    private boolean[] freeFrames;

    public GM(int memorySize) {
        this.memorySize = memorySize;
        pageSize = 16;
        frames = memorySize/pageSize;
        freeFrames = initFrames();
    }

    private boolean[] initFrames() {
        boolean[] free = new boolean[frames];
        for(int i = 0; i< frames; i++){
            free[i] = true;
        }
        return free;
    }

    //Como nós estamos escrevendo o código em txt, esse número de palavras pode ser o número de linhas do txt
    public int[] alloc(int words){
        int pages = words/pageSize;
        if(words%pageSize>0) pages++;
        int[] allocatedFrames = new int[pages];
        int allocated_i = 0;

        for(int i = 0; i< frames; i++){
            if(pages == 0) break;
            if(freeFrames[i]){
                freeFrames[i] = false;
                allocatedFrames[allocated_i] = i;
                allocated_i++;
                pages--;
            }
        }
        return allocatedFrames;
    }

    //Como sabemos o que deve ser desalocado? Acho que passamos o dict de processos
    public void desaloca(){

    }

//    public String toString(){
//        return memorySize + " " + pageSize + " " + numberOfFrames;
//    }
}
