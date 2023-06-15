package guru.springframework.sfgdi.repositories;




public class GreetingInEnglishRepositoryImpl implements GreetingRepository {
    @Override
    public String getGreeting() {
        return "Hello World - EN";
    }
}
