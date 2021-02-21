package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import manager.VendingMachine;

import java.io.*;

public class DatabaseServiceImpl implements DatabaseService {

    private static String FILE_PATH = "database/vendingMachine.json";

    private static DatabaseServiceImpl instance;
    private Gson gson;

    public DatabaseServiceImpl() {
        gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();
    }

    public static DatabaseServiceImpl getInstance() {
        if (null == instance) {
            instance = new DatabaseServiceImpl();
        }
        return instance;
    }

    @Override
    public void write(VendingMachine vm) {
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(FILE_PATH), "UTF-8");
        } catch (Exception e) {
            System.out.println("Failed create/write file." + e);
        }

        gson.toJson(writer);

        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("failed to close file" + e);
        }
    }

    @Override
    public VendingMachine read() {
        VendingMachine vm = null;
        try {
            vm = gson.fromJson(new FileReader(FILE_PATH), VendingMachine.class);
        } catch (Exception e) {
            System.out.println("Failed to load file" + e);
        }

        return vm;
    }
}
