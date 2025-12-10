# Aplicação de Design Patterns — Laboratório de Análises Clínicas

## Resumo do domínio
Sistema simples para gerenciamento de ordens de exames e resultados. Entidades principais: **Paciente**, **Ordem de Exame**, **Resultado**, e um repositório de dados em memória que simula um banco. O sistema aceita entrada do usuário via console.

## Padrões escolhidos
### 1) Singleton — `LabDatabase`
**Aplicabilidade:** garante uma única instância do repositório de dados (ex.: cache em memória) acessível de qualquer parte do sistema.  
**Justificativa:** evita inconsistências de estados e múltiplas cópias de dados quando várias partes do sistema (facade, observadores, builder) precisam ler/gravar ordens e resultados.  
**Imagem sugerida:** diagrama simples: App → LabDatabase (instância única).

### 2) Builder — `ExamOrderBuilder`
**Aplicabilidade:** criação de ordens de exame com muitos campos opcionais (lista de exames, observações, prioridade, coleta domiciliar).  
**Justificativa:** torna a construção de `ExamOrder` legível e evita construtores com muitos parâmetros; facilita testes e ampliação futura.

### 3) Estrutural — Facade — `LabFacade`
**Aplicabilidade:** provê interface simples (pedido de exame, processamento, consulta de resultado) escondendo a complexidade (db, notificações, geração de laudo).  
**Justificativa:** clientes (UI/console) chamam poucas operações, reduz acoplamento e centraliza lógica de alto nível.

### 4) Comportamental — Observer — `ResultObserver`
**Aplicabilidade:** notificar pacientes ou sistemas externos assim que um resultado é gerado/validado.  
**Justificativa:** separa a lógica de notificação do processamento de resultados; permite múltiplos observadores (SMS, email, portal paciente) sem alterar o código que gera resultados.

## Fluxo de uso (exemplo)
1. Usuário insere dados do paciente e solicita exames via console (Builder cria `ExamOrder`).
2. `LabFacade` recebe ordem, salva em `LabDatabase` (Singleton) e inicia processamento.
3. Ao gerar `ExamResult`, `LabDatabase` persiste e notifica `ResultObserver` que alerta o paciente.

## Observações finais
- Organização simples, clara para uma entrega acadêmica.  
- Documentação e código-fonte no repositório `doc/` e `src/`.  
- Padrões escolhidos favorecem manutenção, testes e extensão do sistema.
