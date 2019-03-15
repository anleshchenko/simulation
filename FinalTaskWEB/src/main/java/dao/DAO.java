package dao;

public interface DAO<Entity, Key> {
    boolean insert(Entity entity);
    Entity select(Key key);
    boolean update(Entity entity);
    boolean delete(Key key);
}
