import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class OS {
    public VM vm;
    private GM gm;
    private GP gp;
    private Escalonador escalonador;
    private LinkedList<PCB> prontos;
    private Rotinas rotinas;
    private Semaphore escSemaforo = new Semaphore(0);
    private Semaphore cpuSemaforo = new Semaphore(0);

    public OS() {
        vm = new VM(escSemaforo, cpuSemaforo);
        prontos = new LinkedList();
        escalonador = new Escalonador(prontos, escSemaforo, cpuSemaforo, vm.cpu);
        gm = new GM(vm.mem);
        gp = new GP(gm, vm, prontos);
        rotinas = new Rotinas(gp, escalonador, escSemaforo, cpuSemaforo);
        vm.cpu.setRotinas(rotinas);
    }

    public void carga(String file) {
        gp.criaProcesso(file);
    }

    public void run(){
        escalonador.setName("escalonador");
        escalonador.start();
        vm.cpu.setName("cpu");
        vm.cpu.start();

    }

}