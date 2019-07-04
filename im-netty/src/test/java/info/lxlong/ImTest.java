package info.lxlong;

import info.lxlong.client.Client;
import info.lxlong.server.Server;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Scanner;

@Slf4j
public class ImTest {

    @Test
    public void startServer() throws InterruptedException {
        new Server().start();
    }

    @Test
    public void startClient() throws InterruptedException {
        new Client().start();
    }

    @Test
    public void test() {
        Scanner scanner = new Scanner(System.in);
        log.info("请输入：");
        String reply = scanner.nextLine();
        log.info("----------- " + reply);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        log.info("请输入：");
        String reply = scanner.nextLine();
        log.info("----------- " + reply);
    }
}
