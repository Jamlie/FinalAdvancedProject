package edu.najah.cap.delete_feature.internal.delete_service;

public class ServiceDeleter<T extends IServiceDeleter> {
    private final T service;

    public ServiceDeleter(T service) {
        this.service = service;
    }

    public void delete(String username) {
        service.delete(username);
    }
}
