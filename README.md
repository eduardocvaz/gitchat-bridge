# GitChat Bridge

## Estrutura do Projeto
A organização do projeto "GitChat Bridge" reflete uma estrutura modular, onde cada funcionalidade e integração é cuidadosamente segregada em pacotes específicos dentro do diretório src/main/java/com/trf5/gitchatbridge/.
Essa abordagem modular promove a clareza, a manutenibilidade e a escalabilidade do código, facilitando a compreensão da arquitetura do projeto e a implementação de novas funcionalidades no futuro.
Na raiz do projeto, encontramos elementos essenciais para a configuração e execução, como o arquivo docker-compose.yml (localizado na pasta compose) que define os serviços utilizados, o arquivo pom.xml para o gerenciamento de dependências com o Maven, e a pasta requests que auxilia na interação com a API via Postman.


A estrutura interna do diretório `src/main/java/com/trf5/gitchatbridge/` é composta pelos seguintes pacotes:
+ `comum`: Este pacote abriga componentes e funcionalidades compartilhadas entre as diferentes integrações, como o serviço `GoogleChatWebHookService` que centraliza o envio de notificações para o Google Chat.
+ `gitlab`: Contém as classes e recursos específicos para a integração com o GitLab, como o tratamento de webhooks e a geração de notificações personalizadas.
+ `grafana`: Engloba os elementos relacionados à integração com o Grafana, como a configuração de painéis e alertas.
+ `logs`: Reúne as classes e lógicas responsáveis pelo monitoramento de logs e pelo envio de notificações em caso de eventos relevantes.
+ GitChatBridgeApplication.java: A classe principal da aplicação Spring Boot, responsável por inicializar e configurar o projeto.
  
Essa estrutura bem definida e organizada facilita a navegação pelo código, a identificação de componentes específicos e a manutenção do projeto como um todo, contribuindo para um desenvolvimento mais eficiente e colaborativo.
## Docker Compose

Para facilitar a implantação e o gerenciamento dos diversos serviços que compõem o projeto, utilizamos o Docker Compose. O arquivo `docker-compose.yml`, localizado na pasta `compose`, define e configura os seguintes serviços:
+ `gitchat-bridge`: O serviço principal, responsável por executar a aplicação Spring Boot do projeto "GitChat Bridge".
+ `postgresql`: O serviço de banco de dados PostgreSQL, utilizado para armazenar informações relevantes, como as mensagens enviadas pelo GoogleChatWebHookService.
+ `prometheus`: O serviço de coleta e armazenamento de métricas, essencial para o monitoramento do desempenho da aplicação e dos serviços relacionados.
+ `grafana`: A ferramenta de visualização de dados, que permite a criação de painéis e gráficos interativos para acompanhar as métricas coletadas pelo Prometheus.

Além do `docker-compose.yml`, o projeto também utiliza um arquivo `.env` para armazenar as variáveis de ambiente, promovendo a segurança e a flexibilidade na configuração do ambiente. As variáveis definidas neste arquivo incluem:
+ `DATABASE_HOST`, `DATABASE_PORT`, `DATABASE_NAME`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`: Credenciais de acesso ao banco de dados PostgreSQL.
+ `URL_WEBHOOK_GITLAB`, `URL_WEBHOOK_LOGBACK`: URLs dos webhooks do Google Chat para as integrações com o GitLab e o Logback, respectivamente.

Antes de iniciar os serviços com o `docker-compose up`, é crucial configurar corretamente as URLs dos webhooks do Google Chat no arquivo `.env`. Recomenda-se criar dois espaços distintos no Google Chat, um dedicado às notificações do GitLab e outro para o Monitoramento de Logs. Em cada espaço, adicione um webhook e substitua as variáveis `URL_WEBHOOK_GITLAB` e `URL_WEBHOOK_LOGBACK` no arquivo `.env` pelos respectivos links gerados para acesso aos webhooks. Essa configuração garante que as notificações sejam direcionadas corretamente para os espaços correspondentes no Google Chat, facilitando a organização e o acompanhamento das informações relevantes para cada integração.
Ao executar o comando `docker-compose up`, todos os serviços definidos no arquivo `docker-compose.yml` são iniciados e configurados automaticamente, criando um ambiente pronto para uso. Essa combinação do Docker Compose com o arquivo `.env` oferece uma solução eficiente para a implantação e o gerenciamento do projeto "GitChat Bridge", simplificando o processo de configuração e garantindo a consistência do ambiente em diferentes contextos de execução.
