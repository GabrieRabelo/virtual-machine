import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class OS {
    private VM vm;
    private GM gm;
    private GP gp;
    private Escalonador escalonador;
    private LinkedList<PCB> prontos;
    private Rotinas rotinas;
    private Semaphore escSemaforo = new Semaphore(1);
    private Semaphore cpuSemaforo = new Semaphore(1);

    public OS() {
        vm = new VM(escSemaforo, cpuSemaforo, rotinas);
        prontos = new LinkedList();
        escalonador = new Escalonador(prontos, escSemaforo, vm.cpu);
        gm = new GM(vm.mem);
        gp = new GP(gm, prontos);
        this.rotinas = new Rotinas(gp, escalonador, escSemaforo);

    }

    public void carga(String file) {
        gp.criaProcesso(file);
    }

    public void run(){
        escalonador.start();


//        while(true){
//            if(prontos.isEmpty()){
//                return;
//            }
//            switch(vm.run(processo)){
//                case INT_STOP:
//                    System.out.println(" \n ---------------- Resultado do processo " + processo.getId() + "---------------- ");
//                    vm.dump(0,180);
//                    rotinas.stop(processo);
//                    break;
//                case INT_ENDERECO_INVALIDO:
//                    System.out.println("\n ---------------- Tentativa de acesso de endereço inválido de memória ---------------- ");
//                    rotinas.stop(processo);
//                    break;
//                case INT_INSTRUCAO_INVALIDA:
//                    System.out.println("\n ---------------- Instrução assembly Inválida ---------------- ");
//                    rotinas.stop(processo);
//                    break;
//                case INT_TIMER:
//                    rotinas.timer(processo, vm.cpu);
//                    break;
//            }
//        }

    }

}