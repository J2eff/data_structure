
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchTable<K extends Comparable<K>,V> implements OrderedMap<K, V> {

    private Entry<K,V> entries[];
    private int size;

    public SearchTable(int capacity) {
        entries = new Entry[capacity];
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }

    public boolean isFull() { return size == entries.length; }

    @Override public Entry<K, V> firstEntry() {

        if(this.isEmpty()) return null;


        Entry temp = this.entries[0];
        for(int i = 1 ; i < this.entries.length; i++){
            if(this.entries[i] != null&&  (temp.key.compareTo(this.entries[i].key) >0)   ){
                temp = this.entries[i];
            }
        }

        return temp;

    }

    @Override public Entry<K, V> lastEntry() {
        if(this.isEmpty()) return null;
       

        Entry temp = this.entries[0];
        for(int i = 1 ; i < this.entries.length; i++){
            if(this.entries[i] != null&& (temp.key.compareTo(this.entries[i].key)) <0 ){
                temp = this.entries[i];
            }
        }
        return temp;

    }

    @Override public Entry<K, V> floorEntry(final K key) {
        

        if(this.isEmpty()) return null;
        
        
        Entry temp  = null;

        for(int i = 0 ; i < this.entries.length; i++){
            if( this.entries[i] != null&& (this.entries[i].key.compareTo(key) <=0 ) ){

                if(temp == null){
                    temp = this.entries[i];
                }else{
                    if((this.entries[i].key.compareTo((K)temp.key) >=0 ) ){
                        temp = this.entries[i];
                    }
                }
            
            }
            
        }

        return temp;
    }

    @Override public Entry<K, V> ceilingEntry(final K key) {
        if(this.isEmpty()) return null;
        
        
        Entry temp  = null;

        for(int i = 0 ; i < this.entries.length; i++){
            if( this.entries[i] != null&& (this.entries[i].key.compareTo(key) >=0 ) ){

                if(temp == null){
                    temp = this.entries[i];
                }else{
                    if((this.entries[i].key.compareTo((K)temp.key) <=0 ) ){
                        temp = this.entries[i];
                    }
                }
            
            }
            
        }
        return temp;


    }

    @Override public Entry<K, V> lowerEntry(final K key) {

        if(this.isEmpty()) return null;
        
        
        Entry temp  = null;

        for(int i = 0 ; i < this.entries.length; i++){
            if( this.entries[i] != null&& (this.entries[i].key.compareTo(key) <0 ) ){

                if(temp == null){
                    temp = this.entries[i];
                }else{
                    if((this.entries[i].key.compareTo((K)temp.key) >=0 ) ){
                        temp = this.entries[i];
                    }
                }
            
            }
            
        }

        return temp;


    }

    @Override public Entry<K, V> higherEntry(final K key) {
        if(this.isEmpty()) return null;

        Entry temp  = null;

        for(int i = 0 ; i < this.entries.length; i++){
            if( this.entries[i] != null&& (this.entries[i].key.compareTo(key) >0 ) ){

                if(temp == null){
                    temp = this.entries[i];
                }else{
                    if((this.entries[i].key.compareTo((K)temp.key) <=0 ) ){
                        temp = this.entries[i];
                    }
                }
            
            }
            
        }


        return temp;
    }

    @Override public Entry<K, V> get(final K key) {
        if(this.isEmpty()) return null;
        
        for(int i = 0; i< this.entries.length;i++){
            
            if(this.entries[i] != null&&entries[i].key.equals(key)){
            
                return this.entries[i];
            }
        }

        return null;
    }

    /**
     * Associates the given value with the given key, returning any overridden value.
     * @param key search key
     * @param value value of entry
     * @return old value associated with the key, if already exists, or null otherwise
     * @throws IllegalStateException if full
     */
    @Override public V put(final K key, final V value) {
        if( this.isFull() ){
            throw (new IllegalStateException("Table is full")) ;
        }
        

        for(int i = 0; i< this.size;i++){
            
            if(this.entries[i] != null&&entries[i].key.equals(key)){
                V temp = this.entries[i].value;
                this.entries[i].value = value;
                return  temp;
            }

            if(this.entries[i] == null){
                Entry temp =  new Entry(key,value);
                this.entries[i] = temp;
                size +=1;
                return null;
            }  
        }


        Entry temp =  new Entry(key,value);
        this.entries[size] = temp;
        size +=1;
        return null;
    
        
        
    }   

    @Override public V remove(final K key) {
        
        if(this.isEmpty()) return null;

        
        for(int i = 0; i< this.entries.length;i++){
            
            if( this.entries[i] != null  && ( this.entries[i].key == key) ){
                V temp = this.entries[i].value;
                this.entries[i] = null;
                this.size -=1;
                return  temp;
            }
        }


        return null;

    }

    @Override public int size() {
        return size;
    }

    @Override public Set<K> keys() {
        return Arrays.stream(entries).limit(size).map(e -> e.key).collect(Collectors.toSet());
    }

    @Override public Collection<V> values() {
        return Arrays.stream(entries).limit(size).map(e -> e.value).collect(Collectors.toList());
    }

    @Override public Collection<Entry<K, V>> entries() {

        ArrayList temp =  new ArrayList();
        
        for(int i = 0; i<this.entries.length ; i++){
            if(this.entries[i] != null ){
                temp.add(this.entries);
            }
        }

        return temp;
    }

    @Override public String toString() {
        return Arrays.toString(entries);
    }

    
    // You can define private fields and/or methods below, if necessary ...

}
