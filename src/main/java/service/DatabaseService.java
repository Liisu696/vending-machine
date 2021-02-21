package service;

import manager.VendingMachine;

public interface DatabaseService {
    void write(VendingMachine vm);
    VendingMachine read();
}
