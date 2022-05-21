import { createRouter, createWebHashHistory } from 'vue-router'
import TransferenciaView from '../views/TransferenciaView.vue'
import ListaView from '../views/ListaView.vue'

const routes = [
  {
    path: '/',
    name: 'Transferencia',
    component: TransferenciaView
  },
  {
    path: '/lista',
    name: 'Lista',
    component: ListaView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
