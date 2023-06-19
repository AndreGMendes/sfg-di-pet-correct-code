package guru.springframework.sfgdi.config;

import com.springframework.pets.DogPetService;
import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.GreetingInSpanishRepositoryImpl;
import guru.springframework.sfgdi.repositories.GreetingRepository;
import guru.springframework.sfgdi.repositories.GreetingInEnglishRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

// @PropertySource("classpath:datasource.properties")
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
@EnableConfigurationProperties (SfgConstructorConfig.class)
public class GreetingServiceConfig {

    // ---- BEAN EXAMPLE ----

   /* @Bean -----------------------------------------------------------> Configurado no ficheiro "sfgdi-config.xml"
    ConstructorGreetingService constructorGreetingService () {
        return new ConstructorGreetingService();
    }*/

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


    // ---- DATASOURCE.PROPERTIES EXAMPLE ----

    @Bean
    FakeDataSource fakeDataSource (SfgConstructorConfig sfgConstructorConfig) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUserName(sfgConstructorConfig.getUserName());
        fakeDataSource.setPassword(sfgConstructorConfig.getPassword());
        fakeDataSource.setJdbcurl(sfgConstructorConfig.getJdbcurl());

        return fakeDataSource;
    }

}
