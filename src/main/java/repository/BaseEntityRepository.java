package repository;

public interface BaseEntityRepository<T> {
    void add(T entity);

    void update(T entity);

    void delete(T entity);
}
