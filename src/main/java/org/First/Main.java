package org.First;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        User.Address.Geo geo = new User.Address.Geo("-37.3159", "81.1496");
        org.First.User.Address address = new org.First.User.Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", geo);
        org.First.User.Company company = new org.First.User.Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets");
        User user = new User(1, "Krasher", "high", "Sincere@april.biz", address, "1-770-736-8031 x56442", "hildegard.org", company);

        HttpMethod httpMethod = new HttpMethod();

//        System.out.println(httpMethod.post(user));
//        System.out.println(httpMethod.put(user, 10));
//        System.out.println(httpMethod.delete(user, 10));
//        System.out.println(httpMethod.getUsers());
//        System.out.println(httpMethod.getUserById("11"));
//        System.out.println(httpMethod.getUserByUsername("Antonette"));
//           HttpMethod.printTodosForUser("https://jsonplaceholder.typicode.com/",4);
           httpMethod.getUserLastPostComments("https://jsonplaceholder.typicode.com","1");
    }
}