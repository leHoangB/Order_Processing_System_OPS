package model;

/**
 * The class is used to add an item in JCombobox for display value(Name) and actual value (id)
 *
 * @author Le Viet Hoang
 */
public class ComboboxItem {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComboboxItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
