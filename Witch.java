import java.util.ArrayList;

public class Witch implements Contract{

    boolean outside;
    static ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> inventory = new ArrayList<String>();
    int houseX;
    int houseY;
    int witchX;
    int witchY; 
    double witchSize;
    
    /**
     * Constructor; initializes all of the attributes and adds to the Array of Objects that the witch will have
     */
    public Witch(){
        items.add("cauldron");
        items.add("hat");
        items.add("wand");
        items.add("spellbook");
        items.add("broom");
        outside = false;
        houseX = 20;
        houseY = 20;
        witchX = 0;
        witchY = 0;
        witchSize = 1; //Regular size for a person
        }

    /**
     * Grabs objects and adds them to the owner's inventory
     * @param item
     */
    public void grab(String item){
        if (items.contains(item)) {
            if (!inventory.contains(item)) {
                System.out.println("Grabbbing item...");
                System.out.println("You have grabbed " + item + " successfully!");
                inventory.add(item);
                System.out.println(item +" added to inventory!");
                System.out.println("What do want to do next?");
            }
            else {
                throw new RuntimeException("You already have this item in your inventory!");
            }
        }
        else {
            throw new RuntimeException("You only have limited items to pick up, please pick one of those.");
        }  
    }

    /**
     * Drops the items and removes it from the owner's inventory 
     * @param item
     * @return item
     */
    public String drop(String item){
        if (inventory.contains(item)) {
            System.out.println("Dropping item...");
            System.out.println("You have dropped " + item + " successfully!");
            inventory.remove(item);
            System.out.println("What do want to do next?");
            return item;
        }
        else {
            throw new RuntimeException("You do not have this item in your inventory!");
        }
    }

    /**
     * Lets the user examine the items that they have and their properties
     * @param item
     */
    public void examine(String item){
        System.out.println(items);
        System.out.println("What item do want to examine?");
        if (item == "cauldron") {
            System.out.println("Item: Cauldron \n Type: Object \n Level: 10 [MAX] \n Abilities: make potions, cook food \n Material: Iron ");
        }
        else if (item == "hat") {
            System.out.println("Item: Hat \n Type: Clothing \n Level: 3 \n Abilities: invisibility, stylishness \n Material: Cloth, Spider Silk, Snake Skin");
        }
        else if (item == "wand") {
            System.out.println("Item: Wand \n Type: Object \n Level: 9 \n Abilities: cast spells, hair holder \n Material: Elm Wood, Phoenix Feather Core");
        }
        else if (item == "spellbook") {
            System.out.println("Item: Spellbook \n Type: Object \n Level: 5 \n Abilities: cast spells, bug crusher \n Material: Paper, Squid Ink, Leather");
        }
        else if (item == "broom") {
            System.out.println("Item: Broom \n Type: Object \n Level: 10 [MAX] \n Abilities: fly, hit intruders \n Material: Elm Wood, Ancient Spell");
        }
        else {
            throw new RuntimeException("Please select from your items.");
        }
        System.out.println("What do want to do next?");
    }

    /**
     * Lets the user use the items that are in their inventory 
     * @param item
     */
    public void use(String item){
       if (inventory.contains(item)) {
        if (item == "cauldron") {
            System.out.println("You have made a potion!");
        }
        else if (item == "hat") {
            System.out.println("Can't do much with a hat.");
        }
        else if (item == "wand") {
            System.out.println("You cast a spell! Now, you must deal with the consequences.");
        }
        else if (item == "spellbook") {
            System.out.println("You have done some studying! You're becoming a great witch.");
        }
        else if (item == "broom") {
            System.out.println("You flew! Hope you had fun. ");
        }
       }
       else {
         throw new RuntimeException("Item is not in your inventory, please select from your inventory.");
       }
        System.out.println("What do want to do next?");
    }

    /**
     * Allows the user to walk in any direction, but with parameters if they're inside 
     * @param direction
     */
    public boolean walk(String direction){
        if (!outside ) {
           if (witchX < houseX && witchY < houseY) {
                if (direction == "North") {
                    witchY++;
                    
                }
                if (direction == "South") {
                    witchY--;
                }
                if (direction == "West") {
                    witchX--;
                }
                if (direction == "East") {
                    witchX++;
                }
            }    
           else {
                throw new RuntimeException("You cannot walk any further. You are at an edge.");
            }   
            System.out.println("Do you want to walk again?");
            System.out.println("Location: \n " + witchX + "," + witchY);
            return true;
        }
        else {
            if (direction == "North") {
                witchY++;
                
            }
            if (direction == "South") {
                witchY--;
            }
            if (direction == "West") {
                witchX--;
            }
            if (direction == "East") {
                witchX++;
            }
            System.out.println("Do you want to walk more?");
            System.out.println("Location: \n " + witchX + "," + witchY);
            return true; 
    }
}

    /**
     *  Allows the user to fly to a location if they're outside 
     * @param x
     * @param y
     */
    public boolean fly(int x, int y){
        if (outside) {
            if (inventory.contains("broom")) {
                witchX += x;
                witchY += y;
                System.out.println("What do want to do next?");
                System.out.println("Location: \n " + witchX + "," + witchY);
                return true;
            }
        else {
            throw new RuntimeException("You do not have your broom in your inventory!");
        }
        }
        else {
            throw new RuntimeException("You are not outside and therefore, cannot fly.");
        }
    }

    /**
     * Shrinks the size of the witch 
     * @return witchSize
     */
    public Number shrink(){
        if (witchSize > 0) {
            witchSize = witchSize/0.05;
            System.out.println("What do want to do next?");
            return witchSize;
        }
        else {
            throw new RuntimeException("You're too small! You can't shrink any more.");
        }
    }

    /**
     * Grows the size of the witch
     * @return witchsize
     */
    public Number grow(){
        if (witchSize < 10) {
            witchSize = witchSize*0.05;
            System.out.println("What do want to do next?");
            return witchSize;
        }
        else {
            throw new RuntimeException("You're too big! You can't grow any more.");
        }
    }

    /**
     * Allows the user to rest, empties the inventory for the user to start again
     */
    public void rest(){
        System.out.println("What do want to do next?");
        System.out.println("Good night! Have a good rest.");
        inventory.clear();
        System.out.println("Your inventory is empty. What do you want to do next?");
    }

    /**
     * Undos all changes to the witch: undos the shrinking and growing, as well as clearing the inventory, etc.
     */
    public void undo(){
        witchX = 0;
        witchY = 0; 
        outside = false;
        witchSize = 1; 
        inventory.clear();
        System.out.println("All changes undone!");
        System.out.println("What do want to do next?");
    }

    /**
     * Allows the user to walk inside
     * @return outside
     */
    public boolean insideHouse() {
        System.out.println("You are now inside.");
        outside = true;
        return outside;
    }

    /**
     * Allows the user to walk outside
     * @return outside
     */
    public boolean outsideHouse() {
        System.out.println("You are now outside.");
        outside = false;
        return outside;
    }

    public static void main (String[] args) {
        Witch newWitch = new Witch();
        System.out.println(items);
        newWitch.grab("wand");
        newWitch.examine("wand");
    }
}