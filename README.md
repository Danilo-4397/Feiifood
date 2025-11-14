FEIFood – Sistema de Pedidos em Java + PostgreSQL








O FEIFood é um sistema desktop desenvolvido em Java (Swing) integrado ao PostgreSQL, criado para simular um ambiente real de pedidos de alimentos.
O sistema permite cadastro, login, busca de alimentos, criação de pedidos e avaliação, funcionando como um pequeno iFood acadêmico.

🚀 Funcionalidades
✔ Cadastro de Usuário
✔ Login com validação no banco de dados
✔ Busca de alimentos
✔ Listagem de alimentos encontrados
✔ Cadastro de pedidos
✔ Avaliação de pedidos
✔ Interface gráfica moderna construída em Swing
✔ Integração completa com banco PostgreSQL via JDBC
🏗 Arquitetura do Projeto

O projeto segue uma arquitetura MVC simplificada:

/src
 ├── model/     → Entidades: Usuario, Alimento, Pedido, Avaliacao
 ├── dao/       → Classes de acesso ao banco (DAO)
 ├── view/      → Telas Swing (Login, Cadastro, Pedido, Avaliação...)
 └── util/      → Conexão JDBC

DAO (Data Access Object)

UsuarioDAO

AlimentoDAO

PedidoDAO

AvaliacaoDAO

Conexao (gerencia a conexão JDBC)

View (interface Swing)

TelaLogin

TelaCadastro

TelaPrincipal

TelaBuscaAlimento

TelaPedido

TelaAvaliacao

🗄 Banco de Dados (PostgreSQL)

O sistema utiliza o PostgreSQL com as tabelas:

🧑‍💼 usuarios

| id | nome | email | senha |

🍔 alimentos

| id | nome | categoria | preco | restaurante_id |

🧾 pedidos

| id | usuario_id | total | data |

⭐ avaliacao

| id | pedido_id | nota | comentario |

Foram adicionados mais de 30 alimentos reais à base.

⚙️ Tecnologias Utilizadas

Java SE 17

Swing

PostgreSQL 16

JDBC (Driver 42.7.7)

Apache Maven

NetBeans

📥 Como executar o projeto
1️⃣ Clonar o repositório
git clone https://github.com/usuario/FEIFood.git

2️⃣ Importar no NetBeans

Vá em File > Open Project

Selecione a pasta FEIFood

3️⃣ Configurar o banco de dados

Crie o banco:

CREATE DATABASE Feiifood;


Importe as tabelas e os alimentos cadastrados (arquivo SQL do repositório).

4️⃣ Ajustar credenciais do banco

No arquivo Conexao.java:

private static final String URL = "jdbc:postgresql://localhost:5433/Feiifood";
private static final String USER = "postgres";
private static final String PASSWORD = "SUA_SENHA_AQUI";

5️⃣ Executar

Execute a classe:

TelaLogin.java

🖥 Interface do Sistema
🔐 Tela de Login

Login estilizado, com fonte grande e interface moderna.

Botão para acessar cadastro.

🏠 Tela Principal

Acesso a:

Fazer Pedido

Buscar Alimentos

Avaliar Pedidos

🔍 Busca de Alimentos

Consulta em tempo real ao banco

Listagem em tabela Swing (JTable)

🛒 Pedidos

Adicionar alimentos

Exibir itens selecionados

Calcular total

Registrar pedido no banco

⭐ Avaliação

Seleção de pedidos realizados

Atribuição de nota

Comentário explicativo

🧪 Testes Realizados

Testes com múltiplos usuários

Teste de inserção de 30+ alimentos

Testes de pedido completo

Teste de avaliação

Teste de busca por nome

Verificação da interface (tamanhos, fontes e responsividade em Windows)

🧱 Dificuldades e Soluções
❗ Driver JDBC não era encontrado

➡ Correção do systemPath no POM
➡ Ajuste manual do jar na pasta lib

❗ Método duplicado em PedidoDAO

➡ Remoção de duplicata

❗ Coluna "categoria" não encontrada

➡ Correção do SQL e mapeamento

❗ Interface com texto pequeno

➡ Ajuste de fontes e componentes Swing

📌 Próximas melhorias (opcionais)

Implementar carrinho mais avançado

Adicionar imagens dos alimentos

Criar painel administrativo

Criar API REST futuramente

Migrar interface para JavaFX

🎉 Conclusão

O projeto FEIFood é uma aplicação completa que demonstra domínio em:

✔ Programação orientada a objetos
✔ Integração com banco de dados
✔ Construção de interface gráfica
✔ CRUD completo
✔ Arquitetura modular
✔ Boas práticas com DAO e JDBC

O sistema funciona do início ao fim, atendendo todos os requisitos acadêmicos.
