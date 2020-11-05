import java.util.LinkedList;

public class OS {
    private VM vm;
    private GM gm;
    private Escalonador escalonador;
    private LinkedList<PCB> prontos;
    private static int process_id = 0;

    public OS() {
        vm = new VM();
        gm = new GM(vm.mem);
        prontos = new LinkedList();
        escalonador = new Escalonador(prontos);
    }

    public void carga(String file) {
        Word[] p = vm.assembly(file);
        int[] allocatedPages = gm.alloc(p);
        PCB processo = new PCB(process_id, allocatedPages);
        process_id++;
        prontos.add(processo);
    }

    public void run(){
        PCB processo = escalonador.escalona();
        vm.run(processo);
        gm.desaloca(processo);
    }

}