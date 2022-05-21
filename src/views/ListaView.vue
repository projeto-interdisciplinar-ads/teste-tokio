<template>
   <table class="table">
        <thead>
          <tr>
            <th>CONTA ORIGEM</th>
            <th>CONTA DESTINO</th>
            <th>VALOR</th>
            <th>DATA DA TRANSFERÊNCIA</th>
            <th>DATA AGENDADA</th>
            <th>TAXA</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="transferencia of transferencia" :key="transferencia.id">
            <td>{{ transferencia.contaOrigem }}</td>
            <td>{{ transferencia.contaDestino }}</td>
            <td>{{ `${transferencia.valor}R$` }}</td>
            <td>{{ transferencia.transferenciaFormatado }}</td>
            <td>{{ transferencia.agendamentoFormatado }}</td>
            <td>{{ `${transferencia.taxa}R$` }}</td>
          </tr>
        </tbody>
      </table>
</template>

<script>
import TransferenciaService from "../services/transferenciaService";
export default {
  data() {
    return {
      transferencias: {
        contaOrigem: "",
        contaDestino: "",
        agendamentoFormatado: "",
        transferenciaFormatado: "",
        valor: "",
        taxa: "",
        tipoOperacao: "",
      },
      transferencia: [],
    };
  },
  mounted() {
    this.listar();
  },
  methods: {
    listar() {
      TransferenciaService.listar()
        .then((response) => {
          this.transferencia = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style>
</style>

