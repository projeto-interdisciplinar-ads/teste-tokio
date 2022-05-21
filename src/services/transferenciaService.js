import { http } from "./config";

export default {

    listar:() => {
        return http.get('transferencia/')
    },

    salvar:(transferencia)=>{
		return http.post('transferencia/salvar',transferencia);
  },

}