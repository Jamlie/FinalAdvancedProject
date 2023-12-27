package edu.najah.cap.delete.internal.delete_service;

public class DeletingService<T extends IServiceDeleter> {
    T service;
    public DeletingService(T service) {
        this.service = service;
    }

    public void delete(String username) {
        service.delete(username);
    }
}
