package com.pbo.latres.controller;

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.model.TodoRepository;
import com.pbo.latres.model.TodoTask;
import com.pbo.latres.view.TodoView;
import javax.swing.event.ListSelectionEvent;

public class DBController {
    private TodoView view; 
    private TodoRepository repository; 
    
    public DBController(TodoView view, TodoRepository repository){
        this.view = view;
        this.repository = repository;
        initController();
    }

    private void refreshTable(){
        view.showTodos(repository.getAll());
    }
    
    private void initController() {
        refreshTable();
        
        view.onAdd(e -> {
            String title = view.getTitleInput();
            String status = view.getStatusInput(); 
            
            if(title.isEmpty()){
                view.showMessage("Task tidak boleh kosong");
                return;
            }
            
            repository.insert(new InsertTodoDTO(title, status));
            refreshTable();
            view.clearForm();
        });
        
        view.onUpdate(e -> {
            int selectedId = view.getSelectedTodoId();
            
            if(selectedId == -1){
                view.showMessage("Pilih data terlebih dahulu");
                return;
            }
            
            TodoTask task = new TodoTask(
                selectedId,
                view.getTitleInput(), 
                view.getStatusInput()
            );
            
            repository.update(task);
            refreshTable();
            view.clearForm();      
        });
        
        view.onDelete(e -> {
            int selectedId = view.getSelectedTodoId();
            
            if(selectedId == -1){
                view.showMessage("Pilih data dahulu");
                return; 
            }
            
            repository.deleteById(selectedId);
            refreshTable();
            view.clearForm();        
        });
        
        view.onClear(e -> {
            view.clearForm();
        });
        
        view.onTableSelect(e -> {
            javax.swing.event.ListSelectionEvent event = (javax.swing.event.ListSelectionEvent) e;
            if (event.getValueIsAdjusting()) {
                return;
            }

            // 1. Ambil ID dari baris yang sedang diklik
            int selectedId = view.getSelectedTodoId();
            
            // Hentikan proses jika tidak ada baris valid yang terpilih
            if (selectedId == -1) {
                return;
            }
            
            // 2. Tarik data lengkap dari database berdasarkan ID
            TodoTask task = repository.getById(selectedId);
            
            // 3. Jika data ditemukan, perintahkan View untuk mengisi form
            if (task != null) {
                view.setForm(task);
            }
        });
    }
}