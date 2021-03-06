import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class OS {
    public Memory memory;
    private CPU cpu;
    private GM gm;
    private GP gp;
    private Escalonador escalonador;
    private LinkedList<PCB> prontos;
    private Rotinas rotinas;
    private Semaphore escSemaforo = new Semaphore(0);
    private Semaphore cpuSemaforo = new Semaphore(0);

    public OS() {
        memory = new Memory(escSemaforo, cpuSemaforo);
        prontos = new LinkedList();
        gm = new GM(memory.mem);
        escalonador = new Escalonador();
        gp = new GP();
        rotinas = new Rotinas();
        cpu = new CPU();

        escalonador.setAttributes(prontos, escSemaforo, cpuSemaforo, cpu);
        gp.setAttributes(gm, memory, prontos, escSemaforo, escalonador);
        rotinas.setAttributes(gp, escalonador, escSemaforo, memory);
        cpu.setAttributes(memory.mem, escSemaforo, cpuSemaforo, rotinas);

        this.run();
    }

    public void carga(String file) {
        gp.criaProcesso(file);
    }

    public void run(){
        escalonador.setName("Escalonador");
        System.out.println("\nIniciando Thread Escalonador");
        escalonador.start();
        System.out.println("Iniciando Thread CPU");
        cpu.setName("Cpu");
        cpu.start();
    }

}