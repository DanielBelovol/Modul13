package org.First;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        User.Address.Geo geo = new User.Address.Geo("-37.3159", "81.1496");
        org.First.User.Address address = new org.First.User.Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", geo);
        org.First.User.Company company = new org.First.User.Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets");
        User user = new User(1, "Krasher", "high", "Sincere@april.biz", address, "1-770-736-8031 x56442", "hildegard.org", company);


//        System.out.println(HttpMethod.post(user));
//        System.out.println(HttpMethod.put(user, 10));
//        System.out.println(HttpMethod.delete(user, 10));
//        System.out.println(HttpMethod.getUsers());
//        System.out.println(HttpMethod.getUserById("11"));
//        System.out.println(HttpMethod.getUserByUsername("Antonette"));
//           HttpMethod.printTodosForUser("https://jsonplaceholder.typicode.com/",4);
//           HttpMethod.getUserLastPostComments("https://jsonplaceholder.typicode.com","10");
    }
}