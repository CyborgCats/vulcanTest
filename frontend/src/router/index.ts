import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/alumnos',
    name: 'Alumnos',
    component: () => import('@/views/AlumnosView.vue'),
  },
  {
    path: '/cursos',
    name: 'Cursos',
    component: () => import('@/views/CursosView.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
