package com.xilixir.fortniteapi.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class Configuration {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static File folder = new File("configuration");
    private static Set<File> inUse = new HashSet<>();

    private String name;
    private Type type;
    private File file;

    static {
        if (!folder.exists()){
            folder.mkdir();
        }
    }

    public Configuration(String name, Type type, String... foldersToCreate){
        for (String s : foldersToCreate){
            new File(folder, s).mkdir();
        }
        this.name = name;
        this.type = type;
        this.file = new File(folder, name + ".json");
    }

    public Configuration withDefault(Object o){
        if (!exists()){
            this.write(o);
        }
        return this;
    }

    public boolean exists(){
        return this.file.exists();
    }

    public void setInUse(boolean b){
        if (b){
            inUse.add(this.file);
        } else {
            inUse.remove(this.file);
        }
    }

    public boolean write(Object o){
        while (inUse.contains(this.file)){
            //
        }
        setInUse(true);
        try {
            String data = gson.toJson(o);
            FileWriter fw = new FileWriter(this.file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(data);
            bw.close();
            fw.close();
            setInUse(false);
            return true;
        } catch (IOException ex){
            ex.printStackTrace();
        }
        setInUse(false);
        return false;
    }

    public <T> T read(){
        while (inUse.contains(this.file)){
            //
        }
        setInUse(true);
        try {
            String data = new String(Files.readAllBytes(this.file.toPath()));
            setInUse(false);
            return gson.fromJson(data, this.type);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        setInUse(false);
        return null;
    }
}
