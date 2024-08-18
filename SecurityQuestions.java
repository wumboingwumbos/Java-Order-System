


public class SecurityQuestions {
    public static String[] securityQuestions={"0: Where were you born",                 //1
                                    "1: What is the mightiest beast you have slain",    //2
                                    "2: What is the name of your kingdom?",             //3
                                    "3: What is your favorite color?",                  //4
                                    "4: What is the first spell you learned",           //5
                                    "5: How old were you when you first set out on your adventure?",    //6
                                    };
                                    
    public static void main(String[] args) {
        list();
    }

    public static void list() {
        for(String Q : securityQuestions)
        {
            System.out.println(Q);
        }
    }
}

