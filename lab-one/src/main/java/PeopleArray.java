public abstract class PeopleArray {

    protected Person[] arr;
    protected int nElems;

    public PeopleArray(int max) {
        arr = new Person[max];
        nElems = 0;
    }

    /**
     * Return the Person with lastName, or null. This method is abstract and must be
     * be overridden in subclasses of PeopleArray.
     *
     * @param lastName
     * @return
     */
    public abstract Person find(String lastName);

    /**
     * Insert a new Person object into the array, or throw ArrayIndexOutOfBoundsException
     * if arr is full. This method is abstract and must be overridden in subclasses of PeopleArray.
     *
     * @param first
     * @param last
     * @param age
     */
    public abstract void insert(String first, String last, int age);

    /**
     * Return index in the array of the Person with lastName. If not found return -1.
     *
     * @param lastName
     * @return
     */
    public int indexOf(String lastName) {
        int index = -1;
        boolean found = false;
        for (int i = 0; i < nElems && !found; i++) {
            Person person = this.arr[i];
            if (person != null && person.getLastName().equals(lastName)) {
                found = true;
                index = i;
            }
        }
        return index;
    }

    /**
     * Remove the Person object with lastName from the array. Return false if the person is not found, true otherwise.
     * One way to do this is to use one loop to find the index of the object then, starting from the position of the
     * object, loop through the rest of the array setting arr[i] to arr[i+1]
     *
     * @param lastName
     * @return
     */
    public boolean delete(String lastName) {
        boolean result = false;
        int index = indexOf(lastName);
        if (index != -1) {
            for (; index < this.nElems; index++) {
                this.arr[index] = this.arr[index + 1];
            }
            this.nElems--;
            result = true;
        }
        return result;
    }

    /**
     * Print the Person object with lastName to the screen. Do nothing if not found.
     *
     * @param lastName
     */
    public void printElem(String lastName) {
        Person person = this.find(lastName);
        if (person != null) {
            System.out.println(person);
        }
    }

    /**
     * Reset the array
     */
    public void clear() {
        this.arr = new Person[this.arr.length];
    }

    public boolean equals(Object o) {
        if (!(o instanceof PeopleArray))
            return false;
        PeopleArray that = (PeopleArray) o;
        if (nElems != that.nElems)
            return false;

        boolean eq = true;
        for (int i = 0; i < nElems; i++) {
            if (!arr[i].equals(that)) {
                eq = false;
                break;
            }
        }
        return eq;
    }

}

