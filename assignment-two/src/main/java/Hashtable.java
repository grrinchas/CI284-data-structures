//@formatter:off

/* ***************************************************************************
 *                          Changes to source code
 * ***************************************************************************/

/**
 * 1. Added DEFAULT_PROBE field.
 *
 *    This will increase code readability. Also, some Hashtable clients may
 *    wa to know default probe, so instead of checking documentation they
 *    can access it directly through class.
 *
 * 2. Added static modifier to maxLoad field and DBL_HASH_K field.
 *
 *    Every instance of Hashtable will have the same values of
 *    maxLoad and DBL_HASH_K. Thus having one class field is more memory
 *    efficient than having multiple instance fields.
 *
 * 3. Changed maxLoad name to MAX_LOAD. Due to Java naming conventions for
 *    constants.
 *
 * 4. Removed instance field max for length of an array,
 *
 *    Having less non final instance variables decreases mutability of the
 *    class (state can't be change if there are no state). Also it
 *    increases maintainability and readability (less things to keep track
 *    when reading source code). In addition, lowered scope of the variable,
 *    reduces bugs.
 *
 * 5. Added final modifier to probType instance field.
 *
 *    Some internal methods depends on probType, thus changing it after
 *    Hashtable initialization will break them.
 *
 * 6. Changed Object[] array to Pair<V>[] array.
 *
 *    Pair<V>[] gives us compile time type safety, meaning we can
 *    only put V type in the array. Also, accessing entry
 *    from the array doesn't required explicit cast to Pair object.
 *
 * 7. Changed inner Pair class of Hashtable to a static nested class.
 *
 *    The main advantage of having non static nested (inner) class is that
 *    it can access outer class private members. But Pair class don't do that,
 *    so there is no need of declaring it as inner class.
 *
 * 8. Removed private access modifiers from Pair class fields (key and value).
 *
 *    Nested class Pair is a private, not visible to outside world, thus
 *    hiding its members as well. In this case private modifiers are
 *    redundant.
 *
 * 9. Removed public access modifier from Pair class constructor.
 *
 *    The only class which will create Pair objects will be outer class
 *    (Hashtable). And in Java outer classes have access to all members of
 *    its nested classes, even private constructors. Thus public modifier is
 *    redundant.
 *
 * 10. Generified Pair class (from Pair to Pair<V>) for compile time
 *     type safety.
 *
 * 11. Added new private method findKeyNullIndex().
 *
 *     It is useful to have a method which would return index where the key is
 *     stored in the array, or index of null if key is not found. It helps
 *     to optimize insertion by performing a search only once. (see comments)
 *
 * 12. Removed methods (V find()) and (int findEmpty()).
 *
 *     The functionality of these two methods has been replaces by
 *     (int findKeyNullIndex()) method.
 *
 */

/* ***************************************************************************
 *                        Complexity of get() and put()
 * ***************************************************************************/

/**
 * 1. Complexity of get() method
 *
 *    In the best case scenario get() method will have constant time O(1) complexity.
 *    This would be the case if every key would have different hashcode in the table
 *    OR the key would match the first key even if there was a collision.
 *
 *    In the worst case scenario get() method will have linear time O(n) complexity
 *    where n is the numbers of items in the table (not an array length) and linear.
 *    probe type. This would be the case if the table would contain keys with
 *    the same hashcode AND the supplied key would be the last key inserted or would
 *    not exist. In this case hashtable is no better than linked list. Good news is
 *    that this scenario is unlikely to occur, due to good hash function combined with
 *    double hash probing which reduces collisions.
 *
 *    Average complexity depends on hash function, load factor and probe type. For example,
 *    if load factor varies between o.1 and 0.3 then total comparisons needed for
 *    unsuccessful search will be 1-2 independent from probe type in use. And that
 *    makes sense, because get() method terminates when the key or empty space is
 *    and in this case hash table is only 10 to 30 % full, meaning 70% will be free space,
 *    and even with linear probing, the possibility of worst case scenario is almost zero,
 *    unless your hash function returns constant every time :). So on average we are looking
 *    at O(1) complexity.
 *
 *    Total comparisons while searching for the key, when load factor is higher than 0.5-0.7
 *    grows exponentially. And linear probing is the most vulnerable for this, due to it's
 *    linear insertion when collision occurs, creating long chain clusters, which get()
 *    method has to go through to find an item. On the other hand for quadratic and double
 *    hashing probing distributes items more randomly thus leaving gaps between elements,
 *    and we can search them more efficiently.
 *
 *    So the actual average complexity it is hard to measure do to many variables
 *    (hash function, probe type, load factor). But in most cases and in this hashtable
 *    implementation average complexity is O(1), only because worst case scenarios are very rare.
 *
 * 2. Complexity of put() method.
 *
 *    In most cases put() method complexities are identical to get() - best and
 *    average cases are O(1) and worst O(n). Because internally it uses same procedure as get()
 *    to insert an item. The only difference is that, if load factor reaches maximum
 *    allowed, in our case 0.6, then we have to copy all the items in the array to the new
 *    one with bigger size to decrease load factor. And we know that going through all the
 *    items in the array of size n complexity is O(n). But this case doesn't occur very often,
 *    making no notable difference to complexity.
 *
 */

/**
 * A HashTable with no deletions allowed. Duplicates overwrite the existing value. Values are of
 * type V and keys are strings -- one extension is to adapt this class to use other types as keys.
 *
 * The underlying data is stored in the array `arr', and the actual values stored are pairs of
 * (key, value). This is so that we can detect collisions in the hash function and look for the next
 * location when necessary.
 *
 * @author Dainius Grinciukas
 */

//@formatter:on


import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;


@SuppressWarnings("ALL")
public class Hashtable<V> {

    public static enum PROBE_TYPE {
        LINEAR_PROBE, QUADRATIC_PROBE, DOUBLE_HASH
    }

    public static final PROBE_TYPE DEFAULT_PROBE = PROBE_TYPE.LINEAR_PROBE;
    private static final BigInteger DBL_HASH_K = BigInteger.valueOf(8);
    private static final double MAX_LOAD = 0.6;

    private Pair<V>[] arr; //an array of Pair objects, where each pair contains the key and value
    private int itemCount; //the number of items stored in arr
    final PROBE_TYPE probeType; //the type of probe to use when dealing with collisions


    /**
     * Create a new Hashtable with a given initial capacity and using a given probe type
     *
     * @param initialCapacity the initial capacity
     * @param pt              the pt
     */
    public Hashtable(int initialCapacity, PROBE_TYPE pt) {
        this.arr = new Pair[this.nextPrime(initialCapacity)];
        this.probeType = pt;
    }

    /**
     * Create a new Hashtable with a given initial capacity and using the default probe type
     *
     * @param initialCapacity the initial capacity
     */
    public Hashtable(int initialCapacity) {
        this(initialCapacity, DEFAULT_PROBE);
    }

    /**
     * Store the value against the given key. If the loadFactor exceeds MAX_LOAD, call the resize
     * method to resize the array. the If key already exists then its value should be overwritten.
     * <p>
     * findKeyNullIndex() method is beeing used to find the index of the key as well as the position
     * of next null to insert the item.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(String key, V value) {
        if (this.getLoadFactor() > this.MAX_LOAD)
            this.resize();

        int index = this.findKeyNullIndex(this.hash(key), 0, key);
        if (index > -1) {
            this.arr[index].value = value;
        } else {
            this.arr[-index - 1] = new Pair<V>(key, value);
            this.itemCount++;
        }
    }

    /**
     * Find the value stored for this key, starting the search at position startPos in the array. Uses findKeyNullIndex
     * method to check if index for this key exists. If it does, then uses that index to retrieve value, otherwise
     * returns null.
     *
     * @param key the key
     * @return v
     */
    public V get(String key) {
        int index = findKeyNullIndex(hash(key), 0, key);
        return index > -1 ? this.arr[index].value : null;
    }

    /**
     * Return true if the Hashtable contains this key, false otherwise
     *
     * @param key the key
     * @return boolean
     */
    public boolean hasKey(String key) {
        return findKeyNullIndex(hash(key), 0, key) > -1;
    }


    /**
     * Return all the keys in this Hashtable as a collection
     *
     * @return keys
     */
    public Collection<String> getKeys() {
        return Arrays.stream(this.arr)
                     .filter(Objects::nonNull)
                     .map(pair -> pair.key)
                     .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Return the load factor, which is the ratio of itemCount to max
     *
     * @return load factor
     */
    public double getLoadFactor() {
        return (double) this.itemCount / this.getCapacity();
    }

    /**
     * return the maximum capacity of the Hashtable
     *
     * @return capacity
     */
    public int getCapacity() {
        return this.arr.length;
    }

    /**
     * Find the index for specified key starting the search at position startPos in the array. If
     * the item at position startPos is null, then Hashtable does not contain a key, and will return
     * negated index of null - 1. Meaning, that one less of absolute value of that index (abs(index -1)
     * indicates empty space which can be used to insert new item. In other words if returned value
     * is negative then it is a position of null, if it is positive then it is index of the key.
     * NOTE! we have to subtract -1 due  to the fact that 0 can not be negated.
     * <p>
     * If the key stored in the pair at position startPos matches the key we're looking for, return the
     * index of the key. If the key stored in the pair at position startPos does not match the key we're
     * looking for, this is a hash collision so use the getNextLocation method with an incremented value of
     * stepNum to find the next location to search (the way that this is calculated will differ
     * depending on the probe type being used). Then use the value of the next location in a recursive call to find.
     *
     * @param startPos
     * @param stepNum
     * @param key
     * @return index if key exists, one less of negative index if null
     */

    private int findKeyNullIndex(int startPos, int stepNum, String key) {
        Pair<V> pair = this.arr[startPos];
        return pair == null ? -startPos - 1 : pair.key.equals(key) ? startPos : this.findKeyNullIndex(
                this.getNextLocation(startPos, stepNum, key), stepNum++, key);
    }

    /**
     * Finds the next position in the Hashtable array starting at position startPos. If the linear
     * probe is being used, we just increment startPos. If the double hash probe type is being used,
     * add the double hashed value of the key to startPos. If the quadratic probe is being used, add
     * the square of the step number to startPos.
     *
     * @param startPos
     * @param stepNum
     * @param key
     * @return
     */
    private int getNextLocation(int startPos, int stepNum, String key) {
        int step = startPos;
        switch (probeType) {
            case LINEAR_PROBE:
                step++;
                break;
            case DOUBLE_HASH:
                step += doubleHash(key);
                break;
            case QUADRATIC_PROBE:
                step += stepNum * stepNum;
                break;
            default:
                break;
        }
        return step % this.getCapacity();
    }

    /**
     * A secondary hash function which returns a small value (less than or equal to DBL_HASH_K)
     * to probe the next location if the double hash probe type is being used
     *
     * @param key
     * @return
     */
    private int doubleHash(String key) {
        BigInteger hashVal = BigInteger.valueOf(key.charAt(0) - 96);
        for (int i = 1; i < key.length(); i++) {
            BigInteger c = BigInteger.valueOf(key.charAt(i) - 96);
            hashVal = hashVal.multiply(BigInteger.valueOf(27)).add(c);
        }
        return DBL_HASH_K.subtract(hashVal.mod(DBL_HASH_K)).intValue();
    }

    /**
     * Return an int value calculated by hashing the key. See the lecture slides for information
     * on creating hash functions. The return value should be less than max, the maximum capacity
     * of the array
     *
     * @param key
     * @return
     */
    private int hash(String key) {
        int hash = 0;
        for (int i = 1; i < key.length(); i++) {
            // seems that 31 constant is a popular choice for implementing hash function
            hash = 31 * hash + key.charAt(i);
        }
        hash %= this.getCapacity();
        // before returning hash we have to deal with overflow
        return hash < 0 ? hash + this.getCapacity() : hash;
    }

    /**
     * Return true if n is prime
     *
     * @param n
     * @return
     */
    private boolean isPrime(int n) {
        // We exclude number 2 from primes. Because it is even, and there is no point of having
        // hashtable of size 2 (it will be resiszed as soon as second element is inserted).
        if (n < 3 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i < n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the smallest prime number which is larger than n
     *
     * @param n
     * @return
     */
    private int nextPrime(int n) {
        return isPrime(n) ? n : nextPrime(++n);
    }

    /**
     * Resize the hashtable, to be used when the load factor exceeds MAX_LOAD. The new size of
     * the underlying array should be the smallest prime number which is at least twice the size
     * of the old array.
     */
    private void resize() {
        Pair<V>[] oldArray = this.arr;
        this.arr = new Pair[nextPrime(this.getCapacity() * 2)];
        Arrays.stream(oldArray).filter(Objects::nonNull).forEach(pair -> this.put(pair.key, pair.value));
    }

    /**
     * Instances of Pair are stored in the underlying array. We can't just store
     * the value because we need to check the original key in the case of collisions.
     *
     * @author jb259
     */
    private static class Pair<V> {
        String key;
        V value;

        Pair(String key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
