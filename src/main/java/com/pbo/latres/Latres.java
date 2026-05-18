package com.pbo.latres;

import com.pbo.latres.controller.DBController;
import com.pbo.latres.model.RealTodoRepository;
import com.pbo.latres.view.TodoView;

public class Latres {

    public static void main(String[] args) {
        TodoView view = new TodoView();
        RealTodoRepository repository = new RealTodoRepository();
        
        // Memanggil DBController untuk mengambil alih semua logika UI dan Database
        DBController controller = new DBController(view, repository);
    }
}