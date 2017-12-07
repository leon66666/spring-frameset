
package wangzhongqiu.spring.core.utils.accessor;

public interface Accessor<K, V> {
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	V setValue(K key, V value) ;
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	V getValue(K key) ;
	
	/**
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	V getValue(K key, Object... params) ;
	
	/**
	 * 
	 * @return
	 */
	K[] getKeys() ;
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	V remove(K key) ;
}
