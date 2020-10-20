interface GroepInterface<T> {
    void add(T nieuw);
    T get(int i);
    int getCapaciteit();
    boolean isLeeg();
    void maakLeeg();
}
