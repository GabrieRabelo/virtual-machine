//Classe gerente de memória
public class GM {

    private Word[] mem;
    private int pageSize;
    private int frames;
    private boolean[] freeFrames;

    public GM(Word[] mem) {
        this.mem = mem;
        pageSize = 16;
        frames = mem.length/pageSize;
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
    public int[] alloc(Word[] words){
        int pages = words.length/pageSize;
        if(words.length%pageSize>0) pages++;
        int[] allocatedFrames = new int[pages];
        int allocated_i = 0;
        int program_i = 0;   //indice do programa

        for(int i = 0; i< frames; i++){
            if(pages == 0) break;
            if(freeFrames[i]){
                freeFrames[i] = false;
                //Aqui implementamos o for para alocar o programa de acordo com a sua tabela de páginas (allocatedPages)
                //Esse for abaixo está errado
                for (int j = pageSize * i; j < pageSize * (i + 1); j++) {
                    if(program_i >= words.length)
                        break;
                    mem[j].opCode = words[program_i].opCode;
                    mem[j].r1 = words[program_i].r1;
                    mem[j].r2 = words[program_i].r2;
                    mem[j].param = words[program_i].param;
                    program_i++;
                }
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
