# Backend
Backend do site Mind Plus

docker run --name mindplus -e POSTGRES_PASSWORD="#Dr2IUj%gbxmSdJb" -e POSTGRES_USER=postgres_admin -e POSTGRES_DB=mindplus -p 5432:5432 -d postgres

Criar o container do keycloak (explicado no repositório de segurança)
mvn spring-boot:run
