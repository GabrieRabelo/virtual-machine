import java.util.LinkedList;

public class OS {
    private VM vm;
    private GM gm;
    private GP gp;
    private Escalonador escalonador;
    private LinkedList<PCB> prontos;

    public OS() {
        vm = new VM();
        gm = new GM(vm.mem);
        prontos = new LinkedList();
        gp = new GP(vm, gm, prontos);
        escalonador = new Escalonador(prontos, vm);
    }

    public void carga(String file) {
        gp.criaProcesso(file);
    }

    public void run(){
        escalonador.escalona();
        //gm.desaloca(processo);
    }

}