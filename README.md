# 🍔 FEIFood – Sistema de Pedidos (Java + PostgreSQL)

O **FEIFood** é um sistema desktop desenvolvido em **Java (Swing)** com integração ao **PostgreSQL**, criado para simular um ambiente real de pedidos de alimentos.

O projeto foi construído com foco em **estruturação de dados, integração com banco e organização de código**, reproduzindo o fluxo de um sistema de delivery.

---

# 🚀 Funcionalidades

* Cadastro de usuários
* Login com validação no banco de dados
* Busca de alimentos em tempo real
* Listagem de resultados em tabela (JTable)
* Criação de pedidos
* Cálculo automático do total
* Avaliação de pedidos
* Interface gráfica em Java Swing
* Integração completa com PostgreSQL via JDBC

---

# 🧠 Arquitetura do Projeto

O projeto segue uma estrutura baseada em **MVC (Model-View-Controller)** simplificado:


/src
 ├── model/   → Entidades (Usuario, Alimento, Pedido, Avaliacao)
 ├── dao/     → Acesso ao banco (DAO)
 ├── view/    → Interfaces gráficas (Swing)
 └── util/    → Conexão com banco (JDBC)

### 🔹 Camada DAO

Responsável por toda manipulação de dados (CRUD):

* UsuarioDAO
* AlimentoDAO
* PedidoDAO
* AvaliacaoDAO

---

# 🗄 Banco de Dados

O sistema utiliza PostgreSQL com as seguintes tabelas:

### 👤 usuarios

| id | nome | email | senha |

### 🍔 alimentos

| id | nome | categoria | preco | restaurante_id |

### 🧾 pedidos

| id | usuario_id | total | data |

### ⭐ avaliacao

| id | pedido_id | nota | comentario |

📌 O banco já possui mais de **30 alimentos cadastrados** para testes.

---

# ⚙️ Tecnologias Utilizadas

* Java SE 17
* Swing (Interface gráfica)
* PostgreSQL 16
* JDBC
* Apache Maven
* NetBeans

---

# ▶️ Como Executar o Projeto

### 1. Clonar o repositório


git clone https://github.com/Danilo-4397/FEIFood.git


### 2. Importar no NetBeans

* File → Open Project
* Selecionar a pasta do projeto

### 3. Criar o banco de dados


CREATE DATABASE Feiifood;


### 4. Importar estrutura e dados

* Execute o arquivo .sql disponível no repositório

### 5. Configurar conexão

No arquivo Conexao.java, ajuste:

java
private static final String URL = "jdbc:postgresql://localhost:5433/Feiifood";
private static final String USER = "postgres";
private static final String PASSWORD = "SUA_SENHA";


### 6. Executar o sistema

* Execute a classe TelaLogin.java

---

# 🖥 Fluxo do Sistema

1. Login ou cadastro de usuário
2. Busca de alimentos
3. Seleção e criação de pedido
4. Armazenamento no banco de dados
5. Avaliação do pedido

---

# 🧪 Testes Realizados

* Múltiplos usuários cadastrados
* Inserção de +30 alimentos
* Fluxo completo de pedidos
* Testes de avaliação
* Busca por nome
* Validação da interface (responsividade e legibilidade)

---

# 🧱 Principais Desafios

**Driver JDBC não encontrado**
Correção no Maven (POM) e ajuste manual

**Método duplicado no DAO**
Refatoração do código

**Erro de coluna no banco**
Ajuste de SQL e mapeamento

**Interface com baixa legibilidade**
Melhoria de fontes e layout

---

# 📊 Foco em Dados

Este projeto também demonstra:

* Modelagem relacional
* Manipulação de dados com SQL
* Integração aplicação + banco
* Operações CRUD completas

📌 Possibilita futuras análises como:

* Produtos mais pedidos
* Avaliações médias
* Comportamento de usuários

---

# 🚀 Melhorias Futuras

* Implementação de API REST
* Dashboard com Power BI
* Painel administrativo
* Upload de imagens
* Migração para JavaFX

---

# 🎯 Conclusão

O FEIFood é um projeto completo que demonstra:

* Programação orientada a objetos
* Integração com banco de dados
* Estruturação de dados
* CRUD completo
* Organização em camadas (DAO + MVC)

O sistema funciona ponta a ponta, simulando um ambiente real de pedidos.

---

# 🔗 Acesse o Projeto

👉 [https://github.com/usuario/FEIFood](https://github.com/usuario/FEIFood)

---

💡 Projeto desenvolvido para fins acadêmicos e prática em desenvolvimento orientado a dados.


[Projeto final feiifood.pdf](https://github.com/user-attachments/files/23557187/Projeto.final.feiifood.pdf)




