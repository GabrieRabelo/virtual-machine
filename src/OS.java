import java.util.LinkedList;

public class OS {
    private VM vm;
    private GM gm;
    private LinkedList<PCB> processos;
    private static int process_id = 0;

    public OS() {
        vm = new VM();
        gm = new GM(vm.mem);
        processos = new LinkedList();
    }

    public void carga(String file) {
        Word[] p = vm.assembly(file);
        int[] allocatedPages = gm.alloc(p);
        PCB processo = new PCB(process_id, allocatedPages);
        process_id++;
        processos.add(processo);
    }

    // Função para dizer pro OS mandar a VM executar X processo. Ainda n sei ao certo a melhor maneira de fazer isso, então deixei direto na carga
    public void run(int process_id){
        vm.run(processos.get(process_id));
        gm.desaloca(processos.get(process_id));
    }

}