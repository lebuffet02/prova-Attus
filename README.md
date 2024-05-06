<h1 align="center">
  Attus Desafio
</h1>

## Tecnologias Utilizadas

- Spring Boot, Security, Data e Cloud;
- Keycloak
- JPA
- Feign Client
- Docker Compose
- Envio de Email
- Junit5
- Swagger

## Modelagem Conceitual
![Modelagem](/images/modeloConceitual.png)

- No modelo conceitual acima, temos presente duas entidades que estão realacionadas, 
dentre elas: Usuario e Endereco. Dessa forma, além dos atributos presentes em cada uma
das entidades, existe uma associação "muitos-para-um (N:1)" na entidade Usuario 
em relação a entidade Endereco.

  
## Como Executar a API

- Clonar repositório git:
```
- git clone https://github.com/lebuffet02/prova-usuarios.git
```
- Executar o Keycloak:
```
- docker-compose up
```
- Executar a API:
```
- Acessar a classe Main (UsuariosApplication), clicar no botão verde e depois em 'Run'.
```

Acessar o Swagger da API:
```
- http://localhost:8081/swagger-ui/index.html#/
```

![swagger](/images/swagger.png)

## Arquitetura

A arquitetura foi do tipo Model-View-Controller (MVC), de modo que o usuário realiza
uma request através do endpoint /token que irá devolver o bearer token através do keycloak.
Dessa forma, o bearer deve ser repassado no authorization de cada um dos endpoints.

Segue abaixo o modelo da arquitetura:

![arquitetura](/images/arquitetura.png)

## Envio de Emails

```
- Para a realização de envio de emails, eu acabei utilizando o host do gmail, mais especificamente "spring.mail.host=smtp.gmail.com",
junto com a propriedade e porta "spring.mail.port=587". Segue abaixo o exemplo utilizado para realizar o envio de emails.

    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username= email do remetente
    spring.mail.password= código de autorização do app para envio de emails
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
```

## Exemplo de JSON da API

```
               REQUEST:                                           RESPONSE:

POST http://localhost:8081/api/user                  
 
{                                                   {    
    "nomeCompleto": "Lucas Buffet",                     "nomeCompleto": "Lucas Buffet",                                       
    "email": "lebuffet02@gmail.com",                    "email": "lebuffet02@gmail.com",                                                                                             
    "cpf": "12345678911",                               "cpf": "123.456.789-11",                         
    "dataNascimento": "26/02/2002",                     "dataNascimento": "26/02/2002",                                         
    "addressDTO": {                                     "addressDTO": {              
        "estado": "rs",                                     "estado": "RS",                               
        "cidade": "Porto Alegre",                           "cidade": "Porto Alegre",                               
        "logradouro": "Afonso",                             "logradouro": "Afonso",
        "cep": "96611470",                                  "cep": "96611-470",                                
        "numero": "15"                                      "numero": "15"                                   
    }                                                   }                                                  
}                                                   }                                      
```


## Testes Unitários
![Modelagem](/images/coberturaTestesUnitarios.png)

- Com base na imagem, foi realizado cerca de 59 testes unitários, de modo que todos os
testes unitários foram realizados com sucesso e a taxa de cobertura da API foi de 91%.