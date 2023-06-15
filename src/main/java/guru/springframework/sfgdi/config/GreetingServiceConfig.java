package guru.springframework.sfgdi.config;

import com.springframework.pets.DogPetService;
import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.repositories.GreetingInSpanishRepositoryImpl;
import guru.springframework.sfgdi.repositories.GreetingRepository;
import guru.springframework.sfgdi.repositories.GreetingInEnglishRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class GreetingServiceConfig {

    // ---- BEAN EXAMPLE ----

    @Bean
    ConstructorGreetingService constructorGreetingService () {
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService () {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }

    @Bean
    @Primary
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }


    // ---- SERVICE REPOSITORY EXAMPLE ----

    @Bean
    GreetingRepository englishGreetingRepository () {
        return new GreetingInEnglishRepositoryImpl();
    };

    @Bean("i18nService")
    @Profile("EN")
    I18nEnglishGreetingService greetingInEnglish(@Qualifier ("englishGreetingRepository") GreetingRepository greetingRepository) {
        return new I18nEnglishGreetingService(greetingRepository);
    }


    @Bean
    GreetingRepository spanishGreetingRepository() { return new GreetingInSpanishRepositoryImpl();}

    @Bean("i18nService")
    @Profile({"ES", "default"})
    I18nSpanishGreetingService greetingInSpanish(@Qualifier ("spanishGreetingRepository") GreetingRepository greetingRepository) {
        return new I18nSpanishGreetingService(greetingRepository);
    }


    // ---- FACTORY SERVICE EXAMPLE ----

    @Bean
    PetServiceFactory petServiceFactory () {
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService (PetServiceFactory petServiceFactory) {
        return petServiceFactory().getPetService("dog");
    }

    @Profile({"cat"})
    @Bean
    PetService catPetService (PetServiceFactory petServiceFactory) {
        return petServiceFactory().getPetService("cat");
    }

}
