package com.li.template;

public class Facebook extends Network {

    public Facebook(String username, String password) {
        super(username, password);
    }

    @Override
    protected boolean login(String username, String password) {
        System.out.println("\nChecking user's parameters");
        System.out.println("Name: " + username);
        System.out.print("Password: ");
        for (int i = 0; i < password.length(); i++) {
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogIn success on Facebook");
        return true;
    }

    @Override
    protected boolean sendData(String message) {
        System.out.println("Message: '" + message + "' was posted on Facebook");
        return true;
    }

    @Override
    protected void logout() {
        System.out.println("User: '" + getUsername() + "' was logged out from Facebook");
    }

    private void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 10) {
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
