<template>
  <div id="app">
    <nav></nav>
    <div class="container">
      <form @submit.prevent="salvar" class="w-50 p-3">
        <div class="form-group">
          <label for="contaOrigem">Conta de Origem*</label>
          <input
            type="text"
            class="form-control"
            id="contaOrigem"
            placeholder="Conta Origem"
            v-model="contaOrigem"
          />
          <small id="contaOrigemHelp" class="form-text text-muted"
            >Nós nunca compartilharemos sua conta com ninguém.</small
          >
        </div>
        <div class="form-group">
          <label for="contaDestino">Conta Destino*</label>
          <input
            type="text"
            class="form-control"
            id="contaDestino"
            v-model="contaDestino"
            placeholder="Conta que receberá a transferência"
          />
        </div>
        <div class="form-group">
          <label for="valor">Valor a ser transferido</label>
          <input
            type="number"
            class="form-control"
            id="valor"
            v-model="valor"
            placeholder="Insira o valor"
          />
        </div>
        <div class="form-group">
          <label for="dataAgendamento">Data de Agendamento</label>
          <datepicker
            v-model="dataAgendamento"
            placeholder="Selecione a data"
          ></datepicker>
        </div>
        <div class="form-group">
          <label for="dataTransferencia">Data de Transferência</label>
          <datepicker
            v-model="dataTransferencia"
            placeholder="Selecione a data"
          ></datepicker>
        </div>
        <br />
        <div class="form-group">
          <label for="tipoOperacao">Tipo Operação</label>
          <select class="custom-select" v-model="tipoOperacao">
            <option
              v-for="option in options"
              :key="option.id"
              v-bind:value="option.value"
            >
              {{ option.text }}
            </option>
          </select>
        </div>
        <br />
        <div class="form-group">
          <button type="submit" class="btn btn-primary">
            Realizar Transferência
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import TransferenciaService from "../services/transferenciaService";
import Datepicker from "vue3-datepicker";
export default {
  components: {
    Datepicker,
  },
  data() {
    return {
      transferencias: {
        contaOrigem: "",
        contaDestino: "",
        dataTransferencia: null,
        dataAgendamento: null,
        valor: "",
        taxa: "",
        tipoOperacao: "",
      },
      options: [
        { text: "A", value: "A" },
        { text: "B", value: "B" },
        { text: "C", value: "C" },
      ],
      transferencia: [],
    };
  },
  methods: {
    salvar() {
      TransferenciaService.salvar({
        contaOrigem: this.contaOrigem,
        contaDestino: this.contaDestino,
        dataTransferencia: this.dataTransferencia,
        dataAgendamento: this.dataAgendamento,
        valor: this.valor,
        tipoOperacao: this.tipoOperacao,
      }).then((response) => {
        console.log(response);
      });
    },
  },
};
</script>

<style>
</style>
