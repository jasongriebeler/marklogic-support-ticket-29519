package com.ntrs.wm.ml;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.DocumentPage;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.query.QueryManager;

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
        String host = "xxxx";
        int port = 8153;
        String username = "xxxx";
        String password = "xxxx";

        DatabaseClient mlClient = null;
        try {
                SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
                sslContext.init(null, null, null);
                mlClient = DatabaseClientFactory.newClient(host, port,
                        new DatabaseClientFactory.BasicAuthContext(username, password).withSSLContext(sslContext), DatabaseClient.ConnectionType.GATEWAY);

                QueryManager queryManager = mlClient.newQueryManager();
                DocumentPage page = mlClient.newDocumentManager().read("/WDS/canonical/financialAsset/ee3fb1ba-1511-49ef-937c-7845e6a371a1.json");
                System.out.println(page.next().getContent(new StringHandle()).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SUCCESS: " + mlClient);
    }
}
