public class OrderedPeopleArray extends PeopleArray {

    public OrderedPeopleArray(int max) {
        super(max);
    }

    /**
     * Return the Person with lastName, or null. Use BinarySearch to to do the finding.
     *
     * @param lastName
     * @return
     */
    public Person find(String lastName) {
        int start = 0;
        if (super.nElems > 0)
            while (start <= super.nElems) {
                int middle = (start + super.nElems) / 2;
                int comparison = super.arr[middle].getLastName().compareTo(lastName);
                if (comparison < 0) {
                    start = middle + 1;
                } else if (comparison > 0) {
                    start = middle - 1;
                } else {
                    return super.arr[middle];
                }
            }
        return null;
    }

    /**
     * Insert a new person maintaining the order of the data.
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

