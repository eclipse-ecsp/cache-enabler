package org.eclipse.ecsp.cache.config;

/**
 * Sample class for testing the jackson mapper config.
 */
public class ItemToUserNameMapping {

    private int id;
    private String itemName;
    private String userName;

    /**
     * Default constructor.
     */
    public ItemToUserNameMapping(int id, String itemName, String userName) {
        this.id = id;
        this.itemName = itemName;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
