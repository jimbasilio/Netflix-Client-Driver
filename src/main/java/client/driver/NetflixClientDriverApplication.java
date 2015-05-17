package client.driver;

import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import client.driver.dto.MicroClientServiceDTO;

@EnableAsync
@SpringBootApplication
public class NetflixClientDriverApplication {

    public static void main(String[] args) throws InterruptedException {
        NetflixClientDriverApplication application = new NetflixClientDriverApplication();

        application.invokeData();
        application.invokeLDAP();
        application.invokeUserLookup();

        // run this loop until kill the client
        do {

            application.invokeData();

            application.invokeLDAP();

            application.invokeUserLookup();

        } while (true);
    }

    @Async
    public Future<MicroClientServiceDTO> invokeData() {
        sleep();
        RestTemplate rest = new RestTemplate();
        MicroClientServiceDTO returnedDTO = rest.getForObject(
                "http://localhost:8080/data/1", MicroClientServiceDTO.class);
        return new AsyncResult<MicroClientServiceDTO>(returnedDTO);
    }

    @Async
    public Future<MicroClientServiceDTO> invokeLDAP() {
        sleep();
        RestTemplate rest = new RestTemplate();
        MicroClientServiceDTO returnedDTO = rest.getForObject(
                "http://localhost:8080/ldap/1", MicroClientServiceDTO.class);
        return new AsyncResult<MicroClientServiceDTO>(returnedDTO);
    }

    @Async
    public Future<MicroClientServiceDTO> invokeUserLookup() {
        sleep();
        RestTemplate rest = new RestTemplate();
        MicroClientServiceDTO returnedDTO = rest.getForObject(
                "http://localhost:8080/user/1", MicroClientServiceDTO.class);
        return new AsyncResult<MicroClientServiceDTO>(returnedDTO);
    }

    /**
     * sleep b/t 10-50ms
     */
    private void sleep() {
        // invoke each service at random intervals b/t 10-50ms

        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(40) + 10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
