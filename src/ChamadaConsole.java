public class ChamadaConsole {
    private String type;
    private int processId;
    private int memoryAddress;
    private int[] allocatedPages;

    public ChamadaConsole(String type, int processId, int memoryAddress, int[] allocatedPages) {
        this.type = type;
        this.processId = processId;
        this.memoryAddress = memoryAddress;
        this.allocatedPages = allocatedPages;
    }

    public String getType() {
        return type;
    }

    public int getProcessId() {
        return processId;
    }

    public int[] getAllocatedPages() {
        return allocatedPages;
    }

    public int getMemoryAddress() {
        return memoryAddress;
    }
}
