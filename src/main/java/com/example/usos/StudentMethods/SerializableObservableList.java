package com.example.usos.StudentMethods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SerializableObservableList<T> implements Serializable {
    private ObservableList<T> list;

    public SerializableObservableList(ObservableList<T> list) {
        this.list = list;
    }

    public ObservableList<T> getList() {
        return list;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(list));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        ArrayList<T> arrayList = (ArrayList<T>) s.readObject();
        list = FXCollections.observableArrayList(arrayList);
    }
}