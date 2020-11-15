import java.util.concurrent.Semaphore;

public class Rotinas {
    private GP gp;
    private Escalonador escalonador;
    private Semaphore escSemaforo;
    private Semaphore cpuSemaforo;


    public Rotinas(GP gp, Escalonador escalonador, Semaphore escSemaforo, Semaphore cpuSemaforo) {
        this.gp = gp;
        this.escalonador = escalonador;
        this.escSemaforo = escSemaforo;
        this.cpuSemaforo = cpuSemaforo;
    }
    //finaliza o processo, chamando o GP e escalona novo processo
    public void stop(){
        gp.finalizaProcesso(escalonador.getRunningProcess());
        escSemaforo.release();
    }

    public void timer(Context context) {
        PCB process = escalonador.getRunningProcess();

        process.saveContext(context);
        escalonador.getProntos().add(process);
        escSemaforo.release();
    }
}
