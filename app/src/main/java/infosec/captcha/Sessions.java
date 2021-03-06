package infosec.captcha;

import java.io.File;

import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

// helper class for session handling
// ref: https://javalin.io/tutorials/jetty-session-handling
public class Sessions {
    // --------------------- File Sessions Handler ---------------------
    public static SessionHandler fileSessionHandler() {
        SessionHandler sessionHandler = new SessionHandler();
        SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
        sessionCache.setSessionDataStore(fileSessionDataStore());
        sessionHandler.setSessionCache(sessionCache);
        sessionHandler.setHttpOnly(true);
        // make additional changes to your SessionHandler here
        return sessionHandler;
    }

    private static FileSessionDataStore fileSessionDataStore() {
        FileSessionDataStore fileSessionDataStore = new FileSessionDataStore();
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        File storeDir = new File(baseDir, "javalin-session-store");
        storeDir.mkdir();
        fileSessionDataStore.setStoreDir(storeDir);
        return fileSessionDataStore;
    }

    // --------------------- SQL Sessions Handler ---------------------
    // public static SessionHandler sqlSessionHandler(String driver, String url) {
    //     SessionHandler sessionHandler = new SessionHandler();
    //     SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
    //     sessionCache.setSessionDataStore(jdbcDataStoreFactory(driver, url).getSessionDataStore(sessionHandler));
    //     sessionHandler.setSessionCache(sessionCache);
    //     sessionHandler.setHttpOnly(true);
    //     // make additional changes to your SessionHandler here
    //     return sessionHandler;
    // }

    // private static JDBCSessionDataStoreFactory jdbcDataStoreFactory(String driver, String url) {
    //     DatabaseAdaptor databaseAdaptor = new DatabaseAdaptor();
    //     databaseAdaptor.setDriverInfo(driver, url);
    //     JDBCSessionDataStoreFactory jdbcSessionDataStoreFactory = new JDBCSessionDataStoreFactory();
    //     jdbcSessionDataStoreFactory.setDatabaseAdaptor(databaseAdaptor);
    //     return jdbcSessionDataStoreFactory;
    // }

}
