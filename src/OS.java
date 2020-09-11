import java.util.LinkedList;

public class OS {
    private VM vm;
    private GM gm;
    private LinkedList<PCB> processos;
    private static int process_id = 0;

    public OS() {
        vm = new VM();
        gm = new GM(vm.tamMem);
        processos = new LinkedList<PCB>();
    }

    public void carga(String file) {
        Word[] p = vm.assembly(file);
        int[] allocatedPages = gm.alloc(p.length);
        PCB processo = new PCB(process_id, allocatedPages);
        process_id++;
        processos.add(processo);

        //Aqui implementamos o for para alocar o programa de acordo com a sua tabela de páginas (allocatedPages)
        //Esse for abaixo está errado
        for (int i = 0; i < p.length; i++) {
            vm.mem[i].opCode = p[i].opCode;     vm.mem[i].r1 = p[i].r1;     vm.mem[i].r2 = p[i].r2;     vm.mem[i].param = p[i].param;
        }
        // Por enquanto damos carga e rodamos direto para testar se está dando certo mesmo
        vm.run(p);
    }

    // Função para dizer pro OS mandar a VM executar X processo. Ainda n sei ao certo a melhor maneira de fazer isso, então deixei direto na carga
//    public void run(int process_id){
//        vm.run(processos.get(process_id).getAllocatedPages());
//    }
}