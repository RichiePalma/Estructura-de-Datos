public interface HashTable<K,V>{
	public boolean contains(K key);
	public V get(K key);
	public void put(K key, V value);
	public V delete(K key);
}
