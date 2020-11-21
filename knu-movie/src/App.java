import config.DBConfig;
import injected.DIContainer;
import ui.KnuMovie;

public class App {
    public static void main(String[] args) {
        boolean test = false;
        if (test) new KnuMovie(DIContainer.test, new DBConfig()).run();
        else {
            AppEnvironment appEnvironment = AppEnvironment.bootstrap();
            new KnuMovie(appEnvironment.container, appEnvironment.DBconfig).run();
        }
    }
}
