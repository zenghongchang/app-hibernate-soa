package edu.hnust.application.system;

@FunctionalInterface
public interface DBOption<T> {
    public T dbOption();
    
    public default ServiceResponse<T> execute() {
        ServiceResponse<T> response = new ServiceResponse<T>();
        try {
            T data = dbOption();
            response.setResult(data);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ServiceResponseCode.SERVER_ERROR);
            response.setDescription(ServiceResponseDescription.SERVER_ERROR);
        }
        
        return response;
    }
}