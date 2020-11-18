import injected.DIContainer;
import ui.KnuMovie;

public class App {
    public static void main(String[] args) {
        boolean test = true;
        if (test) new KnuMovie(DIContainer.test).run();
        else {
            AppEnvironment appEnvironment = AppEnvironment.bootstrap();
            new KnuMovie(appEnvironment.container).run();
        }
    }
}
