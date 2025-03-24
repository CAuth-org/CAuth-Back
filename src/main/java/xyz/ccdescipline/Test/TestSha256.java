package xyz.ccdescipline.Test;


import org.junit.jupiter.api.Test;

import static xyz.ccdescipline.Util.Sha256Util.sha256;

public class TestSha256 {
    @Test
    public void test01(){
        String hash = sha256("text");
        System.out.println("SHA-256: " + hash);
    }
}
