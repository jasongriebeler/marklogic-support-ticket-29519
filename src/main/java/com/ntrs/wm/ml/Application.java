package com.ntrs.wm.ml;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;

import javax.net.ssl.SSLContext;

public class Application {
    public static void main(String... args) {
        /*
            openjdk version "11" 2018-09-25
            OpenJDK Runtime Environment 18.9 (build 11+28)
            OpenJDK 64-Bit Server VM 18.9 (build 11+28, mixed mode)
         */
        System.out.println(System.getProperty("java.version")); // prints "11"
        System.out.println(System.getProperty("java.runtime.name")); // prints "OpenJDK Runtime Environment"
        System.out.println(System.getProperty("java.runtime.version")); // prints 11+28
        String host = "test";
        int port = 8153;
        String username = "test";
        String password = "test";

        DatabaseClient mlClient = null;
        try {
                SSLContext sslContext = SSLContext.getInstance("SSLv3");
                sslContext.init(null, null, null);
                mlClient = DatabaseClientFactory.newClient(host, port,
                        new DatabaseClientFactory.BasicAuthContext(username, password).withSSLContext(sslContext), DatabaseClient.ConnectionType.GATEWAY);
                /*
                java.lang.StringIndexOutOfBoundsException: begin 0, end -1, length 2
	                at java.base/java.lang.String.checkBoundsBeginEnd(String.java:3319)
	                at java.base/java.lang.String.substring(String.java:1874)
	                at com.marklogic.client.impl.OkHttpServices.connect(OkHttpServices.java:338)
	                at com.marklogic.client.DatabaseClientFactory.newClient(DatabaseClientFactory.java:1277)
	                at com.marklogic.client.DatabaseClientFactory.newClient(DatabaseClientFactory.java:1231)
	                at com.ntrs.wm.ml.Application.main(Application.java:21)
                 */
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SUCCESS: " + mlClient);
    }
}
