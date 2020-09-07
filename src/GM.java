//Classe gerente de memória
public class GM {

    private int memorySize;
    private int pageSize;
    private int numberOfFrames;
    private boolean[] freeFrames;

    public GM(int memorySize) {
        this.memorySize = memorySize;
        pageSize = 16;
        numberOfFrames = memorySize/pageSize;
        freeFrames = initFrames();
    }

    private boolean[] initFrames() {
        boolean[] free = new boolean[numberOfFrames];
        for(int i = 0; i<numberOfFrames; i++){
            free[i] = true;
        }
        return free;
    }

    //Como nós estamos escrevendo o código em txt, esse número de palavras pode ser o número de linhas do txt
    public int[] aloca(int num_palavras){
        int nro_paginas = num_palavras/pageSize;
        if(num_palavras%pageSize>0) nro_paginas++;
        int[] alocados = new int[nro_paginas];
        int alocado_i = 0;

        for(int i=0; i<numberOfFrames;i++){
            if(nro_paginas == 0) break;
            if(freeFrames[i]){
                freeFrames[i] = false;
                alocados[alocado_i] = i;
                alocado_i++;
                nro_paginas--;
            }
        }
        return alocados;
    }

    //Como sabemos o que deve ser desalocado? Acho que passamos o dict de processos
    public void desaloca(){

    }

//    public String toString(){
//        return memorySize + " " + pageSize + " " + numberOfFrames;
//    }
}
