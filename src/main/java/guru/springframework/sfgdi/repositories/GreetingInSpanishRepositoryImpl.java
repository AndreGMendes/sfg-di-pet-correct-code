package guru.springframework.sfgdi.repositories;

public class GreetingInSpanishRepositoryImpl implements GreetingRepository{
    @Override
    public String getGreeting() {
        return "Hola Mundo - ES";
    }
}
