import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Rotinas {
    private GP gp;
    private Escalonador escalonador;
    private Semaphore escSemaforo;

    public Rotinas(GP gp, Escalonador escalonador, Semaphore escSemaforo) {
        this.gp = gp;
        this.escalonador = escalonador;
        this.escSemaforo = escSemaforo;
    }
    //finaliza o processo, chamando o GP e escalona novo processo
    public void stop(PCB processo){
        gp.finalizaProcesso(processo);
        escSemaforo.release();
    }

    public void timer(PCB processo, CPU cpu) {
        processo.saveContext(cpu.getContext());
        escalonador.getProntos().add(processo);
        escSemaforo.release();
    }
}
