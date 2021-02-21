import manager.VendingMachine;
import service.DatabaseService;
import service.DatabaseServiceImpl;

public class Application {

    public static void main(String[] args) {
        DatabaseService databaseService = DatabaseServiceImpl.getInstance();
        VendingMachine vm = databaseService.read();

        vm.setDbService(databaseService);
        vm.start();
    }
}
