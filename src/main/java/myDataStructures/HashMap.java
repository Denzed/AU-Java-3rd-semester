package myDataStructures;
import myDataStructures.LinkedList;

public class HashMap {
    // хеш-таблица, использующая список
    // ключами и значениями выступают строки
	int M = (int) 1e6;
	int keyCount;
	LinkedList[] map;
	
	public HashMap() {
		keyCount = 0;
		map = new LinkedList[M];
		for (int i = 0; i < M; ++i) {
			map[i] = new LinkedList();
		}
	}
	
	public HashMap(int customM) {
		M = customM;
		keyCount = 0;
		map = new LinkedList[M];
		for (int i = 0; i < M; ++i) {
			map[i] = new LinkedList();
		}
	}
	
	public int size() {
        // кол-во ключей в таблице
        return keyCount;
    }
	
	public LinkedList getCell(String key) {
		return map[key.hashCode() % M];
	}

    public boolean contains(String key) {
        // true, если такой ключ содержится в таблице
    	return getCell(key).contains(key);
    }

    public String get(String key) {
        // возвращает значение, хранимое по ключу key
        // если такого нет, возвращает null
    	return (String) getCell(key).get(key);
    }

    public String put(String key, String value) {
        // положить по ключу key значение value
        // и вернуть ранее хранимое, либо null
    	if (!contains(key)) {
    		++keyCount;
    	}
    	return (String) getCell(key).put(key, value);
    }

    public String remove(String key) {
        // забыть про пару key-value для переданного key
        // и вернуть забытое value, либо null, если такой пары не было
    	if (contains(key)) {
    		--keyCount;
    	}
    	return (String) getCell(key).remove(key);
    }

    public void clear() {
        // забыть про все пары key-value
    	keyCount = 0;
    	for (int i = 0; i < M; i++) {
    		map[i].clear();
    	}
    }
}
