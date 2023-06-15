package guru.springframework.sfgdi.services;

import guru.springframework.sfgdi.repositories.GreetingRepository;

/**
 * Created by jt on 12/27/19.
 */

// @Profile({"ES", "default"})
// @Service("i18nService")
public class I18nSpanishGreetingService implements GreetingService {

    private final GreetingRepository greetingRepository;

    public I18nSpanishGreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {
        return "Hola Mundo - ES";
    }
}
