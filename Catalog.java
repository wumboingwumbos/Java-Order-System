


public class Catalog {
    /**
     * item list
     */
    public static String[] item={"Long Sword",     //1
                        "Short Sword",      //2
                        "Dagger",           //3
                        "Claymore",         //4
                        "Mace",             //5
                        "Sheild",           //6
                        "Helmet",           //7
                        "Chestplate",       //8
                        "Health Potion"};   //9
    /**
     * 
     * item descriptions */                   
    public static String[] description={"A Long Sword: requires two hands and has increased range and damage.",
                                "A Short Sword: only reqires one hand to wield, but has less reach and damage.",
                                "A Small Dagger: can be thrown short distances .",
                                "A Claymore: and exceptionally large sword, too heavy for most",
                                "A Mace: Works best agianst armored opponents",
                                "A Shield: Can stop an arrow or two",
                                "A Helmet: protects the noggin",
                                "A Chestplate: Keeps your organs where they're at",
                                "A health potion: heals most scrapes and bruises"};
    /**
     * item prices
     */
    public static double[] rPrice={5,
                            3.50,
                            1.25,
                            8.75,
                            7.10,
                            5.00,
                            3.50,
                            8.00,
                            50.00};
    /**
     * sales tax
     */
    public static double tithe=.1;

    /**
     * get item from list
     * @param index
     * @return
     */
    public static String getItem(int index)
    {
        return item[index];
    }
    /**
     * get description
     * @param index
     * @return
     */
    public static String getDescription(int index)
    {
        return description[index];
    }
    /**
     * get price 
     * @param index
     * @return
     */
    public static double getPrice(int index)
    {
        return rPrice[index];
    }
    /**get final sale price of item with tax */
    public static double getSalePrice(int index)
    {
        return rPrice[index]+rPrice[index]*tithe;
    }
    public static void printDescriptions() {
        for (int i =0;i<9;i++) {
            System.out.println("--------Item: "+item[i]+" --------");
            System.out.println("Description: "+description[i]);
            System.out.println("Retail Price: "+rPrice[i]);
            System.out.println("Price plus Tax: "+getSalePrice(i));
            System.out.println();
        }
    }
    public static String getAllDescriptions() {
        String string="";
        for(String desc:description)
        {
            string += "\n"+desc;
        }
        return string;
    }
    
}