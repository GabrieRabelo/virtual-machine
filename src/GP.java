import java.util.LinkedList;

public class GP {
    private VM vm;
    private GM gm;
    private LinkedList<PCB> prontos;
    private static int process_id = 0;

    public GP(VM vm, GM gm, LinkedList<PCB> prontos) {
        this.vm = vm;
        this.gm = gm;
        this.prontos = prontos;
    }

    /*solicita memoria, carrega imagem processo, cria pcb, coloca na fila de prontos
        se não ha processo rodando, libera o escalonador*/

    public void criaProcesso(String file) {
        Word[] p = vm.assembly(file);
        int[] allocatedPages = gm.alloc(p);
        PCB processo = new PCB(process_id, allocatedPages);
        process_id++;
        prontos.add(processo);
    }

    /*desaloca pcb e memoria e retira de filas*/

    public void finalizaProcesso (PCB processo){
        gm.desaloca(processo);
        prontos.remove(processo);
    }

}
