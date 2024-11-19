# Kanban API - Java

## **Pré-requisitos**
- **Java 17 ou superior**
- **Maven** (para gerenciar dependências)
- **Banco de dados H2** (embutido e configurado para desenvolvimento)
- **Postman ou outra ferramenta de teste de API**

---

## **Como executar o projeto**

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/kanban-api.git
   cd kanban-api
   ```

2. **Configure o ambiente**:
   Certifique-se de que você possui o Java 17 ou superior configurado no seu ambiente.

3. **Compilar e rodar o projeto**:
   - Usando o Maven:
     ```bash
     mvn spring-boot:run
     ```
   - Usando sua IDE (Eclipse, IntelliJ, etc.):
     - Importe o projeto como um projeto Maven.
     - Execute a classe principal do Spring Boot.

---

## **Endpoints da API**

### **1. Endpoints abertos**

- **Cadastro de usuários**:
  - **POST /auth**: Registra um novo usuário.
    - **Corpo da requisição**:
      ```json
      {
        "email": "string",
        "password": "string",
        "role": "User | Admin"
      }
      ```

- **Login**:
  - **POST /auth/login**: Autentica o usuário e retorna um token JWT.
    - **Corpo da requisição**:
      ```json
      {
        "email": "string",
        "password": "string"
      }
      ```

---

### **2. Endpoints fechados**

Estes endpoints requerem autenticação com um token JWT. Para incluir o token, adicione o token de autorização em Authorization no PostMan, selecione o tipo Bearer TOKEN e passe o token

#### **Tarefas**

1. **Criar uma nova tarefa**
   - **POST /tasks**
     - **Corpo da requisição**:
       ```json
       {
         "title": "Comprar suprimentos",
         "description": "Comprar suprimentos para o escritório",
         "dueDate": "2024-10-30",
         "priority": "ALTA | MEDIA | BAIXA"
       }
       ```
   - **Resposta**: Tarefa criada com sucesso.

2. **Listar todas as tarefas organizadas por coluna**
   - **GET /tasks**

3. **Mover uma tarefa para a próxima coluna**
   - **PUT /tasks/{id}/move**

4. **Atualizar uma tarefa**
   - **PUT /tasks/{id}**
     - **Corpo da requisição**:
       ```json
       {
         "title": "string",
         "description": "string",
         "dueDate": "yyyy-MM-dd",
         "priority": "ALTA | MEDIA | BAIXA"
       }
       ```

5. **Excluir uma tarefa**
   - **DELETE /tasks/{id}**

---
