<template>
  <div>
    <h1>Lista de Cursos</h1>
    <ul v-if="cursos.length">
      <li v-for="curso in cursos" :key="curso.id">
        {{ curso.nombre }} - Cupo: {{ curso.cupoMaximo }} - Alumnos: {{ curso.alumnos.length }}
      </li>
    </ul>
    <p v-else>Cargando cursos...</p>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  data() {
    return {
      cursos: [],
    };
  },
  async created() {
    try {
      const response = await api.get('/cursos');
      this.cursos = response.data;
    } catch (error) {
      console.error('Error al cargar cursos:', error);
    }
  },
};
</script>
