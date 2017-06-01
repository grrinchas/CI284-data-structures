public class UnorderedPeopleArray extends PeopleArray {

    public UnorderedPeopleArray(int max) {
        super(max);
    }

    /**
     * Return the Person with lastName, or null. Use SequentialSearch to find the target.
     *
     * @param lastName
     * @return
     */
    public Person find(String lastName) {
        int index = indexOf(lastName);
        return index != -1 ? super.arr[index] : null;
    }

    /**
     * Insert a new person to the end of the array.
     * Throw ArrayIndexOutOfBoundsException if arr is full
     *
     * @param first
     * @param last
     * @param age
     */
    public void insert(String first, String last, int age) {
        Person person = new Person(first, last, age);
        super.arr[nElems] = person;
        super.nElems++;
    }


}

